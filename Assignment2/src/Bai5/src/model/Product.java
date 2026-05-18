package Assignment2.src.Bai5.src.model;
import Assignment2.src.Bai5.src.util.ProductValidatation;

public class Product {
    private String productCode;
    private String name;
    private double price;
    private int quantity;
    private static int counter = 1;
    private static int totalProducts = 0;
    private static double totalRevenue = 0;


    public void setName(String name) {
        if(ProductValidatation.isValidName(name)) {
            this.name = name;
        } 
    }

    public void setPrice(double price) {
        if (ProductValidatation.isValidPrice(price)) {
            this.price = price;
        }
    }

    public void setQuantity(int quantity) {
        if (ProductValidatation.isValidQuantity(quantity)) {
            this.quantity = quantity;
        }
    }
 
 
    public Product() {
        this.productCode = "P" + String.format("%04d", counter++);
        this.name = "Unknown";
        this.price = 0;
        this.quantity = 0;
        totalProducts++;
        totalRevenue += price * quantity;
    }
    public Product(String name, double price) {
        this.productCode = "P" + String.format("%04d", counter++);
        this.name = name;
        this.price = price;
        this.quantity = 0;
        totalProducts++;
        totalRevenue += price * quantity;
    }
    public Product(String name, double price, int quantity) {
        this.productCode = "P" + String.format("%04d", counter++);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        totalProducts++;
        totalRevenue += price * quantity;
    }

    public String getProductCode() {
        return productCode;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public static int getTotalProducts() {
        return totalProducts;
    }
    public static double getTotalRevenue() {
        return totalRevenue;
    }
    public int sell(int amount) {
        if (amount > 0 && amount <= quantity) {
            quantity -= amount;
            double saleAmount = price * amount;
            totalRevenue += saleAmount;
            return amount;
        }
        return 0; 
    }
    public int restock(int amount) {
        if (amount > 0) {
            quantity += amount;
            return amount;
        }
        return 0; 
    }
    public void displayInfo() {
        System.out.println("Product Code: " + getProductCode() + " - Name: " + getName() + " - Price: $" + getPrice() + " - Quantity: " + getQuantity()); 
    }
    public void applyPromotion(double discountPercent) {
        if (discountPercent > 0 && discountPercent < 100) {
            double discountAmount = discountPercent / 100;
            totalRevenue *= (1 - discountAmount);
        }
    }
     public static void applyGlobalPromotion(Product[] products, double discountPercent) {
        if (discountPercent > 0 && discountPercent < 100) {
            double discountAmount = discountPercent / 100;
            totalRevenue *= (1 - discountAmount);
        }
    }
}


