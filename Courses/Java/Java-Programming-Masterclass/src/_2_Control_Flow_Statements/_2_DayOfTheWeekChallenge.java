package _2_Control_Flow_Statements;

/*
* Write a method with the name printDayOfTheWeek that has one parameter of type int and name it day.

The method should not return any value (hint: void)

Using a switch statement print gSundayh, gMondayh, c ,gSaturdayh if the int parameter gdayh is 0, 1, c , 6 respectively, otherwise it should print gInvalid dayh.

Bonus:
	Write a second solution using if then else, instead of using switch.
	Create a new project in IntelliJ with the  name gDayOfTheWeekChallengeh

* */

public class _2_DayOfTheWeekChallenge {

    public static void main(String[] args ){
        printDayOfTheWeek(-1);
        printDayOfTheWeek(0);
        printDayOfTheWeek(1);
        printDayOfTheWeek(2);
        printDayOfTheWeek(3);
        printDayOfTheWeek(4);
        printDayOfTheWeek(5);
        printDayOfTheWeek(6);
        printDayOfTheWeek(666);
    }

    public static void printDayOfTheWeek(int day){
        switch (day){
            case 0:
                System.out.println("Sunday, the day god rested. God must have been" +
                        " chilling in a sunny day");
                break;

            case 1:
                System.out.println("Monday, Garfield hates 'em");
                break;

            case 2:
                System.out.println("Tuesday, or should I say Two's day since its" +
                        " the second day of the week? Ok, I'm leaving now...");
                break;

            case 3:
                System.out.println("Wednesday, I would have called it Midday or" +
                        " Middleday");
                break;

            case 4:
                System.out.println("Thursday, somebody umst have been very thursty");
                break;

            case 5:
                System.out.println("THANK GOODNESS ITS FRIDAAAAAAY." +
                        " But actually it shouldn't matter what day it is, ");
                System.out.println(" life is all about sacrificing and hard work to achieve your ");
                System.out.println(" goals, every single day. ");
                System.out.println(" So even if its saturday if you have your projects that require ");
                System.out.println(" hard work you gotta work for it. Anyways, have a nice weekend yall! :)");
                break;

            case 6:
                System.out.println("You made it to the weekend, its Saturday." +
                        " Why don't you help yourself to some biscuits?");
                break;

            default:
                System.out.println("Invaliday. Sorry, couldn't help it");
                break;
        }
    }
}
