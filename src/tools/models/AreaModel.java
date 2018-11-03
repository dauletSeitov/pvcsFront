package tools.models;

public class AreaModel {

    private Long id;
    private String name;
    private String description;
    private Integer dangerLevel;

    public AreaModel(Long id, String name, String description, Integer dangerLevel) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dangerLevel = dangerLevel;
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

    public Integer getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(Integer dangerLevel) {
        this.dangerLevel = dangerLevel;
    }
}
