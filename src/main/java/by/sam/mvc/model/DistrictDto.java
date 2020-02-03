package by.sam.mvc.model;

public class DistrictDto {

    private int id;
    private String name;

    public DistrictDto() {
    }

    public DistrictDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}