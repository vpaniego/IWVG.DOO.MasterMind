package masterMind.solucion.controllers;

import masterMind.solucion.controllers.ContinueController;
import masterMind.solucion.controllers.MoveController;
import masterMind.solucion.controllers.PutController;
import masterMind.solucion.controllers.StartController;

public interface OperationControllerVisitor {

	void visit(StartController startController);
	
	/*void visit(PutController putController);
	
	void visit(MoveController moveController);*/
	
	/*void visit(ContinueController continueController);*/

}

