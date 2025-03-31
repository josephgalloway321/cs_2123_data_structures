import java.util.LinkedList;
import java.util.Queue;

public class Department implements Comparable<Department> {
  private String name;
  private Queue<Item> desiredItems = new LinkedList<>();  // Implement queue using linkedlist
  private Queue<Item> cantAffordItems = new LinkedList<>();  // Items department cannot afford
  private Queue<Item> purchasedItems = new LinkedList<>();  // Purchased items by department

  private Double budgetSpent;
  private Double priority;

  public Department(String name, Queue<Item> desiredItem) {
    this.name = name;
    //this.desiredItems = desiredItem;
    this.budgetSpent = 0.0;
    this.priority = 0.0;  // All created departments have priority of 0
  }

  public Double getPriority() {
    return this.priority;
  }

  public Item getNextDesiredItem() {
    return this.desiredItems.poll();
  }

  public Queue<Item> getDesiredItems() {
    return this.desiredItems;
  }

  public void addItemToCantAfford(Item item) {
    this.cantAffordItems.add(item);
  }

  public void addItemToPurchased(Item item) {
    this.purchasedItems.add(item);
  }

  public void increaseBudgetSpent(Double price) {
    this.budgetSpent += price;
  }

  public void increasePriority(Double price) {
    this.priority += price;
  }

  public String getDepartmentName() {
    return this.name;
  }

  // Compare this object of Department with the given object o of department 
  // using priortiy
  @Override
  public int compareTo(Department o) {
      return this.priority.compareTo(o.getPriority());
  }

}