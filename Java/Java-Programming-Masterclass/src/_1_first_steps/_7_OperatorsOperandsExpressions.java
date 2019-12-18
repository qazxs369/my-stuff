package _1_first_steps;

public class _7_OperatorsOperandsExpressions {

    public static void main(String[] args){

        // Operadores: simbolos *, +, -, etc
        // Operandos: los bichos siendo manipulados por operadores
        // Expresion: combinacion de variables, operadores, returns y toda la bola

        int result = 1 + 2; // 3
        System.out.println("1 + 2 is " + result);
        int previousResult = result;
        System.out.println("previousResult is " + previousResult);
        result = result - 1;
        System.out.println("3 - 1 is " + result);
        System.out.println("previousResult is " + previousResult);

        /*
        * cambie result y previousResult quedo igual, eso es porque no era una
        * referencia, porque java no toma por referencia como c++ (aguante c++ vieja)
        * */

        result = result * 10;
        System.out.println("2 * 10 = " + result);

        result = result / 5;
        System.out.println("20 / 5 = " + result);

        result = result % 3;
        System.out.println("4 % 3 = " + result);

        /*
        * ATAJOS DE OPERADORES
        * */

        result++; // result = result + 1
        result--; // result = result - 1
        result += 2; // result = result + 2
        result *= 10; // result = result * 10
        result /= 3; // result = result / 3
        result -= 2; // result = result - 2

        /*
        * IF THEN ELSE
        * */

        boolean isAlien = false;
        if (isAlien == true)
            // then
            System.out.println("Not an alien");
            // else
            System.out.println("ayy lmao");

        boolean elPelucaSape = true;
        if (elPelucaSape) {
            System.out.println("EL PELUCA SAPEEEE");
        } else {
            System.out.println("Tomatela");
        }

        int topScore = 80;
        if (topScore >= 100) {
            System.out.println("HI SCORE");
        } else {
            System.out.println("Segui participando nene");
        }

        int secondTopScore = 60;
        if (topScore > secondTopScore && topScore < 100) {
            System.out.println("Greater than second top score and less than 100");
        }

        if ((topScore > 90) || (secondTopScore <= 90)) {
            System.out.println("Either or both of the conditions are posta. " +
                    "Por que en spanglish jajaj");
        }
    }
}
