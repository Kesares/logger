package kesares;

public enum Level {

    ERROR(AnsiColor.RED),
    WARN(AnsiColor.YELLOW),
    DEBUG(AnsiColor.GRAY),
    INFO(AnsiColor.BLUE),
    SUCCESS(AnsiColor.GREEN);

    private final String colorCode;

    Level(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorCode() {
        return colorCode;
    }
}
