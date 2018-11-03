package tools.models;

public class InsidentSendModel {

    private Long id;
    private String name;
    private String description;
    private int dangerLevel;
    private String atachment;

    //input
    private Long vehicleTypeId;
    private Long incidentTypeId;
    private Long areaId;
    //input


    public InsidentSendModel(String name, String description, int dangerLevel, String atachment, Long vehicleTypeId, Long incidentTypeId, Long areaId) {
        this.name = name;
        this.description = description;
        this.dangerLevel = dangerLevel;
        this.atachment = atachment;
        this.vehicleTypeId = vehicleTypeId;
        this.incidentTypeId = incidentTypeId;
        this.areaId = areaId;
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

    public String getAtachment() {
        return atachment;
    }

    public void setAtachment(String atachment) {
        this.atachment = atachment;
    }

    public Long getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Long vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public Long getIncidentTypeId() {
        return incidentTypeId;
    }

    public void setIncidentTypeId(Long incidentTypeId) {
        this.incidentTypeId = incidentTypeId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }
}