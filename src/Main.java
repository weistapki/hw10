
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Book", 350.0, true, LocalDate.of(2023, 3, 15)));
        productList.add(new Product("Book", 180.0, false, LocalDate.of(2022, 6, 10)));
        productList.add(new Product("Book", 70.0, true, LocalDate.of(2023, 4, 5)));
        productList.add(new Product("Electronics", 520.0, false, LocalDate.of(2023, 2, 20)));
        productList.add(new Product("Clothing", 60.0, true, LocalDate.of(2023, 1, 10)));


        System.out.println("The total value of eligible products whose category:");
        System.out.println(getExpensiveBooks(productList));
        System.out.println();
        System.out.println("Book products with discount:");
        System.out.println(getBookProductsWithDiscount(productList));
        System.out.println();
        System.out.println("The cheapest product from the category \"Book\":");
        System.out.println(getCheapestBook(productList));
        System.out.println();
        System.out.println("Get the last three products added:");
        System.out.println(getLastThreeAddedProducts(productList));
        System.out.println();
        System.out.println("Calculation of the total cost of products:");
        System.out.printf("1)added during the current year\n2)type \"Book\"\n3)price does not exceed 75%n");
        System.out.println("----------------");
        System.out.println(calculateTotalPriceOfBooks(productList));
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
    }

    public static Product getCheapestBook(List<Product> products) {
        return products.stream()
                .filter(p -> p.getType().equals("Book"))
                .min(Comparator.comparingDouble(Product::getPrice))
                .orElseThrow(() -> new RuntimeException("Product [Category: Book] not found"));
    }
    public static List<Product> getLastThreeAddedProducts(List<Product> productList) {
        return productList.stream()
                .sorted(Comparator.comparing(Product::getDateAdded).reversed()) // Сортировка по дате в обратном порядке
                .limit(3) // Ограничение до трех элементов
                .toList();
    }
    public static double calculateTotalPriceOfBooks(List<Product> productList) {
        LocalDate currentDate = LocalDate.now();
        return productList.stream()
                .filter(p -> p.getType().equals("Book"))
                .filter(p -> p.getDateAdded().getYear() == currentDate.getYear())
                .filter(p -> p.getPrice() <= 75)
                .mapToDouble(Product::getPrice)
                .sum();
    }
}
