package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainService {

    private final DBService dbService;

    public MainService() {
        dbService = new DBService();
    }

    public void addName(String name, int price, String amount) {
        String sql = "INSERT INTO products (name, price, amount) VALUES (?,?,?)";
        dbService.insert(sql, name, price, amount);
    }

    public void deleteName(int id) {
        String sql = "DELETE FROM products WHERE id =?";
        dbService.delete(sql, id);
    }

    public void editName(int id, String name, int price, String amount) {
        String sql = "UPDATE products SET name =?, price =?, amount =? WHERE id =?";
        dbService.update(sql, name, price, amount, id);
    }

    public List<Map<String, Object>> getNames() throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        String sql = "SELECT * FROM products";
        ResultSet rs = dbService.select(sql);
        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
            row.put("id", rs.getInt("id"));
            row.put("name", rs.getString("name"));
            row.put("price", rs.getInt("price")); // Assuming price is stored as integer
            row.put("amount", rs.getString("amount")); // Assuming amount is stored as varchar
            result.add(row);
        }
        return result;
    }

}