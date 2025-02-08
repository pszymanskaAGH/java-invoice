package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.*;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
//    private List<Product> products = new ArrayList<>();
    private Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException();
        }
        products.put(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException();
        }
//        for (int i = 0; i < quantity ; i++) {
            products.put(product, quantity);
//        }
    }

    public BigDecimal getSubtotal() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            sum = sum.add(product.getPrice().multiply(new BigDecimal(products.get(product))));
        }
        return sum;
    }

    public BigDecimal getTax() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            sum = sum.add(product.getTaxPercent().multiply(product.getPrice()));
        }
        return sum;
    }

    public BigDecimal getTotal() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal priceWithTax = product.getPrice().multiply(product.getTaxPercent());
            BigDecimal totalPrice = product.getPrice().add(priceWithTax);
            sum = sum.add(totalPrice.multiply(BigDecimal.valueOf(products.get(product))));
        }
        return sum;
    }
}
