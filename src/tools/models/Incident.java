package tools.models;

public class Incident {

    private Long id;
    private String name;
    private String description;
    private int dangerLevel;
    private String incidentType;
    private String vehicleType;
    private String atachment;

    public Incident(Long id, String name, String description, int dangerLevel, String incidentType, String vehicleType, String atachment) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dangerLevel = dangerLevel;
        this.incidentType = incidentType;
        this.vehicleType = vehicleType;
        this.atachment = atachment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(int dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getAtachment() {
        return atachment;
    }

    public void setAtachment(String atachment) {
        this.atachment = atachment;
    }
}
