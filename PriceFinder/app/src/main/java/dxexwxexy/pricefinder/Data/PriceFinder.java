package dxexwxexy.pricefinder.Data;

public class PriceFinder {

    private String itemName;

    PriceFinder(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Simulates a price generation using a range and an initial price.
     * @param initial Initial price of the item.
     * @return Randomized price from range.
     */
    public double updatePrice(double initial) {
        return Math.random() * ((initial*2 - initial/2) + 1) + (initial/2);
    }
}
