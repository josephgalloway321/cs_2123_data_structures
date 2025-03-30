public class Item {
  private String itemName;
  private Double price;  // different than double, this is wrapper class

  public Item(String name, Double price) {
    this.itemName = name;
    this.price = price;
  }

  public String toString() {
    return String.format("Item name: %s, with price %f", this.itemName, this.price);
  }

  public Double getPrice() {
    return this.price;
  }

  public String getName() {
    return this.itemName;
  }

}