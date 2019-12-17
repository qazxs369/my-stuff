package com.company;

public class Main {

    public static void main(String[] args) {

        System.out.println("El peluca sape");

        int myFirstNumber = (10 + 5) + (2*10);
        int mySecondNumber = 12;
        int myThirdNumber = myFirstNumber * 2;
        int myTotal = myFirstNumber + mySecondNumber + myThirdNumber;
        int myLastOne = 1000 - myTotal;

        System.out.println(myLastOne);


        int myValue = 1_000;
        int myMinIntValue = Integer.MIN_VALUE;
        int myMaxIntValue = Integer.MAX_VALUE;
        System.out.println("Integer Minimum Value = " + myMinIntValue);
        System.out.println("Integer Maximum Value = " + myMaxIntValue);
        //OVERFLOW:
        System.out.println("Busted MAX value = " + (myMaxIntValue + 1));
        //UNDERFLOW:
        System.out.println("Busted MIN value = " + (myMinIntValue - 1));
        //int myMaxIntTest = 2_147_483_648; ESTO NO COMPILA POR SER MUY GRANDE

        byte myMinByteValue = Byte.MIN_VALUE;
        byte myMaxByteValue = Byte.MAX_VALUE;
        System.out.println("Byte Minimum Value = " + myMinByteValue);
        System.out.println("Byte Maximum Value = " + myMaxByteValue);

        short myMinShortValue = Short.MIN_VALUE;
        short myMaxShortValue = Short.MAX_VALUE;
        System.out.println("Short Minimum Value = " + myMinShortValue);
        System.out.println("Short Maximum Value = " + myMaxShortValue);

        long myLongValue = 100L;
        long myMinLongValue = Long.MIN_VALUE;
        long myMaxLongValue = Long.MAX_VALUE;
        System.out.println("Long Minimum Value = " + myMinLongValue);
        System.out.println("Long Maximum Value = " + myMaxLongValue);
        long bigLongLiteralValue = 2_147_483_648L;
        System.out.println(bigLongLiteralValue);

        myTotal = (myMinIntValue / 2);
        //byte myNewByteValue = (myMinByteValue / 2); ERROR, la division da int
        byte myNewByteValue = (byte) (myMinByteValue / 2);
        short myNewShortValue = (short) (myMinShortValue / 2);


        byte byteValue = 10;
        short shortValue = 20;
        int intValue = 50;
        int sum = byteValue + shortValue + intValue;
        long longTotal = 50000L + (10*sum);
        System.out.println(longTotal);

        short shortTotal = (short) (1000 + 10 * (byteValue
                + shortValue + intValue));



    }
}
