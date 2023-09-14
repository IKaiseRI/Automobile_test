package entity.auto;

import java.util.Arrays;

public enum SeatNumber {
    ONE(1),
    TWO(2),
    FOUR(4),
    FIVE(5);

    private final int number;

    SeatNumber(int number) {
        this.number = number;
    }

    public static SeatNumber getSeatNumberFromInteger(int numberOfSeats) {
        return Arrays.stream(SeatNumber.values())
                .filter(seatNumber -> seatNumber.number == numberOfSeats)
                .findAny()
                .orElse(FOUR);
    }

    public static boolean isValidSeatNumber(int numberOfSeats) {
        return Arrays.stream(SeatNumber.values())
                .anyMatch(seatNum -> seatNum.number == numberOfSeats);
    }
}
