package kesares;

public class Main {

    public static final Logger LOGGER = Logger.getLogger();
    private static final String TAG = Main.class.getName();

    public static void main(String[] args) {
        LOGGER.log("Hello World!", TAG);
        LOGGER.debug("Hello World!", TAG);
        LOGGER.success("Hello World!", TAG);
        LOGGER.warn("Hello World!", TAG);
        LOGGER.error("Hello World!", TAG);
    }
}
