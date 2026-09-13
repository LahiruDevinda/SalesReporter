package output;

import java.io.IOException;

public interface OutputStrategy {
    void writeReport(String content) throws IOException;
}
