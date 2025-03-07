import java.util.*;
import java.util.Stack;

public class ComputePostfixFormula 
{
  /* TO BE COMPLETED BY YOU
   * Fill in your name in the function below
   */  
  public static void printName( )
  {
    System.out.println("This solution was completed by:");
    System.out.println("Joseph D. Galloway II");
  }
  
  /*
   * Method for processing a boolean formula 
   */
  public static String processPostfixBooleanFormula( Queue<String> symbolQueue ) throws IllegalArgumentException
  {
    /* Use the following to throw an exception if the string is incorrectly formatted: */
    //throw new IllegalArgumentException( "Postfix String is formatted incorrectly" );

    // Step 1
    Stack<String> s = new Stack<String>();
    String computeOp1String = "";  // Use to store for computing operands 1 & 2 whenever needed
    String computeOp2String = "";
    boolean computeOp1Bool;
    boolean computeOp2Bool;
    String computationResult = "";

    // Step 2
    // Step 2.1 (True or false operand)
    for (String token : symbolQueue) {
      if (token.equals("true")) {
        s.push(token);  // Push true to stack
      }
      
      else if (token.equals("false")) {
        s.push(token);  // Push false to stack
      }
      
      // Step 2.2 (Unary operator)
      else if (token.equals("NOT")) {
        // Step 2.2.1, Return an error if stack doesn't have at least one operand
        if (s.empty()) {
          return "error";
        }

        // Step 2.2.2-4, Complete computation using unary operator
        computeOp1Bool = Boolean.valueOf(s.pop());
        computationResult = Boolean.toString(!computeOp1Bool);
        s.push(computationResult);
      }

      // Step 2.3 (Binary operator) 
      else {        
        // Step 2.3.1, Return an error if stack doesn't have at least two operands
        if (s.size() < 2) {
          return "error";  // Stack doesn't have at least two operands
        }

        // Step 2.3.2-5, Complete computation depending on binary operator
        computeOp2String = s.pop();  // Store op2 from top of stack
        computeOp1String = s.pop();  // Store op1 from new top of stack

        if (token.equals("AND")) {
          computeOp2Bool = Boolean.valueOf(computeOp2String);
          computeOp1Bool = Boolean.valueOf(computeOp1String);
          computationResult = Boolean.toString(computeOp1Bool && computeOp2Bool);
          s.push(computationResult);
        }
        else if (token.equals("NAND")) {
          computeOp2Bool = Boolean.valueOf(computeOp2String);
          computeOp1Bool = Boolean.valueOf(computeOp1String);
          computationResult = Boolean.toString(!(computeOp1Bool && computeOp2Bool));
          s.push(computationResult);
        }
        else if (token.equals("OR")) {
          computeOp2Bool = Boolean.valueOf(computeOp2String);
          computeOp1Bool = Boolean.valueOf(computeOp1String);
          computationResult = Boolean.toString(computeOp1Bool || computeOp2Bool);
          s.push(computationResult);
        }
        else if (token.equals("NOR")) {
          computeOp2Bool = Boolean.valueOf(computeOp2String);
          computeOp1Bool = Boolean.valueOf(computeOp1String);
          computationResult = Boolean.toString(!(computeOp1Bool || computeOp2Bool));
          s.push(computationResult);
        }
        else if (token.equals("XOR")) {
          computeOp2Bool = Boolean.valueOf(computeOp2String);
          computeOp1Bool = Boolean.valueOf(computeOp1String);
          computationResult = Boolean.toString(computeOp1Bool != computeOp2Bool);
          s.push(computationResult);
        }
        else if (token.equals("COND")) {
          computeOp2Bool = Boolean.valueOf(computeOp2String);
          computeOp1Bool = Boolean.valueOf(computeOp1String);
          computationResult = Boolean.toString(!computeOp1Bool || computeOp2Bool);
          s.push(computationResult);
        }
        else if (token.equals("BICOND")) {
          computeOp2Bool = Boolean.valueOf(computeOp2String);
          computeOp1Bool = Boolean.valueOf(computeOp1String);
          computationResult = Boolean.toString(computeOp1Bool == computeOp2Bool);
          s.push(computationResult);
        }
      }
    }

    // TODO: Step 3, Check if more than one operand after all tokens evaluated
    // if () {
       //  return something; 
    //}

    // Step 4, Return the only value in the stack
    return s.pop();
  }
 
}

// // EXAMPLE Push items to stack
// System.out.println("__________________________");
// stackTokens.push("operand1");
// stackTokens.push("operand2");
// stackTokens.push("operator");

// // EXAMPLE Printing stack
// System.out.println("Stack => " + stackTokens);
// System.out.println();

// // EXAMPLE Popping items from stack
// String tokenAtTop = stackTokens.pop();
// System.out.println("Stack.pop() => " + tokenAtTop);
// System.out.println("Stack => " + stackTokens);
// System.out.println();

// // EXAMPLE Peek at the item at the top of the stack without removing
// tokenAtTop = stackTokens.peek();
// System.out.println("Stack.peek() => " + tokenAtTop);
// System.out.println("Current Stack => " + stackTokens);
// System.out.println(stackTokens.empty());
// System.out.println("__________________________");

// // EXAMPLE Iterating through queue
// for (String token : symbolQueue) {
    //   System.out.println(token);
    // }
    // System.out.println();