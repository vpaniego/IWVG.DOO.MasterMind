package masterMind.solucion.models;

public class Game {

	private State state;
	
	private Code code;

	private Board board;
	
	private static final int NUM_PLAYERS = 2;
	
	public Game() {
		state = State.INITIAL;
		code = new Code();
		board = new Board(Game.NUM_PLAYERS);
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State state){
		this.state = state;
	}

	public int getNumPlayers() {
		return NUM_PLAYERS;
	}

	public void clear() {
		board.clear();
	}

	public boolean duplicateAllowed(){
		board.
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
