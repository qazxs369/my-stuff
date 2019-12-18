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
        double dividedDoubleValue = 5d / 3d;
        System.out.println("MyIntValue= " + dividedIntValue);
        System.out.println("MyFloatValue= " + dividedFloatValue);
        System.out.println("MyDoubleValue= " + dividedDoubleValue);
    }
}
