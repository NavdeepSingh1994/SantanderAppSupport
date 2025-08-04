package com.santander;

import com.santander.db.DBManager;
import com.santander.model.Incident;
import com.santander.service.SLAAnalyzer;

import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("📊 Santander Incident Support Dashboard\n");

        List<Incident> incidents = DBManager.getAllIncidents();

        System.out.println("--- INCIDENT OVERVIEW ---");
        for (Incident i : incidents) {
            System.out.println(i);
        }

        // SLA-Auswertung
        SLAAnalyzer.printSLAReport(incidents);

        // Prioritäten
        SLAAnalyzer.printTopPriorities(incidents);
    }
}
