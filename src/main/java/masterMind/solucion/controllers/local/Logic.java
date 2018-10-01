package masterMind.solucion.controllers.local;

import masterMind.solucion.models.Game;


public class Logic {

    private Game game;

    private LocalCodeController codeController;

    private LocalStartController startController;

    private LocalContinueController continueController;

    public Logic() {
        game = new Game();
        continueController = new LocalContinueController(game);
        codeController = new LocalCodeController(game,continueController);
        startController = new LocalStartController(game, codeController);
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
