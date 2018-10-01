package masterMind.solucion.models;

public class GuessResult {

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