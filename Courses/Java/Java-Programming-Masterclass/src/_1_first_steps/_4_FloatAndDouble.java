package _1_first_steps;

public class _4_FloatAndDouble {

    public static void main(String[] args){

        float myMinFloatValue = Float.MIN_VALUE;
        float myMaxFloatValue = Float.MAX_VALUE;
        System.out.println("Float Min Value = " + myMinFloatValue);
        System.out.println("Float Max Value = " + myMaxFloatValue);

        double myMinDoubleValue = Double.MIN_VALUE;
        double myMaxDoubleValue = Double.MAX_VALUE;
        System.out.println("Double Min Value = " + myMinDoubleValue);
        System.out.println("Double Max Value = " + myMaxDoubleValue);

        int myIntValue = 5;
        //float myFloatValue = 5.25 ERROR
        float myFixedFloatValue = 5.25f; //mas limpio
        float myFixedFloatValue2 = (float) 5.25;
        double myDoubleValue = 5.25d;
        System.out.println("MyIntValue= " + myIntValue);
        System.out.println("MyFloatValue= " + myFixedFloatValue);
        System.out.println("MyDoubleValue= " + myDoubleValue);

        // Retorna el cociente de la division entera
        int dividedIntValue = 5 / 2;
        // Retornan la division posta, double mas preciso que  float
        float dividedFloatValue = 5f / 3f;
        double dividedDoubleValue = 5.00 / 3.00;/*
                                                * Por un tema de hardware
                                                * asi es mas rapido
                                                * Se usa mas double que float
                                                */
        System.out.println("MyIntValue= " + dividedIntValue);
        System.out.println("MyFloatValue= " + dividedFloatValue);
        System.out.println("MyDoubleValue= " + dividedDoubleValue);

        /*
        * HERE COMES A NEW CHALLENGER
        *
        * Convert a given number of pounds to kilograms
        * HINT: 1 pound is equal to 0.45359237 of a kilogram
        * */

        double pounds = 200d;
        System.out.println("Converted kilos= " + pounds * 0.45359237);

        double pi = 3.1415927d;
        double anotherNumber = 3_000_000.4_567_890d;
        System.out.println(pi);
        System.out.println(anotherNumber);
    }
}
