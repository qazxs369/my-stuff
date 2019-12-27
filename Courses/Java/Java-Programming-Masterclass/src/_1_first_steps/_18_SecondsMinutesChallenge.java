package _1_first_steps;

public class _18_SecondsMinutesChallenge {

    /*
    * HERE COMES A NEW CHALLENGER
    *
    * Create a method called getDurationString with two params, minutes and seconds
    * Validate that mins is >= 0 and secs >= 0 and <= 59, otherwise return "invalid value"
    * Calculate how many hours, mins and secs equal the params
    *
    * Create a second method of the same name but only with secs
    * Validate that its >= 0, otherwise return "invalid value"
    * Calculate how many mins are in the secs and then call the other overloaded method
    * with mins and secs
    * */

    public static void main(String[] args){
        System.out.println(getDurationString(131,15));
        System.out.println(getDurationString(7875));
    }

    public static String getDurationString(int mins, int secs){

        if (0 <= mins && 0 <= secs && secs <= 59){

            int hours = mins / 60;
            int actualMins = mins % 60;

            String hoursStr = hours > 9 ? String.valueOf(hours) : "0" + hours;
            String minsStr = actualMins > 9 ? String.valueOf(actualMins) : "0" + actualMins;
            String secsStr = secs > 9 ? String.valueOf(secs) : "0" + secs;

            return hoursStr + "h " + minsStr + "m " + secsStr + "s";
        } else {
            return "Invalid papu";
        }
    }

    public static String  getDurationString(int secs){
        if (secs >= 0){
            int mins = secs / 60;
            int actualSecs = secs % 60;
            return getDurationString(mins, actualSecs);
        } else {
            return "Are you stupid or something";
        }
    }
}
