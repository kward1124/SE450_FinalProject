package finalproject;

public class Tools implements Product{
    private String name;
    private double price;
    private final String category = "Tools";

    public Tools(String name, double price){
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
