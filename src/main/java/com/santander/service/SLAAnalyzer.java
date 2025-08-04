package com.santander.service;

import com.santander.model.Incident;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class SLAAnalyzer {

    public static void printSLAReport(List<Incident> incidents) {
        int total = incidents.size();
        int violations = 0;
        Map<String, Integer> teamViolations = new HashMap<>();

        for (Incident i : incidents) {
            if (i.getResolutionTime() > i.getSlaThreshold()) {
                violations++;
                teamViolations.put(i.getTeam(), teamViolations.getOrDefault(i.getTeam(), 0) + 1);
            }
        }

        System.out.println("\n--- SLA REPORT ---");
        System.out.println("Total Incidents: " + total);
        System.out.println("SLA Violations: " + violations);
        System.out.printf("Compliance: %.2f %%\n", 100.0 * (total - violations) / total);
        System.out.println("\nViolations by Team:");
        for (Map.Entry<String, Integer> entry : teamViolations.entrySet()) {
            System.out.println("- " + entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void printTopPriorities(List<Incident> incidents) {
        Map<String, Integer> priorityCount = new HashMap<>();

        for (Incident i : incidents) {
            priorityCount.put(i.getPriority(), priorityCount.getOrDefault(i.getPriority(), 0) + 1);
        }

        System.out.println("\n--- INCIDENTS BY PRIORITY ---");
        for (Map.Entry<String, Integer> entry : priorityCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
