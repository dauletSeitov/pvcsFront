package tools.models;

public class Row {

    private Long id;
    private String incident;
    private String vehicle;
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIncident() {
        return incident;
    }

    public void setIncident(String incident) {
        this.incident = incident;
    }

    public Row(Long id, String incident, String vehicle, String description) {
        this.id = id;
        this.incident = incident;
        this.vehicle = vehicle;
        this.description = description;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}