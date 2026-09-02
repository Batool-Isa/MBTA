
import java.util.*;

public class MBTA {
    public static HashMap<String, ArrayList<String>> lines;
    public static String intersection;

    public MBTA() {
        lines = new HashMap<>();
        ArrayList<String> redStops = new ArrayList<>(Arrays.asList(
                "South Station",
                "Park Street",
                "Kendall",
                "Central",
                "Harvard",
                "Porter",
                "Davis",
                "Alewife"
        ));
        lines.put("Red", redStops);

        ArrayList<String> greenStops = new ArrayList<>(Arrays.asList(
                "Government Center",
                "Park Street",
                "Boylston",
                "Arlington",
                "Copley",
                "Hynes",
                "Kenmore"
        ));
        lines.put("Green", greenStops);

        ArrayList<String> orangeStops = new ArrayList<>(Arrays.asList(
                "North Station",
                "Haymarket",
                "Park Street",
                "State",
                "Downtown Crossing",
                "Chinatown",
                "Back Bay",
                "Forest Hills"
        ));
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
                int intIndex = lines.get(srcLine).indexOf(intersection);
                //  System.out.println(intIndex);
                int stopsBeforeInt = Math.abs(intIndex - startIndex);

                // System.out.println(stopsBeforeInt);
                //calculate stops after intersection
                int intersectionIndex = lines.get(desLine).indexOf(intersection); // get interection index in destination line
                //System.out.println("inter" +intersectionIndex);
//            int startAfterInt = lines.get(desLine).indexOf(intersectionIndex);
//            System.out.println("startAfterInt" + startAfterInt);
                int desInt = lines.get(desLine).indexOf(desStation);
                //    System.out.println(desInt);
                int stopsAfter = Math.abs(desInt - (intersectionIndex));
                //   System.out.println(stopsAfter);
                showRoutes(srcLine, srcStation, desLine, desStation);
int totalStops = stopsBeforeInt + stopsAfter;
         //       System.out.println("totalll "+ totalStops);
                return totalStops;
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
        // get station between start and destination stations
        // if same line
        if (srcLine.equals(desLine)) {
            ArrayList<String> stopsBetween = lines.get(srcLine);
           // System.out.println(stopsBetween);
            int startIndex = lines.get(srcLine).indexOf(srcStation);
            int endIndex = lines.get(srcLine).indexOf(desStation);
            if (startIndex >= 0 && endIndex <= stopsBetween.size()) {
                if (startIndex < endIndex) {
                    for (int i = startIndex + 1; i <= endIndex ; i++) {
                        System.out.println("Rider arrives at " + srcLine + " and " + stopsBetween.get(i));
                    }
                } else {
//                    System.out.println("sasa");
//                    System.out.println(endIndex);
//                    System.out.println(startIndex);
                    for (int i = startIndex - 1; i >= endIndex ; i--) {
                        System.out.println("Rider arrives at " + srcLine + " and " + stopsBetween.get(i));
                    }
                }
            }
        } else { // if different line
            // calculate stops from start to intersection
            int startIndex = lines.get(srcLine).indexOf(srcStation);
            // System.out.println(startIndex);
            int intIndex = lines.get(srcLine).indexOf(intersection);
            //  System.out.println(intIndex);

            ArrayList<String> stopsBetween1 = lines.get(srcLine);
           // System.out.println(stopsBetween1);
            if (startIndex >= 0 && intIndex <= stopsBetween1.size()) {
                if (startIndex < intIndex) {
                    for (int i = startIndex + 1; i <= intIndex; i++) {
                        System.out.println("Rider arrives at " + srcLine + " and " + stopsBetween1.get(i));
                    }
                } else {
                    for (int i = intIndex; i >= startIndex; i--) {
                        System.out.println("Rider arrives at " + srcLine + " and " + stopsBetween1.get(i));
                    }
                }
            }

            // print routes after changing the line
            ArrayList<String> stopsBetween2 = lines.get(desLine);
            //calculate stops after intersection
            int intersectionIndex = lines.get(desLine).indexOf(intersection); // get interection index in destination line
            System.out.println("Rider transfers from " + srcLine + " to " + desLine + " at Park Street.");
            int desInt = lines.get(desLine).indexOf(desStation);
            //System.out.println(desInt);
            if (intersectionIndex >= 0 && intersectionIndex <= stopsBetween2.size()) {
                if (intersectionIndex < desInt) {
                    for (int i = intersectionIndex + 1; i <= desInt; i++) {
                        System.out.println("Rider arrives at " + desLine + " and " + stopsBetween2.get(i));
                    }
                } else {
                    for (int i = desInt; i >= intersectionIndex; i--) {
                        System.out.println("Rider arrives at " + desLine + " and " + stopsBetween2.get(i));
                    }
                }
            }

        }
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


        System.out.println(stopsBetweenStations("Red", "South Station", "Green", "Copley"));//4


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
