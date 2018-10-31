package tools.models;

public class Row {

    Long id;
    String incident;

    public Row(Long id, String incident) {
        this.id = id;
        this.incident = incident;
    }

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
}