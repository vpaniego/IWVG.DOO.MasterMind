package masterMind.solucion.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Code {

    String color;
    HashSet<Integer> index;

    private boolean duplicatesAllowed = false;
    private String colores = "ARVZ";
    private int codeLength = 4;

    public Code() {
    }

    public Code(String color, HashSet<Integer> index) {
        this.color = color;
        this.index = index;
    }

    public String getColor() {
        return this.color;
    }

    public int getCodeLength() {
        return this.codeLength;
    }

    public HashSet<Integer> getIndex() {
        return this.index;
    }

    public HashSet<Code> getSecretCode() {
        HashSet<Code> secretCode = new HashSet<Code>();

        HashMap<String, HashSet<Integer>> auxiliar = new HashMap<String, HashSet<Integer>>();
        for (int i = 0; i < this.codeLength; i++) {
            String color = getRandomColor();
            if (!duplicatesAllowed) {
                while (auxiliar.containsKey(color)) {
                    color = getRandomColor();
                }
            }
            HashSet<Integer> indices = auxiliar.getOrDefault(color, new HashSet<Integer>());
            indices.add(i);
            auxiliar.put(color, indices);
            Code code = new Code(color, indices);
            secretCode.add(code);
        }

        return secretCode;
    }

    private String getRandomColor() {
        char c = colores.charAt(ThreadLocalRandom.current().nextInt(colores.length()));
        String color = String.valueOf(c);
        return color;
    }

}
