package masterMind.origen;

import java.io.Console;
import java.lang.StringBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class MasterMind {
    private boolean duplicatesAllowed;
    private int numberOfGuesses;
    private int codeLength;
    private int currentGuess;
    private HashMap<String, HashSet<Integer>> code;
    private String[] colorsAllowed = {"A", "R", "V", "Z"};
    private String[] gameModeAllowed = {"1","2"};
    private static String GAME_MODE_DEMO = "2";

    public MasterMind() {
        codeLength = 4;
        numberOfGuesses = 10;
        duplicatesAllowed = false;
        currentGuess = 0;
    }

    public static void main(String[] args) {
        new MasterMind().play();
    }


    public void play() {

        generateCode();

        Console console = System.console();
        int numModeCount = 0;
        assert console != null;

        System.out.println("Bienvenido al juego MasterMind!");

        String mode = console.readLine("En qué modo deseas jugar? :\n" + "1. Partida\n" + "2. Demo\n");
        System.out.println("Entendido! Sigamos...\n");

        if(!isValidGameMode(mode)){
            System.out.println("El modo de juego indicado no es correcto. Debe ser el valor 1 ó 2.");
        } else {
            if(!mode.equals(GAME_MODE_DEMO)) {
                System.out.printf("Tengo en mente una clave de %d colores, con valores permitidos [A-amarillo, R-rojo, V-verde, Z-azul].\n", codeLength);
                System.out.printf("Como norma del juego: No está permitido poner colores duplicados. \n");
                System.out.printf("Intentas adivinarlo? Tienes %d intentos. Adelante! \n", numberOfGuesses);

                boolean winner = false;
                String guess = null;

                while (currentGuess < numberOfGuesses) {
                    System.out.printf("Intento %d: ", currentGuess + 1);

                    guess = console.readLine();

                    GuessResult result = submitGuess(guess);
                    if (result.isPerfectGuess()) {
                        winner = true;
                        break;
                    }

                    if (!result.getScore().isEmpty()) {
                        System.out.println(result.getScore());
                    } else if (!result.getMessage().isEmpty()) {
                        System.out.println(result.getMessage());
                    }
                }

                String endGameMessage = winner ? "Victoria!!!!!" : "Oh, perdiste :(";
                System.out.println(endGameMessage);
            } else {
                System.out.println("ModoDemo");
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
                    case 'B':
                        scoreDeads++;
                        matches.put(color, matches.getOrDefault(color, 0) + 1);
                        break;
                    case 'N':
                        scoreInjureds++;
                        matches.put(color, matches.getOrDefault(color, 0) + 1);
                    default:
                        perfectGuess = false;
                }
            } else {
                throw new IllegalArgumentException(String.format("El intento debe ser un color entre [A-amarillo, R-rojo, V-verde, Z-azul]!"));
            }
        }

        return new GuessResult(getScoreMessage(scoreDeads, scoreInjureds), perfectGuess);
    }

    private boolean isValidGameMode(String mode) {
        return Arrays.asList(gameModeAllowed).contains(mode);
    }

    private boolean isValidGuessColor(String color) {
        return Arrays.asList(colorsAllowed).contains(color);
    }

    private char checkGuessValue(String color, int index) {
        char score = '\0';

        if (code.containsKey(color)) {
            HashSet<Integer> indices = code.get(color);
            if (indices.contains(index)) {
                score = 'B';
            } else {
                score = 'N';
            }
        }

        return score;
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
