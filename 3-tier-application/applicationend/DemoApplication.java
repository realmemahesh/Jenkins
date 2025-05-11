package com.example.demo;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@SpringBootApplication
@RestController
public class DemoApplication {

    @GetMapping("/")
    String home() {
        StringBuilder result = new StringBuilder();
        result.append("<h1>Application Tier (Java)</h1>");

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://mysql.backend.svc.cluster.local:3306/appdb", "root", "rootpass");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {

            while (rs.next()) {
                result.append(rs.getInt("id")).append(": ").append(rs.getString("name")).append("<br/>");
            }

        } catch (SQLException e) {
            result.append("<p><b>DB Error:</b> ").append(e.getMessage()).append("</p>");
        }

        return result.toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
