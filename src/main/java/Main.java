import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

        try {
            List<ProductSale> salesList = new ArrayList<>();
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

            StringBuilder sb = new StringBuilder();
            sb.append("============================================\n");
            sb.append("PRODUCT SALES SUMMARY REPORT\n");
            sb.append("============================================\n\n");
            sb.append("--- Revenue Per Product ---\n");

            Map<String, Double> categoryRevenueMap = new LinkedHashMap<>();
            ProductSale bestSeller = salesList.get(0);
            ProductSale highestRevenue = salesList.get(0);
            double grandTotal = 0.0;

            for (ProductSale sale : salesList) {
                double revenue = sale.getTotalRevenue();
                grandTotal += revenue;

                sb.append(String.format("%-6s %-18s %-12s LKR %.2f\n",
                        sale.productId, sale.productName, sale.category, revenue));

                categoryRevenueMap.put(sale.category, categoryRevenueMap.getOrDefault(sale.category, 0.0) + revenue);

                if (sale.quantitySold > bestSeller.quantitySold) {
                    bestSeller = sale;
                }

                if (revenue > highestRevenue.getTotalRevenue()) {
                    highestRevenue = sale;
                }
            }

            sb.append("\n--- Revenue Per Category ---\n");
            for (Map.Entry<String, Double> entry : categoryRevenueMap.entrySet()) {
                sb.append(String.format("%-15s : LKR %.2f\n", entry.getKey(), entry.getValue()));
            }

            sb.append("\n--- Highlights ---\n");
            sb.append(String.format("Best-Selling Product   : %s (%d units)\n", bestSeller.productName, bestSeller.quantitySold));
            sb.append(String.format("Highest Revenue        : %s (LKR %.2f)\n", highestRevenue.productName, highestRevenue.getTotalRevenue()));
            sb.append(String.format("Grand Total Revenue    : LKR %.2f\n", grandTotal));

            String reportContent = sb.toString();

            if ("console".equals(outputMethod)) {
                System.out.print(reportContent);
            } else if ("file".equals(outputMethod)) {
                if (args.length < 3) {
                    System.err.println("Error: Output file path is required when output-method is 'file'.");
                    return;
                }
                PrintWriter out = new PrintWriter(new FileWriter(args[2]));
                out.print(reportContent);
                out.close();
                System.out.println("Report successfully saved to " + args[2]);
            } else {
                System.err.println("Error: Invalid output method. Use 'console' or 'file'.");
            }

        } catch (Exception e) {
            System.err.println("Error processing sales report: " + e.getMessage());
        }
    }
}