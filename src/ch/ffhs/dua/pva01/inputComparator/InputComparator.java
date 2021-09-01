package ch.ffhs.dua.pva01.inputComparator;

import edu.princeton.cs.algs4.StdOut;

public class InputComparator {
    public static void main(String[] args) {
        // Check if arguments were provided, else return
        if(args.length == 0){
            StdOut.println("No values to compare");
            return;
        }

        try{
            // Store the first input as the comparison standard
            int firstInput = Integer.parseInt(args[0]);

            for(int i = 1; i<args.length; i++){
                // if any of the provided integers does not match the standard
                // print "Not Equal" and return
                int currInput = Integer.parseInt(args[i]);
                if(firstInput != currInput){
                    StdOut.println("Not Equal");
                    return;
                }
            }

            // if all the provided arguments match, print Equal
            StdOut.println("Equal");
        } catch(NumberFormatException nFEx){
            // catch if any of the arguments cannot be parsed to an int
            StdOut.println("All inputs must be integers!");
        }
    }
}
