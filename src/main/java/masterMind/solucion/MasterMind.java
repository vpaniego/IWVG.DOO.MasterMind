package masterMind.solucion;

import masterMind.solucion.utils.IO;
import masterMind.solucion.models.Color;
import masterMind.solucion.models.Error;

import java.lang.StringBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;


public class MasterMind {

    private Logic logic;

    private MasterMindView;


    /*private boolean duplicatesAllowed;
    private int numberOfGuesses;
    private int codeLength;
    private int currentGuess;
    private HashMap<String, HashSet<Integer>> code;
    private String[] gameModeAllowed = {"1", "2"};
    private static String GAME_MODE_DEMO = "2";

    private IO io = new IO();*/

    public MasterMind() {
        /*codeLength = 4;
        numberOfGuesses = 10;
        duplicatesAllowed = false;
        currentGuess = 0;*/

        logic = new Logic();
        view = new MasterMindView();
    }


    public void play() {
        LocalOperationController controller;
        do {
            controller = logic.getController();
            if (controller != null){
                view.interact(controller);
            }
        } while (controller != null);

    }

    public static void main(String[] args) {
        new MasterMind().play();
    }



    public static void main(String[] args) {
        new MasterMind().play();
    }


    public void play() {

        generateCode();

        int numModeCount = 0;

        io.writeln("Bienvenido al juego MasterMind!");

        String mode = io.readLine("En qué modo deseas jugar? :\n" + "1. Partida\n" + "2. Demo\n");
        io.writeln("Entendido! Sigamos...\n");

        if (!isValidGameMode(mode)) {
            io.writeln("El modo de juego indicado no es correcto. Debe ser el valor 1 ó 2.");
        } else {
            if (!mode.equals(GAME_MODE_DEMO)) {
                io.writef("Tengo en mente una clave de %d colores, con valores permitidos [A-amarillo, R-rojo, V-verde, Z-azul].\n", codeLength);
                io.writeln("Como norma del juego 'No está permitido poner colores duplicados'. \n");
                io.writef("Intentas adivinarlo? Tienes %d intentos. Adelante! \n", numberOfGuesses);

                boolean winner = false;
                String guess = null;

                while (currentGuess < numberOfGuesses) {
                    io.writef("Intento %d: ", currentGuess + 1);

                    guess = io.readLine();

                    GuessResult result = submitGuess(guess);
                    if (result.isPerfectGuess()) {
                        winner = true;
                        break;
                    }

                    if (!result.getScore().isEmpty()) {
                        io.writeln(result.getScore());
                    } else if (!result.getMessage().isEmpty()) {
                        io.writeln(result.getMessage());
                    }
                }

                String endGameMessage = winner ? "Victoria!!!!!" : "Oh, perdiste :(";
                io.writeln(endGameMessage);
            } else {
                io.writeln("ModoDemo");
            }
        }

    }

    private void generateCode() {
        code = new HashMap<>();
        for (int i = 0; i < codeLength; i++) {
            String color = obtenerColorClaveAleatorio();
            if (!duplicatesAllowed) {
                while (code.containsKey(color)) {
                    color = obtenerColorClaveAleatorio();
                }
            }
            HashSet<Integer> indices = code.getOrDefault(color, new HashSet<Integer>());
            indices.add(i);
            code.put(color, indices);
        }
    }

    private String obtenerColorClaveAleatorio() {
        String colores = "ARVZ";
        char c = colores.charAt(ThreadLocalRandom.current().nextInt(colores.length()));
        String color = String.valueOf(c);
        return color;
    }

    private GuessResult submitGuess(String guess) {
        currentGuess++;

        if (guess.length() != codeLength) {
            String errorMessage = String.format("El intento debe tener %d colores de longitud!", codeLength);
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
                int colorMatchCount = matches.getOrDefault(color, 0);
                int actualColorCount = code.getOrDefault(color, new HashSet<>()).size();
                if (actualColorCount > 0 && colorMatchCount == actualColorCount) {
                    perfectGuess = false;
                    continue;
                }
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

    private boolean isValidGameMode(String mode) {
        return Arrays.asList(gameModeAllowed).contains(mode);
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
        if (code.containsKey(color)) {
            HashSet<Integer> indices = code.get(color);
            if (indices.contains(index)) {
                return Color.B;
            } else {
                return Color.N;
            }
        }
        return Color.NO_EXIST;
    }

    private String getScoreMessage(int deads, int injureds) {
        StringBuffer strBf = new StringBuffer().append("" + deads).append(" muertos").append(" y ").append("" + injureds).append(" heridos");
        return strBf.toString();
    }


    private class GuessResult {
        private String scoreValue;
        private boolean perfectGuess;
        private String messageGuess;

        public GuessResult() {
            scoreValue = "";
            perfectGuess = false;
            messageGuess = "";
        }


        public GuessResult(String message) {
            this();
            messageGuess = message;
        }


        public GuessResult(String scoreValue, boolean perfectGuess) {
            this();
            this.scoreValue = scoreValue;
            this.perfectGuess = perfectGuess;
        }


        public GuessResult(String scoreValue, boolean perfectGuess, String message) {
            this(scoreValue, perfectGuess);
            messageGuess = message;
        }

        public String getScore() {
            return scoreValue;
        }


        public boolean isPerfectGuess() {
            return perfectGuess;
        }


        public String getMessage() {
            return messageGuess;
        }
    }

}
