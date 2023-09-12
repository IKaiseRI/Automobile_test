import entity.Automobile;
import entity.BodyType;
import entity.SeatNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AutomobileUtils {

    public static double calculateAnnualRoadTax(Automobile automobile) {
        SeatNumber seatNumber = automobile.getCharacteristic().getNumberOfSeats();

        switch (automobile.getBodyType()) {
            case SUV -> {
                if (seatNumber == SeatNumber.FIVE) {
                    return automobile.getPrice() * 0.1;
                }
                return automobile.getPrice() * 0.08;
            }
            case SEDAN -> {
                return automobile.getPrice() * 0.07;
            }
            default -> {
                return automobile.getPrice() * 0.12;
            }
        }
    }

    public static int compareByMaxSpeed(Automobile auto1, Automobile auto2) {
        return Integer.compare(
                auto1.getCharacteristic().getMaxSpeed(),
                auto2.getCharacteristic().getMaxSpeed()
        );
    }

    public static List<Automobile> findAutomobilesByType(List<Automobile> automobiles, BodyType bodyType) {
        List<Automobile> matchingAutomobiles = new ArrayList<>();

        for (Automobile automobile : automobiles) {
            if (automobile.getBodyType() == bodyType) {
                matchingAutomobiles.add(automobile);
            }
        }

        return matchingAutomobiles;
    }

    public static double calculateFuelEfficiency(Automobile automobile) {
        int maxSpeed = automobile.getCharacteristic().getMaxSpeed();
        double engineVolume = automobile.getCharacteristic().getEngineVolume();

        return maxSpeed / engineVolume;
    }

    public static SeatNumber getSeatingCapacity(Automobile automobile) {
        return automobile.getCharacteristic().getNumberOfSeats();
    }

    public static String getAutomobileDetails(Automobile automobile) {
        return new StringBuilder()
                .append("Brand: ").append(automobile.getBrand())
                .append("\nPrice: ").append(automobile.getPrice())
                .append("\nBodyType: ").append(automobile.getBodyType())
                .append("\nMax Speed: ").append(automobile.getCharacteristic().getMaxSpeed())
                .append("\nEngine Volume: ").append(automobile.getCharacteristic().getEngineVolume())
                .append("\nSeating Capacity: ").append(getSeatingCapacity(automobile))
                .toString();
    }

    public static void sortAutomobilesByPrice(List<Automobile> automobiles, boolean ascending) {
        if (ascending) {
            automobiles.sort(Comparator.comparingInt(Automobile::getPrice));
        } else {
            automobiles.sort(Comparator.comparingInt(Automobile::getPrice).reversed());
        }
    }
}
