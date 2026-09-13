package report;

import model.ProductSale;
import service.SalesReportService;

import java.util.List;
import java.util.Map;

public class SalesReportGenerator {

    private final SalesReportService reportService;

    public SalesReportGenerator(SalesReportService reportService) {
        this.reportService = reportService;
    }

    public String generate(List<ProductSale> productSales) {
        if (productSales == null || productSales.isEmpty()) return "Error: CSV file contains no data.";
        StringBuilder report = new StringBuilder();
        report.append("============================================\n");
        report.append("PRODUCT SALES SUMMARY REPORT\n");
        report.append("============================================\n\n");
        report.append("--- Revenue Per Product ---\n");
        for (ProductSale productSale : productSales) {
            report.append(String.format("%-6s %-18s %-12s LKR %.2f%n",
                    productSale.getProductId(), productSale.getProductName(), productSale.getCategory(), reportService.calculateRevenue(productSale)));
        }
        report.append("\n--- Revenue Per Category ---\n");
        Map<String, Double> categoryRevenue = reportService.calculateCategoryRevenue(productSales);
        for (Map.Entry<String, Double> entry : categoryRevenue.entrySet()) {
            report.append(String.format("%-15s : LKR %.2f%n", entry.getKey(), entry.getValue()));
        }
        ProductSale bestSeller = reportService.findBestSellingProduct(productSales);
        ProductSale highestRevenue = reportService.findHighestRevenueProduct(productSales);
        report.append("\n--- Highlights ---\n");
        report.append(String.format("Best-Selling Product   : %s (%d units)%n", bestSeller.getProductName(), bestSeller.getQuantitySold()));
        report.append(String.format("Highest Revenue        : %s (LKR %.2f)%n", highestRevenue.getProductName(), highestRevenue.getTotalRevenue()));
        report.append(String.format("Grand Total Revenue    : LKR %.2f%n", reportService.calculateTotalRevenue(productSales)));
        report.append("============================================\n");
        return report.toString();
    }

}
