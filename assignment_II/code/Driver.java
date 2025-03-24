import java.io.*;
import java.util.*;

public class Driver 
{
  public static void main(String[] args) 
  { 
    System.out.println(  ); 
    ComputePostfixFormula.printName( );
    System.out.println(  );

    //Call student solution for the test files:
    String fileNames[] = {"./InputFiles/evaluatePostfixTestData-Single.txt",
                          "./InputFiles/evaluatePostfixTestData-Multiple.txt",
                          "./InputFiles/evaluatePostfixTestData-Errors.txt"};
    for( int i=0; i<fileNames.length; i++ )
      testFile( fileNames[i] );

    ComputePostfixFormula.printName( );
    System.out.println(  );
  }
  
  public static void testFile( String fileName ) {  
    Queue<String> symbolQueue = new LinkedList<String>();
    boolean correct = true;
    
    System.out.println("----------------------------TESTING with fileName = " + fileName + "----------------------------");
    System.out.println("");
    
    Scanner myReader;
    
    try{
      File myFile = new File(fileName);
      myReader = new Scanner(myFile);
    }
    catch (FileNotFoundException e){
      System.out.println(e);
      System.out.println("");
      return;
    }
      
    //Read boolean formulas from file 
    while (myReader.hasNextLine()) {
      String booleanFormula = myReader.nextLine();
      String expectedResult = myReader.nextLine();
      String tokens[] = booleanFormula.split(" ");
      
      symbolQueue.clear();
      for( String token : tokens )
        symbolQueue.add( token );
      
      String computedResult;
      System.out.println("Testing boolean formula: " + booleanFormula);
      try {
        //call student algorithm and save the result value
        computedResult = ComputePostfixFormula.processPostfixBooleanFormula( symbolQueue );  
        if( expectedResult.equals(computedResult) ){ //Compare the student's result to the expectedResult
          System.out.println("Your algorithms correctly returned: " + expectedResult);
          System.out.println("");
        }
        else{
          System.out.println("The correct result is: " + expectedResult + "\nBut your algorithm returned: " + computedResult);
          System.out.println("");
          correct = false;
        }
      }
      catch(IllegalArgumentException e) { //if the student throws an exception, check that expectedResult is "error"
        if( expectedResult.equals("error") ){
          System.out.println("Your algorithms correctly returned: error");
          System.out.println("");
        }
        else{
          System.out.println("The correct result is: " + expectedResult + "\nBut your algorithm returned: error");
          System.out.println("");
          correct = false;
        }
      }
      
    }
    myReader.close();
    
    if( correct==true )
    {
      System.out.printf("All test cases were correct\n\n");
    }
  }
}