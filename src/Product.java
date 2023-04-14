import java.time.LocalDate;

public class Product {
    private String type;
    private double price;
    private boolean discount;
    private LocalDate dateAdded;

    public Product(String type, double price, boolean discount, LocalDate dateAdded) {
        this.type = type;
        this.price = price;
        this.discount = discount;
        this.dateAdded = dateAdded;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean isDiscount() {
        return discount;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    @Override
    public String toString() {
        return "Product{" +
                "type='" + type + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", dateAdded=" + dateAdded +
                '}';
    }
}
