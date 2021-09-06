package ch.ffhs.dua.pva02.parantheses;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.List;

public class Parantheses {

  public static void main(String[] args) {
    if(args.length == 0) throw new IllegalArgumentException("Nothing to check.");

    ArrayList<Character> openingParentheses = new ArrayList<>(List.of('{', '(', '['));
    ArrayList<Character> closingParentheses = new ArrayList<>(List.of('}', ')', ']'));

    Stack<Character> parenthesesStack = new Stack<>();
    int index = 0;

    for (String input : args) {
      for(int i = 0; i < input.length(); i++){
        index++;
        char character = input.charAt(i);

        if (openingParentheses.contains(character)) {
          parenthesesStack.push(character);
        } else if (closingParentheses.contains(character) && !parenthesesStack.isEmpty() && !isValidBracket(character, parenthesesStack)) {
          StdOut.printf("\nERROR: Wrong bracket found at index %s.\nWord: %s\n", index, input);
          return;
        }
      }
    }
  }

  private static boolean isValidBracket(char bracket, Stack<Character> parenthesesCheck) {
    char openingBracket = parenthesesCheck.pop();

    boolean curlyBracesMatch = bracket == '}' && openingBracket == '{';
    boolean roundParenthesesMatch = bracket == ')' && openingBracket == '(';
    boolean squareBracketsMatch = bracket == ']' && openingBracket == '[';

    return curlyBracesMatch || roundParenthesesMatch || squareBracketsMatch;
  }
}