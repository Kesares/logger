package kesares;

public interface Loggable {

    void error(String msg);
    void error(Throwable t);
    void warn(String msg);
    void success(String msg);
    void debug(String msg);
    void log(String msg);
}
