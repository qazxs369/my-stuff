package _2_Control_Flow_Statements;

public class _8_WhileDoWhileStatements {

    public static void main(String[] args){
        // es lo mismo que el for pero la diferencia es que la variable de la condicion
        // se define antes, aunque puede tomar cualquier cosa ya existente tambien
        int count = 1;
        while (count != 6){
            System.out.println("Count value is " + count);
            count++;
        }

        count = 1;
        while (true){
            if (count == 6){
                break;
            }
            System.out.println("Count value is " + count);
            count++;
        }

        /*
        * Lo interesante de hacer do while es que lo que este dentro del do se ejecuta
        * antes de ver la condicion por lo que este loop garantiza que va a iterar
        * por lo menos una vez
        * */

        count = 1;
        do {
            System.out.println("Count value was " + count);
            count++;
        } while (count != 6);

        /*
        * Esto nunca sale del loop porque para cuando quiera verificar si count llego a 6
        * count ya se habra pasado de 6 y nunca sera igual a 6 por lo que sigue iterando
        *
        count = 6;
        do {
            System.out.println("Count value was " + count);
            count++;
        } while (count != 6);
        * */

        //	    int count = 6;
//        while(count != 6) {
//            System.out.println("Count value is " + count);
//            count++;
//        }
//
//        for(int i=6; i!= 6; i++) {
//            System.out.println("Count value is " + count);
//        }
//
//        count = 6;
//        do {
//            System.out.println("Count value was " + count);
//            count++;
//
//            if(count >100) {
//                break;
//            }
//
//        } while(count != 6);

//        int number = 5;
//        int finishNumber = 20;
//        while(number <= finishNumber) {
//            if(!isEvenNumber(number)) {
//                number++;
//                continue;
//            }
//
//            System.out.println("Even number " + number);
//            number++;
//        }

        // HERE COMES A NEW CHALLENGER
        //
        // Modify the while code above
        // Make it also record the total number of even numbers it has found
        // and break once 5 are found
        // and at the end, display the total number of even numbers found
        //
        // Create a method called isEvenNumber that takes a parameter of type int
        // Its purpose is to determine if the argument passed to the method is
        // an even number or not.
        // return true if an even number, otherwise return false;


    }
}
