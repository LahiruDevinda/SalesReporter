package io;

import model.ProductSale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvSalesReader {
    public List<ProductSale> read(String filePath) throws IOException {
        List<ProductSale> sales = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                if (isHeader) { isHeader = false; continue; }
                String[] columns = line.split(",");
                if (columns.length < 5) throw new IOException("Invalid CSV row: missing columns.");
                sales.add(new ProductSale(
                        columns[0].trim(), columns[1].trim(), columns[2].trim(),
                        Integer.parseInt(columns[3].trim()), Double.parseDouble(columns[4].trim())
                ));
            }
        }
        return sales;
    }
}
