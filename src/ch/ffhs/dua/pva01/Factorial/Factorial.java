package ch.ffhs.dua.pva01.Factorial;

public class Factorial {
  public static void main(String[] args) {
    System.out.printf("%s", getFact(-4));
  }

  private static int getFact(int number) {
    if (number <= 1) {
      return 1;
    } else {
      return number * getFact(number - 1);
    }
  }
}
