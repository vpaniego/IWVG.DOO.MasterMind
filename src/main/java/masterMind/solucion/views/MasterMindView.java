package masterMind.solucion.views;

import masterMind.solucion.controllers.ContinueController;
import masterMind.solucion.controllers.CodeController;
import masterMind.solucion.controllers.OperationControllerVisitor;
import masterMind.solucion.controllers.StartController;
import masterMind.solucion.controllers.OperationController;

import masterMind.solucion.utils.LimitedIntDialog;
import masterMind.solucion.utils.IO;
import masterMind.solucion.utils.YesNoDialog;
import masterMind.solucion.utils.StartDialog;
import masterMind.solucion.utils.CodeDialog;

public class MasterMindView implements OperationControllerVisitor {

    public void interact(OperationController operationController) {
        assert operationController != null;
        operationController.accept(this);
    }

    @Override
    public void visit(StartController startController) {
        StartDialog startDilalog = new StartDialog();
        int users = new LimitedIntDialog("En qué modo deseas jugar? :\n" + "1. Partida\n" + "2. Demo\n", 1, 2).read();
        startDilalog.write("Entendido! Juguemos...\n");
        startController.start(users);
        new BoardView(startController).write();
    }

    @Override
    public void visit(ContinueController continueController) {
        continueController.resume(new YesNoDialog("Desea continuar")
                .read());
    }

    @Override
    public void visit(CodeController codeController) {
        codeController.score("");
        new BoardView(codeController).write();
    }

}
