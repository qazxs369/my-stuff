package _2_Control_Flow_Statements;

public class _5_ForStatement {

    public static void main(String[] args){
        System.out.println("10,000 at 2% interest = "
                + calculateInterest(10_000.0,2.0));
        System.out.println("10,000 at 3% interest = "
                + calculateInterest(10_000.0,3.0));
        System.out.println("10,000 at 4% interest = "
                + calculateInterest(10_000.0,4.0));
        System.out.println("10,000 at 5% interest = "
                + calculateInterest(10_000.0,5.0));

        // Es una paja, se puede hacer de forma ciclica

        for (int i = 2; i<=8; i++){
            System.out.println("10,000 at " + i + "% interest = "
                    // Con esto parseo para que quede el string del numero
                    // con solo dos decimales
                    + String.format("%.2f",calculateInterest(10_000.0,(double) i)));
        }

        for (int i = 8; i>=2; i--){
            System.out.println("10,000 at " + i + "% interest = "
                    + String.format("%.2f",calculateInterest(10_000.0,(double) i)));
        }


        int primesFound = 0;
        for (int i = 10; i <= 50; i++){
            if (isPrime(i)){
                primesFound++;
                System.out.println("Number " + i + " is a prime number");
                if (primesFound == 3){
                    System.out.println("Exiting loop");
                    break;
                }
            }
        }
    }

    public static double calculateInterest(double amount, double interestRate) {
        return (amount *(interestRate/100));
    }

    public static boolean isPrime(int n){
        if (n == 1){
            return false;
        }

        for (int i=2; i<= n/2; i++){
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
