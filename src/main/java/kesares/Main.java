package kesares;

public class Main {

    public static final String MODULE_NAME = "Test Module";
    public static final Logger LOGGER = Logger.getLogger(MODULE_NAME, new LogWriter("src/main/resources/logs/"));

    public static void main(String[] args) {
        LOGGER.setColoredConsoleLogEnabled(true);
        LOGGER.info("Hello World!");
        LOGGER.debug("Hello World!");
        LOGGER.success("Hello World!");
        LOGGER.warn("Hello World!");
        LOGGER.error("Hello World!");
        int i = 10 + 5;
        LOGGER.info("Das Ergebnis von 10 + 5 ist " + i);

        try {
            System.out.println(1 / 0);
        } catch (ArithmeticException e) {
            LOGGER.error(e);
        }
        System.out.println("Das Ergebnis von 10 + 5 ist " + i);
    }
}
