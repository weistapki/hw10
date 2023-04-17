
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        System.out.println();
        System.out.println("Grouping objects by product type:");
        printGroupedProducts(productList);
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
                .peek(p -> p.setPrice(p.getPrice() * 0.9))
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
                .sorted(Comparator.comparing(Product::getDateAdded).reversed())
                .limit(3)
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
    public static void printGroupedProducts(List<Product> productList) {
        Map<String, List<String>> groupedProducts = productList.stream()
                .collect(Collectors.groupingBy(Product::getType, Collectors.mapping(p ->
                                "Type: " + p.getType() +
                                        ", Price: " + p.getPrice() +
                                        ", Discount: " + p.isDiscount() +
                                        ", Date Added: " + p.getDateAdded(),
                        Collectors.toList())));

        groupedProducts.forEach((type, products) -> {
            System.out.println(type + ":");
            products.forEach(System.out::println);
        });
    }
}
