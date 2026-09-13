public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java Main <csv-file-path> <output-method> [output-file-path]");
            System.exit(1);
        }

        String csvFilePath = args[0];
        String outputMethod = args[1].toLowerCase();

        System.out.println("Reading file from: " + csvFilePath);
        System.out.println("Output method: " + outputMethod);
    }
}