package model;

public class Driver {
    private String id;
    private String name;
    private double proximity;
    private int experience;
    private int delays;
    private int infractions;
    private boolean isAvailable;

    public Driver(String id, String name, double proximity, int experience) {
        this.id = id;
        this.name = name;
        this.proximity = proximity;
        this.experience = experience;
        this.delays = 0;
        this.infractions = 0;
        this.isAvailable = true;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getProximity() { return proximity; }
    public int getExperience() { return experience; }
    public int getDelays() { return delays; }
    public int getInfractions() { return infractions; }
    public boolean isAvailable() { return isAvailable; }

    public void setAvailable(boolean isAvailable) { this.isAvailable = isAvailable; }

    public void incrementDelays() { delays++; }
    public void incrementInfractions() { infractions++; }

    @Override
    public String toString() {
        return id + "," + name + "," + proximity + "," + experience + "," + delays + "," + infractions + "," + isAvailable + "\n";
    }
}