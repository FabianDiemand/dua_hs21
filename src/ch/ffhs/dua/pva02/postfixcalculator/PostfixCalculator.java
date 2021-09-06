package ch.ffhs.dua.pva02.postfixcalculator;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.List;

public class PostfixCalculator {
  public static void main(String[] args) {
    ArrayList<Character> operators = new ArrayList<>(List.of('+', '-', '*', ':'));
    Stack<Double> operands = new Stack<>();

    for(String input: args){
      for(int i = 0; i < input.length(); i++){
        char currentCharacter = input.charAt(i);

        if(operators.contains(currentCharacter)){
          double op2 = operands.pop();
          double op1 = operands.pop();

          operands.push(calculate(op1, op2, currentCharacter));
        } else {

          try{
            double operand = Double.parseDouble(String.valueOf(currentCharacter));
            operands.push(operand);
          } catch(NumberFormatException nFEx){
            StdOut.printf("ERROR: %s cannot be parsed to a Double.", currentCharacter);
            return;
          }
        }
      }
    }

    StdOut.println(operands.pop());
  }

  private static double calculate(double firstOperand, double secondOperand, char operator){
    return switch (operator) {
      case '+' -> firstOperand + secondOperand;
      case '-' -> firstOperand - secondOperand;
      case '*' -> firstOperand * secondOperand;
      case ':' -> firstOperand / secondOperand;
      default -> throw new IllegalArgumentException(
          String.format("%s is not a valid operation.", operator));
    };
  }
}
