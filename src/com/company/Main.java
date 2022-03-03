package com.company;

public class Main {

    public static void main(String[] args) {
        MagicSquare sq = new MagicSquare(3);
        int ct = sq.purelyRandom(100000000);

        if (ct == -1) {
            System.out.println("Failed to find an n = 3 solution.");
        } else {
            System.out.println("\nn = 3 solution in " + ct + " tries.");
            sq.out();
            System.out.println();
        }

        sq = new MagicSquare(4);
        ct = sq.purelyRandom(1000000);

        if (ct == -1) {
            System.out.println("Failed to find an n = 4 solution.");
        } else {
            System.out.println("n = 4 solution in " + ct + " tries.");
            sq.out();
            System.out.println();
        }

        if (sq.rowLastImplemented()) {
            sq = new MagicSquare(4);
            ct = sq.endOfRow(10);
            if (ct == -1) {
                System.out.println("\nFailed to find an n = 4 solution in 10 tries using end of row trick.");
            } else {
                System.out.println("\nn = 4 solution in " + ct + " tries.");
                sq.out();
            }
            ct = sq.endOfRow(10000000);
            if (ct == -1) {
                System.out.println("\nFailed to find an n = 4 solution in 10000000 using end of row trick.");
            } else {
                System.out.println("\nn = 4 solution in " + ct + " tries.");
                sq.out();
            }
        }
        System.out.println("\n\n" + MagicSquare.myName() + " program has completed.");
    }

}
