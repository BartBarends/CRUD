package nl.han.aim.oosevt.lamport.controllers.function.dto;

public class FunctionResponseDTO {
    private int id;
    private String name;

    public FunctionResponseDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public FunctionResponseDTO() {
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
