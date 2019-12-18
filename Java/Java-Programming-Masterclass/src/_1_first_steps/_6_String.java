package _1_first_steps;

public class _6_String {

    public static void main(String[] args){

        String myString = "LOQUITAAAA";
        System.out.println("Mas bien? " + myString);
        myString = myString + ", pica como un hombre, no seas cagon.";
        System.out.println("Mas bien? " + myString);
        myString = myString + " \u00a9 El Bananero";
        System.out.println("Mas bien? " + myString);

        String numberString = "250.55";
        // no los va a sumar literlmente, quedan los strings pegados
        numberString = numberString + "49.95"; /* No lo modifica, crea uno nuevo.
                                                * Es ineficiente, por esose usa algo
                                                * que se se llama StringBuffer
                                                */
        System.out.println(numberString);

        String lastString = "10";
        int myInt = 50;
        // aca convierte el int a string y lo pega atras
        lastString = lastString + myInt;
        System.out.println("LastString is equal to  " + lastString);
        double doubleNumber = 120.47d;
        lastString = lastString + doubleNumber;
        System.out.println("LastString is equal to  " + lastString);
    }
}
