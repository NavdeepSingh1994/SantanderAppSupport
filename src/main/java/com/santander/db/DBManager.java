package com.santander.db;

import com.santander.model.Incident;

import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private static final String DB_URL;

    static {
        try {
            URL resourceUrl = DBManager.class.getClassLoader().getResource("incidents.db");
            if (resourceUrl == null) {
                throw new RuntimeException("Datenbank incidents.db nicht gefunden im resources-Ordner.");
            }
            String decodedPath = URLDecoder.decode(resourceUrl.getPath(), StandardCharsets.UTF_8);
            DB_URL = "jdbc:sqlite:" + decodedPath;
        } catch (Exception e) {
            throw new RuntimeException("Fehler beim Ermitteln der Datenbank-URL: " + e.getMessage(), e);
        }
    }

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static List<Incident> getAllIncidents() {
        List<Incident> incidents = new ArrayList<>();

        String sql = "SELECT id, title, priority, team, resolution_time, sla_threshold, status FROM incidents";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Incident i = new Incident(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("priority"),
                        rs.getString("team"),
                        rs.getInt("resolution_time"),
                        rs.getInt("sla_threshold"),
                        rs.getString("status")
                );
                incidents.add(i);
            }

        } catch (SQLException e) {
            System.err.println("Fehler beim Laden der Datenbank: " + e.getMessage());
        }

        return incidents;
    }
}
