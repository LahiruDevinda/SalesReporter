package output;

import java.io.IOException;
import java.util.Map;

public interface OutputStrategy {
    void writeReport(String content) throws IOException;
}
