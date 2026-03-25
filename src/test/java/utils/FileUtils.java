package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class FileUtils {

    private static final Logger LOG = LoggerFactory.getLogger(FileUtils.class);

    private FileUtils() {
    }

    public static void clearReportFolder() throws IOException {
        File reportDir = new File(System.getProperty("user.dir") + File.separator + "report");
        if (reportDir.exists()) {
            FileUtils.clearDirectory(reportDir);
        }
    }

    private static void clearDirectory(File dir) {
        Arrays.stream(Objects.requireNonNull(dir.listFiles()))
                .forEach(file -> {
                    if (file.isDirectory()) {
                        clearDirectory(file);
                    }
                    file.delete();
                });
    }
}
