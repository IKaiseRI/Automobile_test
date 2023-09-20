package entity.auto;

import constant.AutomobileConstant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Automobile {
    private int id;
    private String brand;
    private int price;
    private BodyType bodyType;
    private TechnicalCharacteristic technicalCharacteristic;

    public Automobile(int id, String brand, int price, String bodyType, TechnicalCharacteristic technicalCharacteristic) {
        this.id = id;
        this.brand = brand;
        this.price = price;
        setBodyType(bodyType);
        this.technicalCharacteristic = technicalCharacteristic;
    }

    public Automobile(String brand, int price, BodyType bodyType, TechnicalCharacteristic technicalCharacteristic) {
        this.brand = brand;
        this.price = price;
        this.bodyType = bodyType;
        this.technicalCharacteristic = technicalCharacteristic;
    }

    public void setPrice(int price) {
        if (!isValidPrice(price)) {
            throw new IllegalArgumentException(
                    new StringBuilder().append("The price should not be lower than ")
                            .append(AutomobileConstant.MIN_AUTOMOBILE_PRICE).append(" euros")
                            .toString()
            );
        }
        this.price = price;
    }

    private static boolean isValidPrice(int price) {
        return price >= AutomobileConstant.MIN_AUTOMOBILE_PRICE;
    }

    public void setBodyType(String bodyType) {
        if (isValidBodyType(bodyType)) {
            this.bodyType = BodyType.valueOf(bodyType);
        } else {
            throw new IllegalArgumentException(
                    new StringBuilder().append("The provided BodyType: ")
                            .append(bodyType).append(" is not valid")
                            .toString()
            );
        }
    }

    private static boolean isValidBodyType(String bodyType) {
        return List.of(BodyType.values()).contains(BodyType.valueOf(bodyType));
    }

    public static boolean isValidAutomobile(int price, String bodyType) {
        return isValidPrice(price) && isValidBodyType(bodyType);
    }
}
