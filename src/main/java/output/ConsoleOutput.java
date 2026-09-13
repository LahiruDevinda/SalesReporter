package output;

import java.io.IOException;

public class ConsoleOutput implements OutputStrategy {
    @Override
    public void writeReport(String content) throws IOException {
        System.out.print(content);
    }
}
