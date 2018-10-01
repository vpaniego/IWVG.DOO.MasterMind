package masterMind.solucion.views;

import masterMind.solucion.controllers.PresenterController;
import masterMind.solucion.utils.IO;

class BoardView {

    private PresenterController controller;

    BoardView(PresenterController controller) {
        assert controller != null;
        this.controller = controller;
    }

    void write() {
        IO io = new IO();
        new ColorView();
        io.writeln();
    }
}
