package _1_first_steps;

public class _9_Methods {

    public static void main (String[] args){

        boolean gameOver = true;
        int score = 800;
        int levelCompleted = 5;
        int bonus = 100;

        if (gameOver) {
            int finalScore = score + (levelCompleted * bonus);
            finalScore += 1000;
            System.out.println("Your final score was " + finalScore);
        }

        score = 10_000;
        levelCompleted = 8;
        bonus = 200;

        if (gameOver) {
            int finalScore = score + (levelCompleted * bonus);
            finalScore += 1000;
            System.out.println("Your final score was " + finalScore);
        }

        /*
        * Es un desperdicio de espacio, lo que podemos hacer es un bloque de codigo
        * que podamos reutilizar para llevar a cabo una tarea: un metodo.
        * Es una funcion pero dentro de una clase
        * */

        calculateScore(true,800, 5, 100);

        calculateScore(true,10_000, 8, 200);

        //ggs

        // HERE COMES A NEW CHALLENGER
        //
        // Create a method called displayHighScorePosition
        // it should a players name as a parameter, and a 2nd parameter as
        // a position in the high score table
        // You should display the players name along with a message like "
        // managed to get into position " and the
        // position they got and a further message " on the high score table".
        //
        // Create a 2nd method called calculateHighScorePosition
        // it should be sent one argument only, the player score
        // it should return an in
        // the return data should be
        // 1 if the score is >=1000
        // 2 if the score is >=500 and < 1000
        // 3 if the score is >=100 and < 500
        // 4 in all other cases
        // call both methods and display the results of the following
        // a score of 1500, 900, 400 and 50
        //

        displayHiScorePosition("chabon1",
                calculateHiScorePosition(1500));
        displayHiScorePosition("chabon2",
                calculateHiScorePosition(900));
        displayHiScorePosition("chabon3",
                calculateHiScorePosition(400));
        displayHiScorePosition("chabon4",
                calculateHiScorePosition(50));
        displayHiScorePosition("chabon5",
                calculateHiScorePosition(1000));
        displayHiScorePosition("chabon6",
                calculateHiScorePosition(500));
        displayHiScorePosition("chabon7",
                calculateHiScorePosition(100));

    }

    public static void calculateScore(boolean gameOver, int score,
                                      int levelCompleted, int bonus) {
        if (gameOver) {
            int finalScore = score + (levelCompleted * bonus);
            finalScore += 1000;
            System.out.println("Your final score was " + finalScore);
        }
    }

    public static void displayHiScorePosition (String playerName,
                                               int hiScoreTablePosition){
        System.out.println(
                playerName + " managed to get into position "
                        + hiScoreTablePosition + " on the high score table");
    }

    public static int calculateHiScorePosition (int playerScore){
        if (playerScore >= 1000){
            return 1;
        }
        if (playerScore >= 500) {
            return 2;
        }
        if (playerScore >= 100) {
            return 3;
        }
        return 4;
    }
}
