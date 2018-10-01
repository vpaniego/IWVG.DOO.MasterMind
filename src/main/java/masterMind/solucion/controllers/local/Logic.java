package masterMind.solucion.controllers.local;

import masterMind.solucion.models.Game;


public class Logic {

    private Game game;

    private LocalCodeController codeController;

    private LocalStartController startController;

    private LocalContinueController continueController;

    public Logic() {
        game = new Game();
        startController = new LocalStartController(game, codeController);
        codeController = new LocalCodeController(game,continueController);
        continueController = new LocalContinueController(game);
    }

    public LocalOperationController getController() {
        switch (game.getState()){
            case INITIAL:
                return startController;
            case IN_GAME:
                return codeController;
            case FINAL:
                return continueController;
            case EXIT:
            default:
                return null;
        }
    }
}
