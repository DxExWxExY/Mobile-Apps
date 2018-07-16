package dxexwxexy.pricefinder.Data;

public class PriceFinder {

    private String itemName;

    PriceFinder(String itemName) {
        this.itemName = itemName;
    }

    public double updatePrice(double initial) {
        return Math.random() * ((initial*2 - initial/2) + 1) + (initial/2);
    }
}
