package masterMind.solucion.controllers.local;

import masterMind.solucion.models.Color;
import masterMind.solucion.models.Game;
import masterMind.solucion.models.State;

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
