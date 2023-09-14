package entity.auto;

import exception.ExceptionMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static constant.AutomobileTechnicalConstant.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TechnicalCharacteristic {
    private int maxSpeed;
    private float engineVolume;
    private SeatNumber seatNumber;

    public void setMaxSpeed(int maxSpeed) {
        if (maxSpeed <= MIN_SPEED || maxSpeed >= MAX_SPEED) {
            throw new IllegalArgumentException(
                    ExceptionMessage.getExceptionText("speed", MAX_SPEED, MIN_SPEED)
            );
        }
        this.maxSpeed = maxSpeed;
    }

    public void setEngineVolume(float engineVolume) {
        if (engineVolume < MIN_ENGINE_VOLUME || engineVolume > MAX_ENGINE_VOLUME) {
            throw new IllegalArgumentException(
                    ExceptionMessage.getExceptionText("engine volume", MIN_ENGINE_VOLUME, MAX_ENGINE_VOLUME)
            );
        }
        this.engineVolume = engineVolume;
    }

    public void setSeatNumber(int automobileSeatNumber) {
        if (SeatNumber.isValidSeatNumber(automobileSeatNumber)) {
            this.seatNumber = SeatNumber.getSeatNumberFromInteger(automobileSeatNumber);
        }
    }
}
