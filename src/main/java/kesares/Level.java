package kesares;

public enum Level {

    ERROR("Error", AnsiColor.RED),
    WARN("Warn", AnsiColor.YELLOW),
    DEBUG("Debug", AnsiColor.GRAY),
    INFO("Info", AnsiColor.BLUE),
    SUCCESS("Success", AnsiColor.GREEN);

    private final String name;
    private final String colorCode;

    Level(String name, String colorCode) {
        this.name = name;
        this.colorCode = colorCode;
    }

    public String getName() {
        return name;
    }

    public String getColorCode() {
        return colorCode;
    }
}
