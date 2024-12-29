package kesares;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Logger implements Loggable {

    private static final DateTimeFormatter LOG_TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private static final HashMap<String, Logger> LOGGERS = new HashMap<>();

    private final String name;
    private final LogWriter logWriter;
    private boolean isConsoleLogEnabled;
    private boolean isColoredConsoleLogEnabled;

    private Logger(String name, LogWriter logWriter) {
        this.name = name;
        this.logWriter = logWriter;
        this.isConsoleLogEnabled = true;
        this.isColoredConsoleLogEnabled = false;
    }

    public static Logger getLogger(String name, LogWriter logWriter) {
        if (LOGGERS.containsKey(name)) {
            return LOGGERS.get(name);
        }
        Logger logger = new Logger(name, logWriter);
        LOGGERS.put(name, logger);
        return logger;
    }

    @Override
    public void error(String msg) {
        this.log(msg, Level.ERROR);
    }

    @Override
    public void error(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        this.log(stringWriter.toString(), Level.ERROR);
    }

    @Override
    public void warn(String msg) {
        this.log(msg, Level.WARN);
    }

    @Override
    public void success(String msg) {
        this.log(msg, Level.SUCCESS);
    }

    @Override
    public void debug(String msg) {
        this.log(msg, Level.DEBUG);
    }

    @Override
    public void log(String msg) {
        this.log(msg, Level.INFO);
    }

    private void log(String msg, Level level) {
        String formattedDateTime = LocalDateTime.now().format(LOG_TIMESTAMP_FORMAT);
        this.logWriter.writeToLog(formattedDateTime, level, this.name, msg);
        if (!this.isConsoleLogEnabled) return;
        this.consoleLog(formattedDateTime, level, this.name, msg);
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
