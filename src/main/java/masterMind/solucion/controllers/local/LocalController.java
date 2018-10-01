package masterMind.solucion.controllers.local;

import masterMind.solucion.models.Color;
import masterMind.solucion.models.Game;
import masterMind.solucion.models.Code;
import masterMind.solucion.models.State;

import java.util.HashSet;

public abstract class LocalController {

    private Game game;

    protected LocalController(Game game) {
        assert game != null;
        this.game = game;
    }

    protected int numPlayers() {
        return game.getNumPlayers();
    }

    protected State getState() {
        return game.getState();
    }

    protected int getCurrentGuess() {
        return game.getCurrentGuess();
    }

    protected void setCurrentGuess(int current) {
        game.setCurrentGuess(current);
    }

    protected int getNumberOfGuess() {
        return game.getNumGuesses();
    }

    protected int getCodeLength() {
        return game.getCodeLength();
    }

    protected HashSet<Code> getSecretCode() {
        return game.getSecretCode();
    }

    public void setState(State state) {
        assert state != null;
        game.setState(state);
    }


    public void clear() {
        game.clear();
    }

    public boolean existMasterMind() {
        return game.existMasterMind();
    }

}
