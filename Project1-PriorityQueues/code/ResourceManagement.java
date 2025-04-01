import java.io.File;
import java.util.*;

/* ResourceManagement
 *
 * Stores the information needed to decide which items to purchase for the given budget and departments
 */
public class ResourceManagement {
  private PriorityQueue<Department> departmentPQ; /* priority queue of departments */
  private Double remainingBudget;                 /* the budget left after purchases are made (should be 0 after the constructor runs) */
  private Double budget;                          /* the total budget allocated */
  
  /* COMPLETED-TODO
   * Fill in your name in the function below
   */  
  public static void printName() {
    /* COMPLETED-TODO : Fill in your name */
    System.out.println("This solution was completed by:");
    System.out.println("Joseph D. Galloway II");
    System.out.println("N/A");
  }

  /* Constructor for a ResourceManagement object
   * COMPLETED-TODO
   * Simulates the algorithm from the pdf to determine what items are purchased
   * for the given budget and department item lists.
   */

  /* Simulate the algorithm for picking the items to purchase */
  /* Be sure to print the items out as you purchase them */
  /* Here's the part of the code I used for printing prices as items */
  //String price = String.format("$%.2f", /*Item's price*/ );
  //System.out.printf("Department of %-30s- %-30s- %30s\n", /*Department's name*/, /*Item's name*/, price );

  public ResourceManagement(String fileNames[], Double budget) {
    departmentPQ = new PriorityQueue<Department>();  // Create priority queue

    remainingBudget = budget;
    this.budget = budget;

    /* Create a department for each file listed in fileNames */
    // Loop through filenames array to create each department then add to priority queue
    for (String fileName : fileNames) { 
      Department dept = new Department(fileName);
      departmentPQ.add(dept);       
    }

    // Print header for purchased items
    System.out.println("ITEMS PURCHASED");
    System.out.println("-------------------------------------");

    Department currentDept = null;
    while (remainingBudget > 0.0) { 
      // Remove and save the front department of the priority queue
      currentDept = departmentPQ.poll();
      //System.out.printf("Removed department %s with priority %.2f\n", currentDept.getDepartmentName(), currentDept.getPriority());

      // Get the current desired item from the department to analyze
      Item currentItem = currentDept.getNextDesiredItem();

      // While the current department has items desired, but can't afford it
      // Then remove from dept's itemsDesired queue and place in itemsRemoved
      while (currentItem != null && currentItem.getPrice() > remainingBudget) { 
        currentDept.addItemToItemsRemoved(currentItem);
        currentItem = currentDept.getNextDesiredItem();
      }

      // Purchase an item that is affordable
      if (currentItem != null) {
        currentDept.addItemToItemsReceived(currentItem);
        currentDept.increasePriority(currentItem.getPrice());
        String price = String.format("$%.2f", currentItem.getPrice());
        System.out.printf("Department of %-30s- %-30s- %30s\n", currentDept.getDepartmentName(), currentItem.getName(), price);
        remainingBudget -= currentItem.getPrice();
      }

      // If the current desired item is null, then the dept. doesn't desire any items
      // Give dept a scholarship or the remaining budget, whichever is less
      else {
        if (remainingBudget > 1000.00) {
          // Scholarship is smaller so give the dept the scholarship
          Item scholarship = new Item("Scholarship", 1000.00);
          currentItem = scholarship;
          currentDept.addItemToItemsReceived(scholarship);
          currentDept.increasePriority(1000.00);
          remainingBudget -= 1000.00;
          String price = String.format("$%.2f", currentItem.getPrice());
          System.out.printf("Department of %-30s- %-30s- %30s\n", currentDept.getDepartmentName(), currentItem.getName(), price);
        }
        else {
          Item remainingBudgetItem = new Item("Scholarship", remainingBudget);
          currentItem = remainingBudgetItem; 
          currentDept.addItemToItemsReceived(remainingBudgetItem);
          currentDept.increasePriority(remainingBudget);
          remainingBudget -= remainingBudget;
          String price = String.format("$%.2f", currentItem.getPrice());
          System.out.printf("Department of %-30s- %-30s- %30s\n", currentDept.getDepartmentName(), currentItem.getName(), price);
        }
      }

      // Add current department back to the priority queue
      departmentPQ.add(currentDept);
    }
  } 

