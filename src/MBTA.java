
import java.util.*;

public class MBTA {
    public static HashMap<String, ArrayList<String>> lines;
    public static String intersection;

    public MBTA() {
        lines = new HashMap<>();
        ArrayList<String> redStops = new ArrayList<>();
        redStops.addAll(Arrays.asList("South Station",
                "Park Street",
                "Kendall",
                "Central",
                "Harvard",
                "Porter",
                "Davis",
                "Alewife"));
        lines.put("Red", redStops);

        ArrayList<String> greenStops = new ArrayList<>();
        greenStops.addAll(Arrays.asList(
                "Government Center",
                "Park Street",
                "Boylston",
                "Arlington",
                "Copley",
                "Hynes",
                "Kenmore"));
        lines.put("Green", greenStops);

        ArrayList<String> orangeStops = new ArrayList<>();
        orangeStops.addAll(Arrays.asList("North Station",
                "Haymarket",
                "Park Street",
                "State",
                "Downtown Crossing",
                "Chinatown",
                "Back Bay",
                "Forest Hills"));
        lines.put("Orange", orangeStops);
        intersection = "Park Street";

    }

    public static int stopsBetweenStations(String srcLine,
                                           String srcStation,
                                           String desLine,
                                           String desStation) {
        if (validatePara(srcLine, srcStation, desLine, desStation)) {
            if (srcLine.equals(desLine)) {
                int startIndex = lines.get(srcLine).indexOf(srcStation);
                int endIndex = lines.get(srcLine).indexOf(desStation);
                int total = endIndex - startIndex;
                showRoutes(srcLine, srcStation, desLine, desStation);
                return Math.abs(total);
            } else {
                // calculate stops from start to intersection
                int startIndex = lines.get(srcLine).indexOf(srcStation);
                // System.out.println(startIndex);
                int intIndex = lines.get(srcLine).indexOf("Park Street");
                //  System.out.println(intIndex);
                int stopsBeforeInt = Math.abs(intIndex - startIndex);

                // System.out.println(stopsBeforeInt);
                //calculate stops after intersection
                int intersectionIndex = lines.get(desLine).indexOf("Park Street"); // get interection index in destination line
                //System.out.println("inter" +intersectionIndex);
//            int startAfterInt = lines.get(desLine).indexOf(intersectionIndex);
//            System.out.println("startAfterInt" + startAfterInt);
                int desInt = lines.get(desLine).indexOf(desStation);
                //    System.out.println(desInt);
                int stopsAfter = Math.abs(desInt - (intersectionIndex));
                //   System.out.println(stopsAfter);
                return stopsBeforeInt + stopsAfter;
            }
        } else {
            System.out.println("Invalid line or station!!!");
            return -1;
        }

    }

    public static boolean validatePara(String srcLine,
                                       String srcStation,
                                       String desLine,
                                       String desStation) {
        if (lines.containsKey(srcLine) && lines.get(srcLine).contains(srcStation)
                && lines.containsKey(desLine) && lines.get(desLine).contains(desStation)) {
            return true;
        } else {
            return false;
        }

    }

    public static void showRoutes(String srcLine,
                                  String srcStation,
                                  String desLine,
                                  String desStation) {
        System.out.println("Rider boards the train a " + srcLine + " and " + srcStation);
//get station between

        //if same line
        if (srcLine.equals(desLine)) {
           ArrayList<String> stopsBetween = lines.get(srcLine);
            int startIndex = lines.get(srcLine).indexOf(srcStation);
            int endIndex = lines.get(srcLine).indexOf(desStation);
           if (startIndex >= 0 && endIndex <= stopsBetween.size() && endIndex > startIndex){
              ArrayList<String> stopsName = new ArrayList<>(stopsBetween.subList(startIndex,endIndex));
               System.out.println("Rider arrives at "+srcLine+" and "+stopsName);

           }


        }

        // Rider arrives at Red Line and Park Street.
// Rider transfers from Red Line to Green Line at Park Street.
// Rider arrives at Green Line and Boylston.
// Rider arrives at Green Line and Arlington.
// Rider arrives at Green Line and Copley.
        System.out.println("Rider exits the train at " + desLine + " and " + desStation + ".");


    }

    public static void main(String[] args) {
        MBTA mbta = new MBTA();
        // Valid tests
        System.out.println(stopsBetweenStations(
                "Red", "Alewife", "Red", "Alewife"
        )); //  0

        System.out.println(stopsBetweenStations(
                "Red", "Alewife", "Red", "South Station"
        )); // 7

        System.out.println(stopsBetweenStations(
                "Red", "South Station", "Green", "Kenmore"
        )); //  6

        // invalid test cases
        System.out.println(stopsBetweenStations(
                "Blue", "Alewife", "Red", "South Station"
        )); // -1

        // Invalid station for the line
        System.out.println(stopsBetweenStations(
                "Red", "Kenmore", "Red", "South Station"
        )); // -1

        System.out.println(stopsBetweenStations(
                "Red", "South Station", "Green", "Harvard"
        )); // -1
    }
}
