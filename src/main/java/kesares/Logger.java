package kesares;

public class Logger {

    private static final Logger LOGGER = new Logger(new LogWriter("src/main/resources/logs/"));

    private final LogWriter logWriter;
    private boolean isConsoleLogEnabled;
    private boolean isColoredConsoleLogEnabled;

    private Logger(LogWriter logWriter) {
        this.logWriter = logWriter;
        this.isConsoleLogEnabled = true;
        this.isColoredConsoleLogEnabled = false;
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    public void error(String msg, String classTag) {
        this.log(msg, classTag, Level.ERROR);
    }

    public void warn(String msg, String classTag) {
        this.log(msg, classTag, Level.WARN);
    }

    public void success(String msg, String classTag) {
        this.log(msg, classTag, Level.SUCCESS);
    }

    public void debug(String msg, String classTag) {
        this.log(msg, classTag, Level.DEBUG);
    }

    public void log(String msg, String classTag) {
        this.log(msg, classTag, Level.INFO);
    }

    public void log(String msg, String classTag, Level level) {
        String formattedDateTime = Utils.formatActualDateTime();
        this.logWriter.writeToLog(formattedDateTime, level, classTag, msg);
        if (!this.isConsoleLogEnabled) return;
        this.consoleLog(formattedDateTime, level, classTag, msg);
    }

    private void consoleLog(String formattedDateTime, Level level, String classTag, String msg) {
        if (this.isColoredConsoleLogEnabled) {
            String coloredLogAttributes = String.format("%s %s%s%s %s: ", formattedDateTime, level.getColorCode(), level,
                    AnsiColor.DEFAULT, classTag);
            System.out.println(coloredLogAttributes + msg);
        } else {
            String logAttributes = String.format("%s %s %s: ", formattedDateTime, level, classTag);
            System.out.println(logAttributes + msg);
        }
    }

    public void setConsoleLogEnabled(boolean isEnabled) {
        this.isConsoleLogEnabled = isEnabled;
    }

    public void setColoredConsoleLogEnabled(boolean isColorPrintEnabled) {
        this.isColoredConsoleLogEnabled = isColorPrintEnabled;
    }
}
