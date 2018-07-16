package dxexwxexy.pricefinder.Data;

import java.util.Locale;

public class Item {
    private String name, url;
    private double initialPrice;
    private double currentPrice;
    private PriceFinder priceFinder;

    public Item(String name, String url, double initialPrice) {
        priceFinder = new PriceFinder(name);
        this.name = name;
        this.url = url;
        this.initialPrice = initialPrice;
        this.currentPrice = initialPrice;
    }

    public String getName() {
        return name;
    }

    public String getInitialPrice() {
        return String.valueOf(initialPrice);
    }

    public String getCurrentPrice() {
        return String.format(Locale.getDefault(), "%.2f", currentPrice);
    }

    public String getDifference() {
        return String.format(Locale.getDefault(), "%.0f", (((currentPrice - initialPrice) / initialPrice ) * 100));
    }

    public void updateCurrentPrice() {
        this.currentPrice = priceFinder.updatePrice(initialPrice);
    }

    public String getURL() {
        return url;
    }

    public String toString() {
        return getName()+","+getURL()+","+getInitialPrice()+","+getCurrentPrice();
    }
}
