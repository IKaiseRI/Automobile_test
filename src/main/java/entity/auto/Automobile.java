package entity.auto;

import constant.AutomobileConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Automobile {
    private String brand;
    private int price;
    private BodyType bodyType;
    private TechnicalCharacteristic technicalCharacteristic;

    public void setPrice(int price) {
        if (price <= AutomobileConstant.MIN_AUTOMOBILE_PRICE) {
            throw new IllegalArgumentException(
                    new StringBuilder().append("The price should not be lower than ")
                            .append(AutomobileConstant.MIN_AUTOMOBILE_PRICE).append(" euros")
                            .toString()
            );
        }
        this.price = price;
    }
}
