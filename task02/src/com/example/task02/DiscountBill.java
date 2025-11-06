package com.example.task02;

/**
 * Счет с поддержкой скидки.
 */
public class DiscountBill extends Bill {

    private double discountPercent;

    public DiscountBill(double discountPercent) {
        setDiscountPercent(discountPercent);
    }

    public void setDiscountPercent(double discountPercent) {
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be in range [0;100]");
        }
        this.discountPercent = discountPercent;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public long getDiscountAmount() {
        long fullPrice = super.getPrice();
        return Math.round(fullPrice * (discountPercent / 100.0));
    }

    @Override
    public long getPrice() {
        long fullPrice = super.getPrice();
        return fullPrice - getDiscountAmount();
    }
}
