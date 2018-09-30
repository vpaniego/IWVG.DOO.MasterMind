package masterMind.solucion.controllers;

import masterMind.solucion.models.Coordinate;

public interface RandomCoordinateController extends CoordinateController {

	Coordinate getTarget(Coordinate origin);

}
