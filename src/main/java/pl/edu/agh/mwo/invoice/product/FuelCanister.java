package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class FuelCanister extends Product {
    private static final BigDecimal excise = BigDecimal.valueOf(5.56);

    public FuelCanister(String name, BigDecimal price, BigDecimal tax, boolean isMotherLawDay) {
        super(name, price.add(excise), tax, isMotherLawDay);
    }
}
