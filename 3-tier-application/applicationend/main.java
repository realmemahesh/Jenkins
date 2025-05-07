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
    String result = "";
    try {
      Connection conn = DriverManager.getConnection(
        "jdbc:mysql://mysql.backend.svc.cluster.local:3306/appdb", "root", "rootpass");
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM users");
      while(rs.next()) {
        result += rs.getInt("id") + ": " + rs.getString("name") + "<br/>";
      }
      conn.close();
    } catch (Exception e) {
      result = "DB Error: " + e.getMessage();
    }
    return "<h1>Application Tier (Java)</h1>" + result;
  }

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}
