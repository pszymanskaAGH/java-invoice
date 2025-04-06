package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private int number;
    private Map<Product, Integer> products = new HashMap<>();
    private Map<String, Integer> productQuantity = new HashMap<>();

    public Invoice(int number) {
        this.number = number;
    }

    public Invoice() {

    }

    public int getNumber() {
        return number;
    }

    public String printProducts() {
        StringBuilder builder = new StringBuilder();
        int counter = 0;

        for (Product product : products.keySet()) {
            long amountOfprod = products.keySet().stream().filter(el -> el.getName().equals(product.getName())).count();

            counter++;
            if (productQuantity.containsKey(product.getName())) {
                productQuantity.replace(product.getName(), 0);
            } else {
                productQuantity.put(product.getName(), 1);
                String result = String.format("""
                                Invoice number: %d
                                Product name: %s
                                Product amount: %s
                                Product price: %s
                                """,
                        counter, product.getName(), amountOfprod, product.getPrice());
                builder.append(result).append("\n");
            }
        }
        builder.append("Amount of products in a invoice: ").append(counter);
        return builder.toString();
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        products.put(product, quantity);
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }
}
