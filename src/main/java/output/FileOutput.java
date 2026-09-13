package output;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class FileOutput implements OutputStrategy {
    private final String filePath;

    public FileOutput(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void writeReport(String content) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.print(content);
        }
    }
}
