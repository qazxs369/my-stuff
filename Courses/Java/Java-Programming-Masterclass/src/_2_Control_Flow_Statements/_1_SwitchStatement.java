package _2_Control_Flow_Statements;

/*
* Basicamente en la seccion de control flow lo que hacemos es a traves de statements
* como switch for, while y do while controlamos el flujo del programa, que codigos va
* a ejecutar despues, es decir por donde va encaminado
* */

public class _1_SwitchStatement {

    public static void main(String[] args){
        /*
        int value = 3;
        if (value == 1){
            System.out.println("Value was 1");
        } else if (value == 2){
            System.out.println("Value was 2");
        } else {
            System.out.println("Was not 1 or 2");
        }
        */

        /*
        * Mas comodo con un switch, basicamente planteo casos para distintos valores
        * que pueda tener lo que vaya dentro de switch, el tema es que solo trabaja
        * en base a la variable esa, no es como un if que podes poner la condicion que
        * se te cante y dentro de un if poner otro if condicionado por otra variable
        * */

        int switchValue = 10;
        switch (switchValue) {
            case 1:
                System.out.println("Value was 1");
                break;

            case 2:
                System.out.println("Value was 2");
                break;

            // puedo agrupar mas de uno
            case 3: case 4: case 5:
                System.out.println("Was a 3, 4 or 5");
                System.out.println("actually it was a " + switchValue);
                break;

            default:
                System.out.println("Was not 1 or 2");
                //break;

            case 666:
                System.out.println("si estas viendo esto te comiste un break");

            case 678:
                System.out.println("se va a meter en los demas casos hasta que termine");

            case 42:
                System.out.println("o hasta que encuentre un break");
        }
        // mas codigo...
        // aca retoma despues de un break

        // HERE COMES A NEW CHALLENGER
        //
        // Create a new switch statement using char instead of int
        // create a new char variable
        // create a switch statement testing for
        // A, B, C, D, or E
        // display a message if any of these are found and then break
        // Add a default which displays a message saying not found

        char charValue = 'A';
        switch (charValue) {

            case 'A':
                System.out.println("A was found!");
                break;

            case 'B':
                System.out.println("B was found!");
                break;

            case 'C': case 'D': case 'E':
                System.out.println(charValue + " was found!");
                break;

            default:
                System.out.println("Couldn't find jack shit");
        }

        String stringValue = "jAnUaRY";
        /*
        * El problema es que hay muchas formas de combinar minusculas y mayusculas
        * por lo que tendria que hacer case january: case January: case jAnuary...
        * te volves loco al pedo, entonces normalicemos stringValue pasandolo todo
        * a minusculas!
        * */
        switch (stringValue.toLowerCase()) {
            case "january":
                System.out.println("Jan");
                break;

            case "june":
                System.out.println("Jun");
                break;

            default:
                System.out.println("Not sure");
        }
    }
}