  /* printSummary
   * TODO
   * Print a summary of what each department received and did not receive.
   * Be sure to also print remaining items in each itemsDesired Queue.
   */      
  public void printSummary() {
    
    /* Here's the part of the code I used for printing prices */
    //String price = String.format("$%.2f", /*Item's price*/ );
    //System.out.printf("%-30s - %30s\n", /*Item's name*/, price );
    System.out.println();
    for (Department dept : departmentPQ) {
      System.out.println("Department of " + dept.getDepartmentName());
      System.out.printf("Total Spent       = $%.2f\n", dept.getPriority());
      System.out.printf("Percent of Budget = %.2f%%\n", (dept.getPriority() / budget)*100);
      System.out.println("-------------------------------------");

      // Print items received
      System.out.println("ITEMS RECEIVED");
      String price = "";
      for (Item item : dept.getReceivedItems()) {
        price = String.format("$%.2f", item.getPrice());
        System.out.printf("%-30s - %30s\n", item.getName(), price);
      }
      System.out.println();

      // TODO: Print items removed and items still on the desired list
      System.out.println("ITEMS NOT RECEIVED");
      for (Item item : dept.getRemovedItems()) {
        price = String.format("$%.2f", item.getPrice());
        System.out.printf("%-30s - %30s\n", item.getName(), price);
      }

      for (Item item : dept.getDesiredItems()) {
        price = String.format("$%.2f", item.getPrice());
        System.out.printf("%-30s - %30s\n", item.getName(), price);
      }

      System.out.println();
    }
    
  }   
}

















/* Department
 *
 * Stores the information associated with a Department at the university
 */
class Department implements Comparable<Department> {
  String name;                /* name of this department */
  Double priority;            /* total money spent on this department */
  Queue<Item> itemsDesired;   /* list of items this department wants */
  Queue<Item> itemsReceived;  /* list of items this department received */
  Queue<Item> itemsRemoved;   /* list of items that were skipped because they exceeded the remaining budget */

  /* COMPLETED-TODO
   * Constructor to build a Department from the information in the given fileName
   */
  public Department(String fileName){
    // Implement queues using Linked Lists
    itemsDesired = new LinkedList<>();  // Items wanted by department
    itemsReceived = new LinkedList<>();  // Items purchased and given to department
    itemsRemoved = new LinkedList<>();  // Items rejected for purchase

    // Open the fileName
    File file = new File(fileName);
    Scanner input;
    try {
      input = new Scanner(file);
      this.name = input.next();  // Assign department name
      System.out.println(this.name);

      // Create items based on the contents in the file
      while (input.hasNextLine()) { 
        System.out.println("Derp");
        String currentString = input.next();
        System.out.println(currentString);

        if (currentString.isEmpty()) {
          continue;  // Skip empty lines
        }
        
        String currentItemName = currentString;
        Double currentItemPrice = input.nextDouble();
        System.out.println(currentItemPrice);
        Item item = new Item(currentItemName, currentItemPrice);

        // Add items to itemsDesired
        itemsDesired.add(item);
        System.out.println("Merp");
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("The file %s was not found");
    }


    // Initialize priority for department to zero
    this.priority = 0.0;
  }
  
  /*
   * Compares the data in the given Department to the data in this Department
   * Returns -1 if this Department comes first
   * Returns 0 if these Departments have equal priority
   * Returns 1 if the given Department comes first
   *
   * This function is to ensure the departments are sorted by the priority when put in the priority queue 
   */
  public int compareTo( Department dept ){
    return this.priority.compareTo( dept.priority );
  }

  public boolean equals( Department dept ){
    return this.name.compareTo( dept.name ) == 0;
  }

  @Override 
  @SuppressWarnings("unchecked") //Suppresses warning for cast
  public boolean equals(Object aThat) {
    if (this == aThat) //Shortcut the future comparisons if the locations in memory are the same
      return true;
    if (!(aThat instanceof Department))
      return false;
    Department that = (Department)aThat;
    return this.equals( that ); //Use above equals method
  }
  
  @Override
  public int hashCode() {
    return name.hashCode(); /* use the hashCode for data stored in this name */
  }

  /* Debugging tool
   * Converts this Department to a string
   */	
  @Override
  public String toString() {
    return "NAME: " + name + "\nPRIORITY: " + priority + "\nDESIRED: " + itemsDesired + "\nRECEIVED " + itemsReceived + "\nREMOVED " + itemsRemoved + "\n";
  }

  public Queue<Item> getDesiredItems() {
    return this.itemsDesired;
  }

  public Queue<Item> getReceivedItems() {
    return this.itemsReceived;
  }

  public Queue<Item> getRemovedItems() {
    return this.itemsRemoved;
  }

  public Item getNextDesiredItem() {
    return this.itemsDesired.poll();
  }

  public void addItemToItemsRemoved(Item item) {
    this.itemsRemoved.add(item);
  }

  public void addItemToItemsReceived(Item item) {
    this.itemsReceived.add(item);
  }

  public void increasePriority(Double price) {
    this.priority += price;
  }

  public String getDepartmentName() {
    return this.name;
  }

  public Double getPriority() {
    return this.priority;
  }
}




/* Item
 *
 * Stores the information associated with an Item which is desired by a Department
 */
class Item {
  String name;    /* name of this item */
  Double price;   /* price of this item */

  /*
   * Constructor to build a Item
   */
  public Item(String name, Double price) {
    this.name = name;
    this.price = price;
  }

  /* Debugging tool
   * Converts this Item to a string
   */		
  @Override
  public String toString() {
    return "{ " + name + ", " + price + " }";
  }

  public Double getPrice() {
    return this.price;
  }

  public String getName() {
    return this.name;
  }
}
