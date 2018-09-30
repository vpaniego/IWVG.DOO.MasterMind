package masterMind.solucion.controllers;

import masterMind.solucion.models.Color;
import masterMind.solucion.models.Coordinate;

public interface ColocateController extends OperationController,
		PresenterController {

	Color take();

	void put(Coordinate target);

	boolean existTicTacToe();

	CoordinateController getCoordinateController();

}
