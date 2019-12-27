package _1_first_steps;

/*
* Write a method printYearsAndDays with parameter of type long named minutes.

The method should not return anything (void) and it needs to calculate the years and days from the minutes parameter.

If the parameter is less than 0, print text "Invalid Value".

Otherwise, if the parameter is valid then it needs to print a message in the format "XX min = YY y and ZZ d".

XX represents the original value minutes.
YY represents the calculated years.
ZZ represents the calculated days.


EXAMPLES OF INPUT/OUTPUT:

* printYearsAndDays(525600);  → should print "525600 min = 1 y and 0 d"

* printYearsAndDays(1051200); → should print "1051200 min = 2 y and 0 d"

* printYearsAndDays(561600);  → should print "561600 min = 1 y and 25 d"


TIPS:

* Be extra careful about spaces in the printed message.

* Use the remainder operator

* 1 hour = 60 minutes

* 1 day = 24 hours

* 1 year = 365 days

NOTES

* The printYearsAndDays method needs to be defined as public static like we have been doing so far in the course.

* Do not add main method to solution code.

* The solution will not be accepted if there are extra spaces
* */

public class _20_MinutesToYearsDaysCalculatorChallenge {

    public static void main(String[] args){
        printYearsAndDays(9_000_000);
    }

    public static void printYearsAndDays(long mins){
        if (mins < 0){
            System.out.println("you are manco");
        } else {
            long days = mins / 1440;
            long years = days / 365;
            long actualDays = days % 365;

            String minsStr = mins > 9 ? String.valueOf(mins) : "0" + mins;
            String yearsStr = years > 9 ? String.valueOf(years) : "0" + years;
            String daysStr = actualDays > 9 ? String.valueOf(actualDays) : "0" + actualDays;

            System.out.println(minsStr + " min = " + yearsStr + " y and " + daysStr + " d");
        }
    }
}
