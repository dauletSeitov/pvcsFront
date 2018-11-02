package tools.models;

public class Incident {

    private Long id;
    private String name;
    private String description;
    private int dangerLevel;

    public Incident(Long id, String name, String description, int dangerLevel) {
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

    public int getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(int dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    @Override
    public String toString() {
        return "Incident{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dangerLevel=" + dangerLevel +
                '}';
    }
}