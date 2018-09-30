package masterMind.solucion.utils;

import java.io.Console;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class IO {

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
            System.in));

    private Console console = System.console();

    public String readLine(String texto) {
        assert console != null;
        return console.readLine(texto);
    }

    public String readLine() {
        assert console != null;
        return console.readLine();
    }

    public String readString(String title) {
        String input = null;
        boolean ok = false;
        do {
            this.write(title);
            try {
                input = bufferedReader.readLine();
                ok = true;
            } catch (Exception ex) {
                this.writeError("de cadena de caracteres");
            }
        } while (!ok);
        return input;
    }

    public int readInt(String title) {
        int input = 0;
        boolean ok = false;
        do {
            try {
                input = Integer.parseInt(this.readString(title));
                ok = true;
            } catch (Exception ex) {
                this.writeError("entero");
            }
        } while (!ok);
        return input;
    }

    public char readChar(String title) {
        char charValue = ' ';
        boolean ok = false;
        do {
            String input = this.readString(title);
            if (input.length() != 1) {
                this.writeError("caracter");
            } else {
                charValue = input.charAt(0);
                ok = true;
            }
        } while (!ok);
        return charValue;
    }

    public void writeln() {
        System.out.println();
    }

    public void write(String texto) {
        System.out.print(texto);
    }

    public void writeln(String texto) {
        System.out.println(texto);
    }

    public void writef(String texto, int param) {
        System.out.printf(texto, param);
    }

    private void writeError(String formato) {
        System.out.println("ERROR DE FORMATO! "
                + "Introduzca un valor con formato " + formato + ".");
    }
}