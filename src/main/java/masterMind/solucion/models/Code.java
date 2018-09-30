package masterMind.solucion.models;


import java.util.concurrent.ThreadLocalRandom;
import java.util.HashSet;

public class Code {

    int codeLength;

    Color color;
    HashSet<Integer> index;

    public Code(int codeLength, ){
        this.codeLength = codeLength;
        this.generate();
    }

    public void generate() {
        code = new HashMap<>();

        for (int i = 0; i < this.codeLength; i++) {
            String color = randomColor();
            if (!duplicatesAllowed) {
                while (code.containsKey(color)) {
                    color = obtenerColorClaveAleatorio();
                }
            }
            HashSet<Integer> indices = code.getOrDefault(color, new HashSet<Integer>());
            indices.add(i);
            code.put(color, indices);
        }
    }

    private String randomColor() {
        String colores = "ARVZ";
        char c = colores.charAt(ThreadLocalRandom.current().nextInt(colores.length()));
        String color = String.valueOf(c);
        return color;
    }

    public Color getColor() {
        return this.color;
    }

    public HashSet<Integer> getIndex() {
        return this.index;
    }
}