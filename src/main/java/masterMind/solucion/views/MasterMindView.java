package masterMind.solucion.views;

/*import masterMind.solucion.controllers.ContinueController;
import masterMind.solucion.controllers.CoordinateController;
import masterMind.solucion.controllers.Error;
import masterMind.solucion.controllers.MoveController;
import masterMind.solucion.controllers.OperationController;
*/
import masterMind.solucion.controllers.OperationControllerVisitor;
/*import masterMind.solucion.controllers.PutController;
import masterMind.solucion.controllers.RandomCoordinateController;
import masterMind.solucion.controllers.StartController;
import masterMind.solucion.controllers.UserCoordinateController;*/
import masterMind.solucion.utils.IO;

public class MasterMindView implements OperationControllerVisitor {

    private IO io = new IO();

    public void interact(OperationController operationController) {
        assert operationController != null;
        operationController.accept(this);
    }

    @Override
    public void visit(StartController startController) {

        io.writeln("Bienvenido al juego MasterMind!");
        io.writef("Tengo en mente una clave de %d colores, con valores permitidos [A-amarillo, R-rojo, V-verde, Z-azul].\n", codeLength);
        io.writeln("Como norma del juego 'No está permitido poner colores duplicados'. \n");
        io.writef("Intentas adivinarlo? Tienes %d intentos. Adelante! \n", numberOfGuesses);

        int users = new LimitedIntDialog("En qué modo deseas jugar? :\n" + "1. Partida\n" + "2. Demo\n", 1, 2).read();
        io.writeln("Entendido! Juguemos...\n");

        startController.start(users);

        new BoardView(startController).write();
    }

    /*@Override
    public void visit(PutController putController) {
        ColorView colorView = new ColorView(putController.take());
        colorView.writeln("Pone el jugador ");
        Coordinate target;
        Error error = null;
        do {
            target = this.getTarget("En",
                    putController.getCoordinateController());
            error = putController.validateTarget(target);
            if (error != null) {
                io.writeln("" + error);
            }
        } while (error != null);
        putController.put(target);
        new BoardView(putController).write();
        if (putController.existTicTacToe()) {
            colorView.writeWinner();
        }
    }

    @Override
    public void visit(MoveController moveController) {
        ColorView colorView = new ColorView(moveController.take());
        colorView.writeln("Mueve el jugador ");
        Coordinate origin;
        Error error = null;
        do {
            origin = this.getOrigin(moveController.getCoordinateController());
            error = moveController.validateOrigin(origin);
            if (error != null) {
                io.writeln("" + error);
            }
        } while (error != null);
        moveController.remove(origin);
        Coordinate target;
        error = null;
        do {
            target = this.getTarget("A",
                    moveController.getCoordinateController(), origin);
            error = moveController.validateTarget(origin, target);
            if (error != null) {
                io.writeln("" + error);
            }
        } while (error != null);
        moveController.put(target);
        new BoardView(moveController).write();
        if (moveController.existTicTacToe()) {
            colorView.writeWinner();
        }
    }

    @Override
    public void visit(ContinueController continueController) {
        continueController.resume(new YesNoDialog("Desea continuar")
                .read());
    }

    private Coordinate getTarget(String title,
                                 CoordinateController coordinateController) {
        if (coordinateController instanceof UserCoordinateController) {
            return this.getTarget(title,
                    (UserCoordinateController) coordinateController);
        } else if (coordinateController instanceof RandomCoordinateController) {
            return this.getTarget(title,
                    (RandomCoordinateController) coordinateController);
        }
        return null;
    }

    private Coordinate getTarget(String title,
                                 UserCoordinateController coordinateController) {
        Coordinate coordinate = coordinateController.getTarget();
        new CoordinateView(title, coordinate).read();
        return coordinate;
    }

    private Coordinate getTarget(String title,
                                 RandomCoordinateController coordinateController) {
        Coordinate coordinate = coordinateController.getTarget();
        new CoordinateView("La máquina pone en ", coordinate).write();
        io.readString(". Pulse enter para continuar");
        return coordinate;
    }

    private Coordinate getOrigin(CoordinateController coordinateController) {
        if (coordinateController instanceof UserCoordinateController) {
            return this
                    .getOrigin((UserCoordinateController) coordinateController);
        } else if (coordinateController instanceof RandomCoordinateController) {
            return this
                    .getOrigin((RandomCoordinateController) coordinateController);
        }
        return null;
    }

    private Coordinate getOrigin(UserCoordinateController coordinateController) {
        Coordinate coordinate = coordinateController.getOrigin();
        new CoordinateView("De", coordinate).read();
        return coordinate;
    }

    private Coordinate getOrigin(RandomCoordinateController coordinateController) {
        Coordinate coordinate = coordinateController.getOrigin();
        new CoordinateView("La máquina quita de ", coordinate).write();
        io.readString(". Pulse enter para continuar");
        return coordinate;
    }

    private Coordinate getTarget(String title,
                                 CoordinateController coordinateController, Coordinate origin) {
        if (coordinateController instanceof UserCoordinateController) {
            return this.getTarget(title,
                    (UserCoordinateController) coordinateController);
        } else if (coordinateController instanceof RandomCoordinateController) {
            return this.getTarget(title,
                    (RandomCoordinateController) coordinateController, origin);
        }
        return null;
    }

    private Coordinate getTarget(String title,
                                 RandomCoordinateController coordinateController, Coordinate origin) {
        Coordinate coordinate = coordinateController.getTarget(origin);
        new CoordinateView("La máquina pone en ", coordinate).write();
        io.readString(". Pulse enter para continuar");
        return coordinate;
    }
    */

}
