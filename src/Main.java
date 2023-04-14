
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Book", 350.0, true));
        productList.add(new Product("Book", 180.0, false));
        productList.add(new Product("Book", 70.0, true));
        productList.add(new Product("Electronics", 520.0, false));
        productList.add(new Product("Clothing", 60.0, true));


        System.out.println("The total value of eligible products whose category:");
        System.out.println(getExpensiveBooks(productList));
        System.out.println();
        System.out.println("Book products with discount:");
        System.out.println(getBookProductsWithDiscount(productList));
    }
    public static List<Product> getExpensiveBooks(List<Product> productList) {
        return productList.stream()
                .filter(p -> p.getType().equals("Book"))
                .filter(p -> p.getPrice() > 250)
                .toList();
    }
    public static List<Product> getBookProductsWithDiscount(List<Product> productList) {
        return productList.stream()
                .filter(p -> p.getType().equals("Book"))
                .filter(Product::isDiscount)
                .peek(p -> {
                    p.setPrice(p.getPrice() * 0.9);// Применение скидки 10%
                })
                .collect(toList());
    };
}
