package com.company;

// Emin Shamshoian
// Comp 282
// M/W 2-3:15 PM
// Project 1
// This is the MagicSquare project

import java.util.Random;

class MagicSquare {
    // Declare required arrays
    private int[] numbersArray;
    private int[][] square;

    // Declare required variables
    private int size;
    private Random randomNumber;
    private int col;
    private int row;

    // constructor -- set the size and instantiate the array
    public MagicSquare(int size) {
        // Declare variables
        int i = 0, j = 0;

        // Initialize a random number
        this.randomNumber = new Random();

        // Set te size of variables
        this.size = size;
        this.col = size;
        this.row = size;

        // Create the two new arrays
        this.square = new int[row][col];
        this.numbersArray = new int[size * size];


        // Set the contents of the square array to zero
        for(i = 0; i < row; i++) {
            for(j = 0; j < col; j++) {
                this.square[i][j] = 0;
            }
        }

        // Set the contents of the num array to zero
        for(i = 0; i < size * size; i++) {
            numbersArray[i] = i + 1;
        }
    }

    // purely random -- give up after "tries" tries
    public int purelyRandom(int tries) {

        // Declare needed variabels
        int tryCt = 0;
        int result = 0;
        boolean isFound = false;


        while (!isFound && tryCt < tries) {
            int temp = size * size;

            for(int i = 0; i < row; i++) {
                for(int j = 0; j < col; j++) {
                    int tRand = randomNumber.nextInt(temp);
                    square[i][j] = numbersArray[tRand];
                    int tNum = numbersArray[tRand];
                    numbersArray[tRand] = numbersArray[temp-1];
                    numbersArray[temp-1] = tNum;
                    temp--;
                }
            }
            isFound = magic();
            tryCt++;
        }

        // This is how my method ends
        if (isFound) {
            return tryCt;
        } else {
            return -1;
        }
    }

    // force last number in each row
    public int endOfRow(int tries) {
        boolean isFound = false;
        int tryCt = 0;
        int magicSum = ((size * (size * size + 1)) / 2);

        while (!isFound && tryCt < tries) {
            // Declare required variables
            boolean ok = true;
            int tNumSize = size * size, sumOfRows = 0, lastNum = 0, pick = 0;


            // fills rows one at a time
            for (int i = 0; i < size && ok; i++) {
                sumOfRows = 0;
                // put random values in all but the last spot
                for (int j = 0; j < size - 1; j++) {
                    int tRand = randomNumber.nextInt(tNumSize);
                    square[i][j] = numbersArray[tRand];
                    sumOfRows += numbersArray[tRand];
                    int tNum = numbersArray[tRand];
                    numbersArray[tRand] = numbersArray[tNumSize-1];
                    numbersArray[tNumSize-1] = tNum;
                    tNumSize--;
                }

                lastNum = tNumSize;
                pick = find(magicSum - sumOfRows, numbersArray, lastNum);

                if (pick == -1) {
                    ok = false;
                } else {
                    int tNum = numbersArray[pick];
                    numbersArray[pick] = numbersArray[tNumSize - 1];
                    numbersArray[tNumSize -1 ] = tNum;
                    tNumSize--;
                    square[i][size - 1] = tNum;
                }
            }
            isFound = magic();
            tryCt++;
        }

        if (isFound){
        } else {
            tryCt = -1;
        }
        return tryCt;
    }

    // Local find method
    private int find(int num, int[] arrOfNums, int lastNum) {
        // Declare required variables
        int i = 0;

        // Conditional statements to find the number
        if(num <= size * size) {
            while(arrOfNums[i] != num && i < lastNum - 1) {
                i++;
            }
        } else {
            i = -1;
        }

        // Check furthur edge cases
        if(i == -1 || arrOfNums[i] != num) {
            i = -1;
        }
        return i;
    }


    // determine if a magic square has been created, i.e., check all rows, columns,
    // and diagonals sum to the same value
    private boolean magic() {

        // Declare needed variables
        boolean isFound = false;
        int temp = size - 1;
        int magicSum = ((size * (size * size + 1)) / 2);

        // Create variables for all the possible sum types
        int rowSum = 0;
        int colSum = 0;
        int firstDSum = 0;
        int secondDSum = 0;
        int magicDiag = 0;
        int magicRow = 0;
        int magicCol = 0;

        // Check if sum is te magic sum
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                rowSum += square[i][j];
                colSum += square[j][i];
            }
            if(rowSum == magicSum) {
                magicRow++;
            }
            if(colSum == magicSum) {
                magicCol++;
            }
            rowSum = 0;
            colSum = 0;
        }

        // Loop to see if any of the diagonals are equal to the magicDiag sum
        for(int i = 0; i < size; i++) {
            firstDSum += square[i][i];
            secondDSum += square[temp][i];
            temp--;
        }

        if(firstDSum == magicSum) {
            magicDiag++;
        }
        if(secondDSum == magicSum) {
            magicDiag++;
        }
        if (magicRow == size && magicCol == size && magicDiag == 2) {
            isFound = true;
        }
        return isFound;
    }

    // output the magic square (or whatever is in the array if it is not)
    public void out() {
        int row, col;
        for (row = 0; row < size; row++) {
            for (col = 0; col < size; col++) {
                System.out.print(String.format("%3d", square[row][col]));
            }
            System.out.println();
        }
    }

    // change to false if this algorithm was not implemented
    public boolean rowLastImplemented()
    {
        return false;
    }

    // change to false if this algorithm was not implemented
    public boolean pairsImplemented()
    {
        return false;
    }

    // put your name here
    public static String myName()
    {
        return "Emin Shamsoian Malhami";
    }
}