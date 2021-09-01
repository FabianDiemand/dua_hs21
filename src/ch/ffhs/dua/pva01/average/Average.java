package ch.ffhs.dua.pva01.average;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Average {

    public static void main(String[] args) {
        double sum = 0.0;
        int cnt = 0;
        while (!StdIn.isEmpty()) {
            sum += StdIn.readDouble();
            cnt++;
        }
        if(cnt == 0 ){
            StdOut.printf("Enter at least one value to calculate the average.");
            return;
        }

        double avg = sum / cnt;
        StdOut.printf("Average is %.5f\n", avg);
    }
}

