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
    }
}