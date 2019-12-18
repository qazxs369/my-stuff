package I_first_steps;

public class III_PrimitiveTypesChallenge {

    public static void main(String[] args) {

        /*
        * Your challenge is to create a byte variable and set it to any valid byte
        * number, it doesn't matter. Same for a short and int variables. Lastly,
        * create a variable of type long and make it equal to 50000 plus 10 times
        * the sum of the other variables
        * */

        byte byteValue = 127;
        short shortValue = 18614;
        int intValue = 666;
        int sum = byteValue + shortValue + intValue;
        long longTotal = 50000L + (10*sum);
        System.out.println(longTotal);

        short shortTotal = (short) (1000 + 10 * (byteValue
                + shortValue + intValue));
    }
}
