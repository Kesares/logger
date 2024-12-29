package kesares;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogWriter implements AutoCloseable {

    private static final Pattern LOG_NUMBER_PATTERN = Pattern.compile(".*_(\\d+)\\.log");

    private final BufferedWriter writer;

    public LogWriter(String logsPath) {
        final Path path = Paths.get(logsPath);
        final String nextFileName = this.getNextFileName(logsPath);
        final Path filePath = path.resolve(nextFileName);
        try {
            if (Files.notExists(path)) {
                Files.createDirectories(path);
                System.out.println("Directory '" + path + "' created successfully");
            }
            this.writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToLog(String formattedDateTime, Level level, String classTag, String msg) {
        try {
            this.writer
                    .append('[')
                    .append(formattedDateTime)
                    .append("] [")
                    .append(Thread.currentThread().getName())
                    .append('/')
                    .append(level.toString())
                    .append("] [")
                    .append(classTag)
                    .append("]: ")
                    .append(msg)
                    .write('\n');
            this.writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Error writing to log file", e);
        }
    }

    @Override
    public void close() throws Exception {
        this.writer.close();
    }

    private String getNextFileName(String logsPath) {
        final String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        final int lastLogNumber = this.getLastLogNumber(logsPath, currentDate);
        return String.format("log_%s_%d.log", currentDate, lastLogNumber <= 0 ? 1 : lastLogNumber + 1);
    }

    private int getLastLogNumber(String logsPath, String currentDate) {
        File[] files = new File(logsPath).listFiles(File::isFile);
        if (files == null || files.length == 0) return -1;

        int maxLogNumber = 0;
        for (File file : files) {
            final String name = file.getName();
            if (name.startsWith("log_" + currentDate)) {
                final int logNumber = this.extractLogNumber(name);
                if (logNumber > maxLogNumber) {
                    maxLogNumber = logNumber;
                }
            }
        }
        return maxLogNumber;
    }

    private int extractLogNumber(String filename) {
        final Matcher matcher = LOG_NUMBER_PATTERN.matcher(filename);
        if (!matcher.matches()) return -1;
        return Integer.parseInt(matcher.group(1));
    }
}