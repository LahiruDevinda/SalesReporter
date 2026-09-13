package service;

import model.ProductSale;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SalesReportService {
    public double calculateRevenue(ProductSale productSale) {
        return productSale.getQuantitySold() * productSale.getUnitPrice();
    }

    public Map<String, Double> calculateCategoryRevenue(List<ProductSale> productSales) {
        Map <String, Double> categoryRevenue = new LinkedHashMap<>();

        for (ProductSale productSale : productSales) {
            double revenue = calculateRevenue(productSale);
            categoryRevenue.put(productSale.getCategory(), categoryRevenue.getOrDefault(productSale.getCategory(), 0.0) + revenue);
        }

        return categoryRevenue;
    }

    public ProductSale findBestSellingProduct(List<ProductSale> productSales) {
        if (productSales.isEmpty()) {
            return null;
        }
        ProductSale bestSellingProduct = productSales.getFirst();
        for (ProductSale productSale : productSales) {
            if (productSale.getQuantitySold() > bestSellingProduct.getQuantitySold()) {
                bestSellingProduct = productSale;
            }
        }
        return bestSellingProduct;
    }

    public ProductSale findHighestRevenueProduct(List<ProductSale> productSales) {
        if (productSales.isEmpty()) {
            return null;
        }
        ProductSale highestRevenueProduct = productSales.getFirst();
        for (ProductSale productSale : productSales) {
            if (calculateRevenue(productSale) > calculateRevenue(highestRevenueProduct)) {
                highestRevenueProduct = productSale;
            }
        }
        return highestRevenueProduct;
    }

    public double calculateTotalRevenue(List<ProductSale> productSales) {
        double totalRevenue = 0;
        if (productSales.isEmpty()) {
            return totalRevenue;
        }

        for (ProductSale productSale : productSales) {
            totalRevenue += calculateRevenue(productSale);
        }
        return totalRevenue;
    }

}
