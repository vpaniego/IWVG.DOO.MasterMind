package masterMind.solucion.utils;

public class CodeDialog {

    private String title;

    public int currentGuess;

    private IO io = new IO();

    public CodeDialog(int currentGuess) {
        this.currentGuess = currentGuess;
        write();
    }

    public void write(String title){
        io.writeln(title);
    }

    public void write() {
        io.writef("Intento %d: ", currentGuess + 1);
    }

    public String read(){
        return io.readLine();
    }

}
