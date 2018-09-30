package masterMind.solucion.controllers.local;

import masterMind.solucion.controllers.OperationController;
import masterMind.solucion.controllers.OperationControllerVisitor;
import masterMind.solucion.controllers.local.LocalController;
import masterMind.solucion.models.Game;

public abstract class LocalOperationController extends LocalController
		implements OperationController {

	protected LocalOperationController(Game game) {
		super(game);
	}

	public abstract void accept(
			OperationControllerVisitor operationControllerVisitor);

}
