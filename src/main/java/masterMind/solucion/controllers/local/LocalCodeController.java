package masterMind.solucion.controllers.local;

import masterMind.solucion.controllers.CodeController;
import masterMind.solucion.controllers.OperationControllerVisitor;
import masterMind.solucion.controllers.Error;

import masterMind.solucion.models.Code;
import masterMind.solucion.models.Color;
import masterMind.solucion.models.GuessResult;
import masterMind.solucion.models.Game;
import masterMind.solucion.models.State;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class LocalCodeController extends LocalOperationController implements
        CodeController {

    LocalContinueController continueController;

    LocalCodeController(Game game, LocalContinueController continueController) {
        super(game);
        assert continueController != null;
        this.continueController = continueController;
    }

    public void score(String guess) {
        assert this.getState() == State.IN_GAME;

        boolean winner = false;

        while (getCurrentGuess() < getNumberOfGuess()) {

            GuessResult result = submitGuess(guess);
            if (result.isPerfectGuess()) {
                winner = true;
                break;
            }

            /*if (!result.getScore().isEmpty()) {
                io.writeln(result.getScore());
            } else if (!result.getMessage().isEmpty()) {
                io.writeln(result.getMessage());
            }*/
        }

        /*String endGameMessage = winner ? "Victoria!!!!!" : "Oh, perdiste :(";
        io.writeln(endGameMessage);*/

        continueController.resume(false);
        this.setState(State.FINAL);

    }


    private GuessResult submitGuess(String guess) {
        setCurrentGuess(getCurrentGuess() + 1);

        if (guess.length() != getCodeLength()) {
            String errorMessage = String.format("El intento debe tener %d colores de longitud!", getCodeLength());
            return new GuessResult(errorMessage);
        }

        if (isDuplicatedGuess(guess)) {
            String errorMessage = String.format(Error.DUPLICATE_COLOR.getMessage());
            return new GuessResult(errorMessage);
        }

        try {
            return getScore(guess);
        } catch (IllegalArgumentException e) {
            String errorMessage = String.format("Intento incorrecto: %s", e.getMessage());
            return new GuessResult(errorMessage);
        }
    }

    private GuessResult getScore(String guess) {
        int scoreDeads = 0;
        int scoreInjureds = 0;
        boolean perfectGuess = true;

        HashMap<String, Integer> matches = new HashMap<>();
        for (int i = 0; i < guess.length(); i++) {
            char guessValue = guess.charAt(i);
            String color = String.valueOf(guessValue).toUpperCase();
            if (isValidGuessColor(color)) {
                /*int colorMatchCount = matches.getOrDefault(color, 0);
                HashSet<Code> code = getSecretCode();
                List<String> codeKeys = new ArrayList<String>(code.keySet());
                if (codeKeys.contains(color)){
                    Code secCode = (Code)code.get(color);
                    int actualColorCount = secCode.getIndex().size();
                    if (actualColorCount > 0 && colorMatchCount == actualColorCount) {
                        perfectGuess = false;
                        continue;
                    }
                }^*/

                switch (checkGuessValue(color, i)) {
                    case B:
                        scoreDeads++;
                        matches.put(color, matches.getOrDefault(color, 0) + 1);
                        break;
                    case N:
                        scoreInjureds++;
                        matches.put(color, matches.getOrDefault(color, 0) + 1);
                        break;
                    default:
                        perfectGuess = false;
                        break;
                }
            } else {
                throw new IllegalArgumentException(String.format(Error.INTERVAL_COLOR.getMessage()));
            }
        }

        return new GuessResult(getScoreMessage(scoreDeads, scoreInjureds), perfectGuess);
    }

    private boolean isDuplicatedGuess(String guess) {
        char[] arrayGuess = guess.toCharArray();
        Set<String> noDuplicados = new HashSet<String>();
        for (char c : arrayGuess) {
            noDuplicados.add(String.valueOf(c));
        }
        return noDuplicados.size() != guess.length();
    }

    private boolean isValidGuessColor(String color) {
        Color guessColor;
        try {
            guessColor = Color.valueOf(color);
        } catch (IllegalArgumentException e) {
            guessColor = Color.NO_EXIST;
        }
        return !guessColor.equals(Color.NO_EXIST);
    }

    private Color checkGuessValue(String color, int index) {
        /*if (code.containsKey(color)) {
            HashSet<Integer> indices = code.get(color);
            if (indices.contains(index)) {
                return Color.B;
            } else {
                return Color.N;
            }
        }
        return Color.NO_EXIST;
        */
        return Color.N;

    }

    private String getScoreMessage(int deads, int injureds) {
        StringBuffer strBf = new StringBuffer().append("" + deads).append(" muertos").append(" y ").append("" + injureds).append(" heridos");
        return strBf.toString();
    }

    @Override
    public void accept(OperationControllerVisitor operationControllerVisitor) {
        operationControllerVisitor.visit(this);
    }

}
