package utils;

import entity.auto.Automobile;
import entity.auto.BodyType;
import entity.auto.SeatNumber;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AutomobileUtils {

    public static double calculateAnnualRoadTax(Automobile automobile) {
        switch (automobile.getBodyType()) {
            case SUV -> {
                if (automobile.getTechnicalCharacteristic().getSeatNumber() == SeatNumber.FIVE) {
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
                auto1.getTechnicalCharacteristic().getMaxSpeed(),
                auto2.getTechnicalCharacteristic().getMaxSpeed()
        );
    }

    public static List<Automobile> findAllByType(List<Automobile> automobiles, BodyType bodyType) {
        return automobiles.stream()
                .filter(automobile -> automobile.getBodyType().equals(bodyType))
                .collect(Collectors.toList());
    }

    public static double calculateFuelEfficiency(Automobile automobile) {
        return automobile.getTechnicalCharacteristic().getMaxSpeed() /
                automobile.getTechnicalCharacteristic().getEngineVolume();
    }

    public static SeatNumber getSeatingCapacity(Automobile automobile) {
        return automobile.getTechnicalCharacteristic().getSeatNumber();
    }

    public static String getDetails(Automobile automobile) {
        return new StringBuilder()
                .append("Brand: ").append(automobile.getBrand())
                .append("\nPrice: ").append(automobile.getPrice())
                .append("\nBodyType: ").append(automobile.getBodyType())
                .append("\nMax Speed: ").append(automobile.getTechnicalCharacteristic().getMaxSpeed())
                .append("\nEngine Volume: ").append(automobile.getTechnicalCharacteristic().getEngineVolume())
                .append("\nSeating Capacity: ").append(getSeatingCapacity(automobile))
                .toString();
    }

    public static void sortAllByPrice(List<Automobile> automobiles, boolean ascending) {
        if (ascending) {
            automobiles.sort(Comparator.comparingInt(Automobile::getPrice));
        } else {
            automobiles.sort(Comparator.comparingInt(Automobile::getPrice).reversed());
        }
    }
}
