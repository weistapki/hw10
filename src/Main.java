import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Book", 350.0));
        productList.add(new Product("Book", 180.0));
        productList.add(new Product("Book", 70.0));
        productList.add(new Product("Electronics", 520.0));
        productList.add(new Product("Clothing", 60.0));

        System.out.println("The total value of eligible products whose category:");
        System.out.println(getExpensiveBooks(productList));
    }
    public static List<Product> getExpensiveBooks(List<Product> productList) {
        return productList.stream()
                .filter(p -> p.getType().equals("Book"))
                .filter(p -> p.getPrice() > 250)
                .toList();
    }
}
