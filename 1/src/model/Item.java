package model;

public class Item {
    String[] itemType = {"RAW", "MANUFACTURED", "IMPORTED"};
    private String name;
    private String type;
    private double price;
    private int quantity;
    protected double tax;

    void getTax(double price) {
    }

    public double returnTaxPerItem(double price) {
        getTax(price);
        return tax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTax() {
        return tax;
    }

    public void getTaxForParticularType(int i) {
        setType(i);
        ItemFactory itF = new ItemFactory();
        Item it = itF.returnItemType(i);
        tax = it.returnTaxPerItem(price);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private double getFinalPricePerItem() {
        return price + tax;
    }

    private double getTotalPrice() {
        return getFinalPricePerItem() * quantity;
    }

    private void setType(int i) {
        type = itemType[i - 1];
    }

    public void printData() {
        System.out.println("\t*************************************************");
        System.out.println("\t\tName:             " + name +
                "\n\t\tType:             " + type +
                "\n\t\tPrice:            " + price +
                "\n\t\tQuantity:         " + quantity +
                "\n\t\tSales Tax:        " + tax +
                "\n\t\tFinal Price/item: " + getFinalPricePerItem() +
                "\n\t\tTotal Price:      " + getTotalPrice());
        System.out.println("\t*************************************************");
    }
}

class RawItem extends Item {
    public void getTax(double price) {
        tax = price * 0.125;
    }
}

class ManufacturedItem extends Item {
    public void getTax(double price) {
        tax = price * 0.125 + 0.02 * (price + price * 0.125);
    }
}

class ImportedItem extends Item {
    public void getTax(double price) {
        double surcharge;
        double impDuty = 0.10 * price;
        double finalCost = impDuty + price;
        if (finalCost <= 100)
            surcharge = 5;
        else if (finalCost > 100 && finalCost <= 200)
            surcharge = 10;
        else
            surcharge = 0.05 * finalCost;
        tax = surcharge + impDuty;
    }
}
