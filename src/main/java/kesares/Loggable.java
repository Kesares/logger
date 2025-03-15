package kesares;

public interface Loggable {

    void error(String msg);
    void error(Throwable t);
    void error(String msg, Throwable t);
    void warn(String msg);
    void warn(Throwable t);
    void warn(String msg, Throwable t);
    void success(String msg);
    void debug(String msg);
    void info(String msg);
}
