package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static constants.TechnicalConstants.*;

@Getter
@AllArgsConstructor
public class TechnicalCharacteristic {
    private int maxSpeed;
    private float engineVolume;
    private SeatNumber numberOfSeats;

    public void setMaxSpeed(int maxSpeed) {
        if (maxSpeed <= MIN_SPEED || maxSpeed >= MAX_SPEED) {
            throw new IllegalArgumentException(getExceptionText("speed", MAX_SPEED, MIN_SPEED));
        }
        this.maxSpeed = maxSpeed;
    }

    public void setEngineVolume(float engineVolume) {
        if (engineVolume < MIN_ENGINE_VOLUME || engineVolume > MAX_ENGINE_VOLUME) {
            throw new IllegalArgumentException(getExceptionText("engine volume", MIN_ENGINE_VOLUME, MAX_ENGINE_VOLUME));
        }
        this.engineVolume = engineVolume;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        if (SeatNumber.isValidSeatNumber(numberOfSeats))
            this.numberOfSeats = SeatNumber.getSeatNumberFromInteger(numberOfSeats);
    }

    private String getExceptionText(String characteristic, Object minimal, Object maximal) {
        return new StringBuilder()
                .append("The ").append(characteristic).append(" should be between ")
                .append(minimal).append(" and ").append(maximal)
                .toString();
    }
}
