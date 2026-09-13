package service;

import model.ProductSale;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SalesReportServiceTest {

    private final SalesReportService service =  new SalesReportService();

    @Test
    void shouldCalculateRevenueCorrectly() {
        ProductSale sale = new ProductSale("P001", "Wireless Mouse", "Electronics", 12, 25.50);
        assertEquals(306.00, service.calculateRevenue(sale));
    }

    @Test
    void shouldFindBestSellingProduct() {
        ProductSale mouse = new ProductSale("P001", "Wireless Mouse", "Electronics", 12, 25.50);
        ProductSale pen = new ProductSale("P004", "Ballpoint Pen", "Stationery", 100, 0.50);
        ProductSale result = service.findBestSellingProduct(List.of(mouse, pen));
        assertEquals("Ballpoint Pen", result.getProductName());
        assertEquals(100, result.getQuantitySold());
    }

    @Test
    void shouldFindHighestRevenueProduct() {
        ProductSale mouse = new ProductSale("P001", "Wireless Mouse", "Electronics", 12, 25.50);
        ProductSale pen = new ProductSale("P004", "Ballpoint Pen", "Stationery", 100, 0.50);
        ProductSale result = service.findHighestRevenueProduct(List.of(mouse, pen));
        assertEquals("Wireless Mouse", result.getProductName());
        assertEquals(306.00, result.getTotalRevenue());
    }

    @Test
    void shouldCalculateTotalRevenueCorrectly() {
        ProductSale mouse = new ProductSale("P001", "Wireless Mouse", "Electronics", 12, 25.50);
        ProductSale pen = new ProductSale("P004", "Ballpoint Pen", "Stationery", 100, 0.50);
        assertEquals(356.00, service.calculateTotalRevenue(List.of(mouse, pen)));
    }
}