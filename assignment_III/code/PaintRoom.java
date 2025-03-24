import java.io.*;
import java.util.*;

/* PaintRoom
 */
public class PaintRoom { 
  /* TODO
   * printName
   * input: none
   * output: none
   *
   * Prints name the student who worked on this solution
   */
  public static void printName( )
  {
    /* TODO : Fill in your name*/
    System.out.printf("\nThis solution was completed by:\n");
    System.out.printf("<student name>\n");
  }

  /* TODO
   * initialPaintRoom
   * input: the room to process
   * output: N/A
   *
   * This non-recursive function is called by the driver and it makes the initial call to recursive function recPaintRoom.
   */
  public static void initialPaintRoom( char room[][] ) {

    /* Find the location of 'A' in the room */
    /* HINT: room.length is the number of rows and room[i].length is the number of columns in the ith row */


    /* Call your recursive function here */
    //recPaintRoom( room, /* initial row value */, /* initial col value */, /* initial value for distanceFromA */  );

  }
  
  /* TODO
   * recPaintRoom
   * input: the room to process, the row and column of the current location being explored, the distance traveled from 'A'
   * output: N/A
   */
  public static void recPaintRoom( char room[][], int row, int col, int distanceFromA /* Feel free to add more inputs to your recursive function */ ) {
    /* Base case: */

    /* Recursive case: */    
    
  }
}
