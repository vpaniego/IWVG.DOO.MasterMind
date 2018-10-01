package masterMind.solucion.controllers;

import masterMind.solucion.controllers.ContinueController;
import masterMind.solucion.controllers.CodeController;
import masterMind.solucion.controllers.StartController;

public interface OperationControllerVisitor {

	void visit(StartController startController);
	
	void visit(CodeController codeController);
	
	void visit(ContinueController continueController);

}

