package masterMind.solucion.models;

import java.util.HashSet;

public class Game {

    private State state;

    private Board board;

    private int currentGuess;

    private static final int NUM_PLAYERS = 2;
    private static final int NUM_GUESSES = 10;

    private int codeLength = 4;

    public Game() {
        state = State.INITIAL;
        this.currentGuess = 0;
        board = new Board(Game.NUM_PLAYERS, Game.NUM_GUESSES);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getNumPlayers() {
        return NUM_PLAYERS;
    }

    public int getNumGuesses() {
        return NUM_GUESSES;
    }

    public int getCurrentGuess() { return this.currentGuess; }

    public void setCurrentGuess(int currentGuess) {
        this.currentGuess = currentGuess;
    }

    public int getCodeLength(){ return this.codeLength; }

    public void clear() {
        this.currentGuess = 0;
        board.clear();
    }

    public HashSet<Code> getSecretCode(){
        return board.getSecretCode();
    }

    public boolean existMasterMind() {
        return board.existMasterMind();
    }

	/*public Color take() {
		return turn.take();
	}
	
	public void change() {
		turn.change();
	}
	
	public boolean full(Coordinate origin) {
		return board.full(origin, turn.take());
	}
	
	public boolean empty(Coordinate target) {
		return board.empty(target);
	}	
	

	
	public void put(Coordinate target) {
		board.put(target, turn.take());
	}

	public void remove(Coordinate origin) {
		board.remove(origin, turn.take());
	}

	public void clear() {
		board.clear();		
	}

	public boolean complete() {
		return board.complete();
	}
	
	public boolean existTicTacToe() {
		return board.existTicTacToe(turn.take());
	}

	public Color getColor(Coordinate coordinate) {
		return board.getColor(coordinate);
	}*/

}
