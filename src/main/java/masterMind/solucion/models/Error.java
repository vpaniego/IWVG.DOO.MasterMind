package masterMind.solucion.models;

public enum Error {
    INTERVAL_COLOR("El intento debe ser un color entre [A-amarillo, R-rojo, V-verde, Z-azul]!"),
    DUPLICATE_COLOR("No está permitido poner colores duplicados!");

    private String message;

    private Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
