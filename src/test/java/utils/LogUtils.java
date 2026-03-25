package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogUtils {

    private LogUtils() {
    }

    public static void clearLogFile() throws IOException {
        File logFile = new File(System.getProperty("user.dir") + "/logs/test-execution.log");
        if (logFile.exists()) {
            new FileWriter(logFile, false).close(); // false = overwrite mode (truncates)
        }
    }
}
