package by.onliner.pages.enums;

public enum Color {
    WHITE_COLOR("rgba(255, 255, 255, 1)"),
    YELLOW_COLOR("rgba(255, 236, 196, 1)");

    private final String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
