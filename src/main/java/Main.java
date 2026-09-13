import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static class ProductSale {
        String productId;
        String productName;
        String category;
        int quantitySold;
        double unitPrice;

        public ProductSale(String productId, String productName, String category, int quantitySold, double unitPrice) {
            this.productId = productId;
            this.productName = productName;
            this.category = category;
            this.quantitySold = quantitySold;
            this.unitPrice = unitPrice;
        }

        public double getTotalRevenue() {
            return quantitySold * unitPrice;
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java Main <csv-file-path> <output-method> [output-file-path]");
            System.exit(1);
        }

        String csvFilePath = args[0];
        String outputMethod = args[1].toLowerCase();

        List<ProductSale> salesList = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] columns = line.split(",");
                String productId = columns[0].trim();
                String productName = columns[1].trim();
                String category = columns[2].trim();
                int quantitySold = Integer.parseInt(columns[3].trim());
                double unitPrice = Double.parseDouble(columns[4].trim());

                salesList.add(new ProductSale(productId, productName, category, quantitySold, unitPrice));
            }
            br.close();

            if (salesList.isEmpty()) {
                System.err.println("Error: CSV file contains no data.");
                return;
            }

            System.out.println("Successfully loaded " + salesList.size() + " products.");

        } catch (Exception e) {
            System.err.println("Error processing sales report: " + e.getMessage());
        }
    }
}