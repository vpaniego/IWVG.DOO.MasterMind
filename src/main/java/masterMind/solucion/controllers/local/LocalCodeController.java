package masterMind.solucion.controllers.local;

import masterMind.solucion.controllers.CodeController;
import masterMind.solucion.controllers.OperationControllerVisitor;
import masterMind.solucion.models.Code;
import masterMind.solucion.models.Game;
import masterMind.solucion.models.State;

public class LocalCodeController extends LocalOperationController implements
        CodeController {

    LocalContinueController continueController;

    LocalCodeController(Game game, LocalContinueController continueController) {
        super(game);
        assert continueController != null;
        this.continueController = continueController;
    }

    public void score() {
        assert this.getState() == State.IN_GAME;
        continueController.resume(false);
        this.setState(State.FINAL);
    }

    @Override
    public void accept(OperationControllerVisitor operationControllerVisitor) {
        operationControllerVisitor.visit(this);
    }

}
