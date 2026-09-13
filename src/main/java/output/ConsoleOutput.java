package output;

public class ConsoleOutput implements OutputStrategy {
    @Override
    public void writeReport(String content){
        System.out.print(content);
    }
}
