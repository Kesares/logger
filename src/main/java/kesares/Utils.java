package kesares;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Utils {

    private Utils() {}

    public static String formatActualDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    }
}
