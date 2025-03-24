import java.io.*;
import java.util.*;

/************************** DO NOT MODIFY THESE FUNCTIONS BELOW ***************************/

/* Driver
 */
public class Driver {
  public static final String DEFAULT_FILENAMES[] = {
                    "./InputFiles/room-Small01.txt", 
                    "./InputFiles/room-Small02.txt", 
                    "./InputFiles/room-Small03.txt", 
                    "./InputFiles/room-Medium01.txt", 
                    "./InputFiles/room-Medium02.txt", 
                    "./InputFiles/room-Long01.txt", 
                    "./InputFiles/room-Long02.txt", 
                    "./InputFiles/room-Large01.txt", 
                    "./InputFiles/room-Large02.txt"};

  public static void main(String[] args) throws IOException {
    int i;
    
    PaintRoom.printName();

    if (args.length > 0) /* Usage: java Driver <filename> */
    {
      System.out.printf("\nRunning on provided test files:\n\n");
      for (i = 0; i < args.length; i++) 
        testFilename(args[i]);
    } 
    else /* Usage: java Driver */
    {
      System.out.printf("\nRunning default test files:\n\n");
      for (i = 0; i < DEFAULT_FILENAMES.length; i++) 
        testFilename(DEFAULT_FILENAMES[i]);
    }
    
    PaintRoom.printName();
  }
  
  /* testFilename
   * input: a string
   * output: none
   *
   * Calls the student code on the given filename
   */
  public static void testFilename(String filename) {  
    System.out.printf("\n--------------- START OF OUTPUT FOR %s -----------------\n\n", filename );

    char room[][] = read2DArray(filename);
    System.out.printf("Base room: \n\n");
    print2DArray(room);

    PaintRoom.initialPaintRoom(room);

    System.out.printf("\nRoom after algorithm: \n\n");
    print2DArray(room);

    System.out.printf("\n--------------- END OF OUTPUT FOR %s -----------------\n\n", filename );
  }


  // Read in and return room from given file
  public static char[][] read2DArray(String fileName) {
    File file = new File(fileName);
    Scanner input;

    try {
      input = new Scanner(file);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("The file " + fileName + " was not found.");
      return null;
    }

    int rows = input.nextInt();
    int cols = input.nextInt();
    char room[][] = new char[rows][cols];

    for (int i = 0; i < rows; i++) {
      String next = input.next();

      for (int j = 0; j < cols; j++) {
        room[i][j] = next.charAt(j);
      }
    }

    input.close();

    return room;
  }

  // Print given 2D array
  public static void print2DArray(char room[][]) {
    for(int i = 0; i < room.length; i++) {
      for(int j = 0; j < room[i].length; j++) System.out.printf("%c", room[i][j]);
      System.out.printf("\n");
    }
  }
}
