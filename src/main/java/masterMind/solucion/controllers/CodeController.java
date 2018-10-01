package masterMind.solucion.controllers;

import masterMind.solucion.models.Code;

public interface CodeController extends OperationController ,
        PresenterController {

    void score();
}
