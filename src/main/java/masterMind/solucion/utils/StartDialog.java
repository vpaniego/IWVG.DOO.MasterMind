package masterMind.solucion.utils;

public class StartDialog {

    private String title;

    public StartDialog() {
        write();
    }

    public void write(String title){
        IO io = new IO();
        io.writeln(title);
    }

    public void write() {
        IO io = new IO();
        io.writeln("Bienvenido al juego MasterMind!");
        io.writeln("Tengo en mente una clave de 4 colores, con valores permitidos [A-amarillo, R-rojo, V-verde, Z-azul].");
        io.writeln("Como norma del juego 'No está permitido poner colores duplicados'.");
        io.writeln("Intentas adivinarlo? Tienes 10 intentos. Adelante!\n");
    }

}
