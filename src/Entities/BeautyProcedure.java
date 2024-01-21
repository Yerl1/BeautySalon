package Entities;

public class BeautyProcedure{
    private String name_of_the_procedure;
    private int price;
    private String description;
    public BeautyProcedure(String name_of_the_procedure, int price, String description) {
        this.name_of_the_procedure = name_of_the_procedure;
        this.price = price;
        this.description = description;
    }

    public void setName_of_the_procedure(String name_of_the_procedure) {
        this.name_of_the_procedure = name_of_the_procedure;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    ///Getters

    public String getProcedureName() {
        return name_of_the_procedure;
    }
    public int getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
}
