package kesares;

public class Logger {

    private static final Logger LOGGER = new Logger();

    private Logger() {}

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
        String message = String.format("%s %s%s%s %s ", Utils.formatActualDateTime(), level.getColorCode(), level, AnsiColor.DEFAULT, classTag);
        if (level == Level.ERROR || level == Level.WARN) {
            System.out.println(message + level.getColorCode() + msg + AnsiColor.DEFAULT);
        } else {
            System.out.println(message + msg);
        }
    }
}
