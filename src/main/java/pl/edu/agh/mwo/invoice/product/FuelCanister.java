package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class FuelCanister extends Product {

    public FuelCanister(String name, BigDecimal price, BigDecimal tax, boolean isMothderOfTheLawDay) {
        super(name, price.add(BigDecimal.valueOf(5.56)), tax, isMothderOfTheLawDay);
    }
}
