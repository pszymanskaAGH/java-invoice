package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class BottleOfWine extends Product {
    public BottleOfWine(String name, BigDecimal price, BigDecimal tax) {
        super(name, price.add(BigDecimal.valueOf(5.56)), tax);
    }

}
