package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static constants.AutomobileConstants.MIN_AUTOMOBILE_PRICE;

@Getter
@Setter
@AllArgsConstructor
public class Automobile {
    private String brand;
    private int price;
    private BodyType bodyType;
    private TechnicalCharacteristic characteristic;

    public void setPrice(int price) {
        if(price <= MIN_AUTOMOBILE_PRICE){
            throw new IllegalArgumentException(
                    new StringBuilder().append("The price should not be lower than ")
                            .append(MIN_AUTOMOBILE_PRICE).append(" euros")
                            .toString()
            );
        }
        this.price = price;
    }
}
