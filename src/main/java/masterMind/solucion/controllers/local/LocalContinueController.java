package masterMind.solucion.controllers.local;

import masterMind.solucion.controllers.ContinueController;
import masterMind.solucion.controllers.OperationControllerVisitor;
import masterMind.solucion.models.Game;
import masterMind.solucion.models.State;

public class LocalContinueController extends LocalOperationController implements
		ContinueController {

	LocalContinueController(Game game) {
		super(game);
	}

	public void resume(boolean another) {
		assert this.getState() == State.FINAL;
		if (another) {
			this.clear();
			this.setState(State.INITIAL);
		} else {
			this.setState(State.EXIT);
		}
	}

	@Override
	public void accept(OperationControllerVisitor operationControllerVisitor) {
		operationControllerVisitor.visit(this);
	}

}
