package learn.foraging.models;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CategoryValue {

    private Item item;
    private Category category;
    private double kilograms;
    private BigDecimal value;

    public CategoryValue(Item item, Category category, BigDecimal value) {
        this.item = item;
        this.category = category;
        this.value = value;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getKilograms() {
        return kilograms;
    }

    public void setKilograms(double kilograms) {
        this.kilograms = kilograms;
    }

    public BigDecimal getValue() {
        if (item == null || item.getDollarPerKilogram() == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal kilos = new BigDecimal(kilograms).setScale(4, RoundingMode.HALF_UP);
        return item.getDollarPerKilogram().multiply(kilos);
    }
}
