package masterMind.solucion.controllers.local;

import masterMind.solucion.controllers.OperationControllerVisitor;
import masterMind.solucion.controllers.StartController;
import masterMind.solucion.models.Game;
import masterMind.solucion.models.State;
import masterMind.solucion.utils.ClosedInterval;

public class LocalStartController extends LocalOperationController implements
        StartController {

    private LocalCodeController codeController;

    LocalStartController(Game game,
                         LocalCodeController codeController) {
        super(game);
        assert codeController != null;
        this.codeController = codeController;
    }

    public void start(int users) {
        assert new ClosedInterval(0, this.numPlayers()).includes(users);
        assert this.getState() == State.INITIAL;
        codeController.score("");
        this.setState(State.IN_GAME);
    }


    @Override
    public void accept(OperationControllerVisitor operationControllerVisitor) {
        operationControllerVisitor.visit(this);
    }

}
