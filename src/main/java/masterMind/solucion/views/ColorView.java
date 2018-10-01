package masterMind.solucion.views;

import masterMind.solucion.models.Color;
import masterMind.solucion.utils.IO;

class ColorView {

	private IO io;

	ColorView() {
		io = new IO();
	}

	void writeln(String title) {
		this.write(title);
		io.writeln();
	}

	void write(String title) {
		io.write(title);
	}


	void writeWinner() {
		io.writeln("Victoria!!!! ");
	}

	void writeLoser() {
		io.writeln("Oh, perdiste :(");
	}

}
