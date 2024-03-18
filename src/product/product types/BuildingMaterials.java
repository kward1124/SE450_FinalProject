package finalproject;

public class BuildingMaterials implements Product{

    private String name;
    private double price;
    private final String category = "Building Materials";
    
    public BuildingMaterials(String name, double price){
        this.name = name;
        this.price = price;

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getCategory() {
        return category;
    }

    
}
