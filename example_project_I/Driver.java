import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Driver {
  public static void main(String[] args) {
    // Create a priority queue to store department objects
    PriorityQueue<Department> prQueue = new PriorityQueue<Department>();

    // Must establish how to compare objects of a class against each other
    // when adding elements to a priority queue that are not comparable
    // Must make department comparable by implementing Comparable interface

    // Create three different departments
    // Create three different queues to hold desired items and add to each department
    Queue<Item> itemsQ1 = new LinkedList<Item>();
    itemsQ1.add(new Item("Pencil", 6.0));
    itemsQ1.add(new Item("Desk", 50.0));
    itemsQ1.add(new Item("Chair", 55.0));

    Department depA = new Department("History", itemsQ1);
    prQueue.add(depA);

    Queue<Item> itemsQ2 = new LinkedList<Item>();
    itemsQ2.add(new Item("Monitor", 100.0));
    itemsQ2.add(new Item("Laptop", 200.0));
    itemsQ2.add(new Item("Keyboard", 50.0));

    Department depB = new Department("CS", itemsQ2);
    prQueue.add(depB);

    Queue<Item> itemsQ3 = new LinkedList<Item>();
    itemsQ3.add(new Item("Pen", 6.0));
    itemsQ3.add(new Item("Coffee", 50.0));
    itemsQ3.add(new Item("Chair", 55.0));

    Department depC = new Department("Art", itemsQ3);
    prQueue.add(depC);

    //System.out.println(prQueue.size());

    double budget = 300.00;
    double remainingBudget = budget;

    // While the budget > 0, take a department out of the priority queue
    // and analyze its desired items    Department dept = new Department(fileName);


    Department currentDept = null;
    while (remainingBudget > 0) { 
      // Remove one department from the prQueue
      currentDept = prQueue.poll();
      System.out.printf("Removed Department %s with priority %f\n", currentDept.getDepartmentName(), currentDept.getPriority());

      Item currentItem = currentDept.getNextDesiredItem();
      // An item that the dept wants but we can't afford
      while (currentItem != null && currentItem.getPrice() > remainingBudget) {
        currentDept.addItemToCantAfford(currentItem);
        currentItem = currentDept.getNextDesiredItem();
      }

      // An item that is affordable
      if (currentItem != null) {
        currentDept.addItemToPurchased(currentItem);
        currentDept.increaseBudgetSpent(currentItem.getPrice());
        currentDept.increasePriority(currentItem.getPrice());
        System.out.printf("Department %s, purchased %s, with price %f\n", currentDept.getDepartmentName(), currentItem.getName(), currentItem.getPrice());
        remainingBudget -= currentItem.getPrice();
      }
      // If the current desired item is null, then the dept doesn't desire any items
      // Give dept a scholarship
      else {
        // Create a new item object called scholarship
        // Its price will be min(remainingBudget, 1000.0)
        // add this scholarship item to the purchasedItems queue of the current dept
        // decrease the remaining budget
        double min = remainingBudget;
        if (remainingBudget > 1000.0) {
          min = 1000.0;
        }

        remainingBudget -= min;
      }

      // Add department being analyzed back into the priority queue
      prQueue.add(currentDept);
      System.out.printf("Remaining Budget: %f\n", remainingBudget);
    }

  }
}