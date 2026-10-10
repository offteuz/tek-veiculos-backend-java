package br.com.teksystem.tekveiculos.vehicle.domain.vehicle;

public enum Color {
    WHITE("Branco"),
    SILVER("Prata"),
    BLACK("Preto"),
    GRAY("Cinza"),
    BLUE("Azul"),
    RED("Vermelho"),
    BROWN("Marrom"),
    BEIGE("Bege"),
    GREEN("Verde"),
    YELLOW("Amarelo");

    private final String description;

    public static Color fromDescription(String description) {
        for (Color color : Color.values()) {
            if (color.description.equals(description)) {
                return color;
            }
        }
        throw new IllegalArgumentException("Color unknown: " + description);
    }

    Color(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}