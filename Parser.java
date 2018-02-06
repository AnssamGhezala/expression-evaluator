package assignments2017.A2PostedCode;

/*

 */

import java.util.Stack;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Expression {
  private ArrayList<String> tokenList;

  // Constructor
  /**
   * The constructor takes in an expression as a string and tokenizes it (breaks it up into
   * meaningful units) These tokens are then stored in an array list 'tokenList'.
   */

  Expression(String expressionString) throws IllegalArgumentException {
    tokenList = new ArrayList<String>();

    StringBuilder token = new StringBuilder();

    token.append(expressionString);

    for (int i = 0; i < token.length(); i++) {

      if ("*/[]()".indexOf(token.charAt(i)) != -1) { //if token.charAt(i) is not * / or [] or ()

        tokenList.add(token.substring(i, i + 1)); //returns new String containing the substring from i to i+1, adds it to array list

      } else if ("+-".indexOf(token.charAt(i)) != -1) { //if token.charAt(i) is not + or -
        int j = 0; //declare and initiale variable
        while ("+-".indexOf(token.charAt(i + j)) != -1) {  //while token.charAt(i+j) if not a + or - operator
          j++; //increment j 
        }
        tokenList.add(token.substring(i, i + j));
        i = i + j - 1;

      } else if ("0123456789".indexOf(token.charAt(i)) != -1) { //if token.charAt(i) if not an integer
        int k = 0;//declare and initiale variable
        while ("0123456789".indexOf(token.charAt(i + k)) != -1) { //while token.charAt(i+k) if not an integer
          k++; //increment j 
        }
        tokenList.add(token.substring(i, i + k));
        i = i + k - 1;
      }

    }


  }

  /**
   * This method evaluates the expression and returns the value of the expression Evaluation is done
   * using 2 stack ADTs, operatorStack to store operators and valueStack to store values and
   * intermediate results. - You must fill in code to evaluate an expression using 2 stacks
   */
  public Integer eval() {
    Stack<String> operatorStack = new Stack<String>();
    Stack<Integer> valueStack = new Stack<Integer>();

    // --- BEGIN CODE ----

    for (int i = 0; i < tokenList.size(); i++) {

      String s = tokenList.get(i).toString();

      if (isInteger(s)) { // if tokenList.get(i) is a digit
        Integer x = Integer.valueOf(tokenList.get(i)); // create x of type Integer and assign to it
                                                       // the value of tokenList.get(i)
        valueStack.push(x); // push it in my value stack

      }

      else if ((("+-*/++--]".indexOf(s) != -1))) { // if tokenList.get(i) is an operator
        String x1 = tokenList.get(i); // store it in a string
        operatorStack.push(x1); // push it in my operator stack

      }

      if (s.equals(")")) { // if i get an open bracket :

        if (operatorStack.isEmpty()) { // if i don't have any operators
          continue; // don't do anything
        }
        String op = operatorStack.pop(); // if i have an operator, pop it from stack and store it in
                                         // a string

        if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")) { // if my
                                                                                    // operator is a
                                                                                    // + - * or /

          int value1 = valueStack.pop(); // assign what s popped from my valueStack to the value

          int value2 = valueStack.pop(); // assign what s popped from my valueStack to the value

          int result = 0; // declare and initialize int variable result

          if (op.equals("+")) { // if my operator is a +

            result = value2 + value1; // assign the addition of my two values to result

            valueStack.push(result); // push that result into my stack

            // if operator is a -, assign the subtraction of my two values to result and push result
            // into my stack
          } else if (op.equals("-")) {

            result = value2 - value1;

            valueStack.push(result);

            // if operator is a *, assign the multiplication of my two values to result and push
            // result into my stack
          } else if (op.equals("*")) {

            result = value2 * value1;

            valueStack.push(result);

            // if operator is a /, assign the division of my two values to result and push result
            // into my stack
          } else if (op.equals("/")) {

            result = value2 / value1;

            valueStack.push(result);
          }

        } else if (op.equals("++") || op.equals("--")) { // if my operator if ++ or --

          int valueUnitary1 = valueStack.pop(); // pop valueStack only once and assign what's
                                                // returned to the int variable valueUnitary1

          // if operator ++, push into value stack the increment++ of my value
          if (op.equals("++")) {

            valueStack.push(valueUnitary1 + 1);

            // if operator --, push into value stack the increment-- of my value
          } else if (op.equals("--")) {

            valueStack.push(valueUnitary1 - 1);
          }

        }

        // if my operator is a closed square bracket, pop valueStack only once, and assign what's
        // returned to the int variable valueUnitary2
      } else if (s.equals("]")) {
        int valueUnitary2 = valueStack.pop();

        valueUnitary2 = Math.abs(valueUnitary2); // transform the value into its absolute value

        valueStack.push(valueUnitary2);


      }
    }
    // --- END CODE ----
    
    return valueStack.pop();
  }


  // Helper methods
  /**
   * Helper method to test if a string is an integer Returns true for strings of integers like "456"
   * and false for string of non-integers like "+" - DO NOT EDIT THIS METHOD
   */
  private boolean isInteger(String element) {
    try {
      Integer.valueOf(element);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  /**
   * Method to help print out the expression stored as a list in tokenList. - DO NOT EDIT THIS
   * METHOD
   */

  @Override
  public String toString() {
    String s = new String();
    for (String t : tokenList)
      s = s + "~" + t;
    return s;
  }

}

