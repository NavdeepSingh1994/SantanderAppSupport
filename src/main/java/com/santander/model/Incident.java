package com.santander.model;

public class Incident {
    private int id;
    private String title;
    private String priority;
    private String team;
    private int resolutionTime; // Minuten
    private int slaThreshold;   // Minuten
    private String status;

    public Incident(int id, String title, String priority, String team, int resolutionTime, int slaThreshold, String status) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.team = team;
        this.resolutionTime = resolutionTime;
        this.slaThreshold = slaThreshold;
        this.status = status;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getPriority() { return priority; }
    public String getTeam() { return team; }
    public int getResolutionTime() { return resolutionTime; }
    public int getSlaThreshold() { return slaThreshold; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "[" + id + "] " + title + " | Priority: " + priority + " | Team: " + team +
                " | Resolution: " + resolutionTime + "min | SLA: " + slaThreshold + "min | Status: " + status;
    }
}
