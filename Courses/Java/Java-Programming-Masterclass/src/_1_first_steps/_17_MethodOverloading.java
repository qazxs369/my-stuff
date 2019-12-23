package _1_first_steps;

public class _17_MethodOverloading {

    public static void main (String[] args){
        int newScore = calculateScore("Lukita",500);
        System.out.println("New Score is " + newScore);
        calculateScore(75);
        calculateScore();
        calcFeetAndInchesToCentimeters(7,-5);
        calcFeetAndInchesToCentimeters(100);
        calcFeetAndInchesToCentimeters(157);
    }

    public static int calculateScore(String playerName, int score){
        System.out.println("Player " + playerName + " scored " + score + " points");
        return score * 1000;
    }

    /*
    * Overloading es cuando se usa el mismo nombre de un metodo para otro
    * pero difieren en parametros
    * */

    public static int calculateScore(int score){
        System.out.println("Unnamed player scored " + score + " points");
        return score * 1000;
    }

    public static int calculateScore(){
        System.out.println("No player scored anything");
        return 0;
    }

    /*
    * Cambiar el tipo no alcanza para que sea unico, si o si hay que cambiar
    * el numero de parametros
    *
    * public static void calculateScore(){
    *    System.out.println("No player scored anything");
    * }
    *
    * */

    // HERE COMES A NEW CHALLENGER
    //
    // Create a method called calcFeetAndInchesToCentimeters
    // It needs to have two parameters.
    // feet is the first parameter, inches is the 2nd parameter
    //
    // You should validate that the first parameter feet is >= 0
    // You should validate that the 2nd parameter inches is >=0 and <=12
    // return -1 from the method if either of the above is not true
    //
    // If the parameters are valid, then calculate how many centimetres
    // comprise the feet and inches passed to this method and return
    // that value.
    //
    // Create a 2nd method of the same name but with only one parameter
    // inches is the parameter
    // validate that its >=0
    // return -1 if it is not true
    // But if its valid, then calculate how many feet are in the inches
    // and then here is the tricky part
    // call the other overloaded method passing the correct feet and inches
    // calculated so that it can calculate correctly.
    // hints: Use double for your number datatypes is probably a good idea
    // 1 inch = 2.54cm  and one foot = 12 inches
    // use the link I give you to confirm your code is calculating correctly.
    // Calling another overloaded method just requires you to use the
    // right number of parameters.

    public static double calcFeetAndInchesToCentimeters(double feet, double inches){
        if (0 <= feet && 0 <= inches && inches <= 12){
            double centimeters = (inches*2.54) + (feet*12*2.54);
            System.out.println(feet + " feet + " + inches + " inches = "
            + centimeters + " cm");
            return centimeters;
        }
        System.out.println("que haces sos gracioso");
        return -1;
    }

    public static double calcFeetAndInchesToCentimeters(double inches){
        if (0 <= inches){
            double feet = (int) inches/12;
            double remainingInches = (int) inches % 12;
            return calcFeetAndInchesToCentimeters(feet,remainingInches);
        }
        System.out.println("para que haces");
        return -1;
    }
}
