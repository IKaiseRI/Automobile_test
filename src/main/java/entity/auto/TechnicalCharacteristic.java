package entity.auto;

import exception.ExceptionMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static constant.AutomobileTechnicalConstant.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TechnicalCharacteristic {
    private int id;
    private int maxSpeed;
    private float engineVolume;
    private SeatNumber seatNumber;

    public TechnicalCharacteristic(int maxSpeed, float engineVolume, SeatNumber seatNumber) {
        this.maxSpeed = maxSpeed;
        this.engineVolume = engineVolume;
        this.seatNumber = seatNumber;
    }

    public void setMaxSpeed(int maxSpeed) {
        if (!isSpeedValid(maxSpeed)) {
            throw new IllegalArgumentException(
                    ExceptionMessage.getCharacteristicExceptionText("speed", MAX_SPEED, MIN_SPEED)
            );
        }
        this.maxSpeed = maxSpeed;
    }

    public static boolean isSpeedValid(int maxSpeed) {
        return maxSpeed >= MIN_SPEED && maxSpeed <= MAX_SPEED;
    }

    public void setSeatNumber(int automobileSeatNumber) {
        if (SeatNumber.isValidSeatNumber(automobileSeatNumber)) {
            this.seatNumber = SeatNumber.getSeatNumberFromInteger(automobileSeatNumber);
        }
    }

    public void setEngineVolume(float engineVolume) {
        if (!isEngineVolumeValid(engineVolume)) {
            throw new IllegalArgumentException(
                    ExceptionMessage.getCharacteristicExceptionText("engine volume", MIN_ENGINE_VOLUME, MAX_ENGINE_VOLUME)
            );
        }
        this.engineVolume = engineVolume;
    }

    public static boolean isEngineVolumeValid(float engineVolume) {
        return engineVolume >= MIN_ENGINE_VOLUME && engineVolume <= MAX_ENGINE_VOLUME;
    }

    public static boolean isTechnicalCharacteristicValid(int maxSpeed, float engineVolume, int seatNumber) {
        return isSpeedValid(maxSpeed) && isEngineVolumeValid(engineVolume) && SeatNumber.isValidSeatNumber(seatNumber);
    }
}
