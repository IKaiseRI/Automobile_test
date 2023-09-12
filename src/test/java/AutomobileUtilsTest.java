import entity.Automobile;
import entity.BodyType;
import entity.SeatNumber;
import entity.TechnicalCharacteristic;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static constants.AutomobileConstants.*;
import static org.junit.Assert.assertEquals;

public class AutomobileUtilsTest {
    private final static List<Automobile> automobileList = new ArrayList<>();

    @BeforeClass
    public static void initializingTestList() {
        automobileList.addAll(
                List.of(
                        new Automobile(
                                PORSCHE_911,
                                100_000,
                                BodyType.SPORT,
                                new TechnicalCharacteristic(360, 3.0f, SeatNumber.TWO)
                        ),
                        new Automobile(
                                SKODA_KODIAQ,
                                30_000,
                                BodyType.SUV,
                                new TechnicalCharacteristic(200, 2.0f, SeatNumber.FIVE
                                )
                        ),
                        new Automobile(
                                TOYOTA_CAMRY,
                                40_000,
                                BodyType.SEDAN,
                                new TechnicalCharacteristic(250, 2.5f, SeatNumber.FOUR
                                )
                        ),
                        new Automobile(
                                KOENIGSEGG_ONE,
                                1_000_000,
                                BodyType.SPORT,
                                new TechnicalCharacteristic(500, 5.0f, SeatNumber.ONE
                                )
                        )
                )
        );
    }

    @Test
    public void testCalculateAnnualRoadTaxSUV() {
        Automobile suv = automobileList.stream()
                .filter(automobile -> automobile.getBodyType().equals(BodyType.SUV))
                .findAny()
                .orElse(new Automobile(
                                SKODA_KODIAQ,
                                30_000,
                                BodyType.SUV,
                                new TechnicalCharacteristic(200, 2.0f, SeatNumber.FIVE)
                        )
                );
        double tax = AutomobileUtils.calculateAnnualRoadTax(suv);

        assertEquals(3_000.0, tax, 0.01);
    }

    @Test
    public void testCalculateAnnualRoadTaxSedan() {
        Automobile sedan = automobileList.stream()
                .filter(automobile -> automobile.getBodyType().equals(BodyType.SEDAN))
                .findAny()
                .orElse(new Automobile(
                                TOYOTA_CAMRY,
                                40_000,
                                BodyType.SEDAN,
                                new TechnicalCharacteristic(250, 2.5f, SeatNumber.FOUR)
                        )
                );
        double tax = AutomobileUtils.calculateAnnualRoadTax(sedan);

        assertEquals(2800.0, tax, 0.01);
    }

    @Test
    public void testCalculateAnnualRoadTaxDefault() {
        Automobile sport = automobileList.stream()
                .filter(automobile -> automobile.getBodyType().equals(BodyType.SPORT))
                .findAny()
                .orElse(new Automobile(
                                PORSCHE_911,
                                100_000,
                                BodyType.SPORT,
                                new TechnicalCharacteristic(350, 3.0f, SeatNumber.TWO)
                        )
                );
        double tax = AutomobileUtils.calculateAnnualRoadTax(sport);

        assertEquals(12_000, tax, 0.01);
    }

    @Test
    public void testCompareByMaxSpeed() {
        Automobile auto1 = automobileList.get(0);
        Automobile auto2 = automobileList.get(1);
        int result = AutomobileUtils.compareByMaxSpeed(auto1, auto2);

        assertEquals(1, result); // auto1 should be slower than auto2
    }

    @Test
    public void testFindAutomobilesByType() {
        List<Automobile> sedans = AutomobileUtils.findAutomobilesByType(automobileList, BodyType.SEDAN);

        assertEquals(1, sedans.size());
    }

    @Test
    public void testCalculateFuelEfficiency() {
        double efficiency = AutomobileUtils.calculateFuelEfficiency(automobileList.get(0));

        assertEquals(120.0, efficiency, 0.01);
    }

    @Test
    public void testGetSeatingCapacity() {
        SeatNumber seatNumber = AutomobileUtils.getSeatingCapacity(automobileList.get(0));

        assertEquals(SeatNumber.TWO, seatNumber);
    }

    @Test
    public void testGetAutomobileDetails() {
        Automobile automobile = automobileList.stream()
                .filter(auto -> auto.getBrand().equals(PORSCHE_911))
                .findAny()
                .orElse(new Automobile(
                                PORSCHE_911,
                                100_000,
                                BodyType.SPORT,
                                new TechnicalCharacteristic(350, 3.0f, SeatNumber.TWO)
                        )
                );
        String details = AutomobileUtils.getAutomobileDetails(automobile);
        String expectedDetails = """
                Brand: Porsche 911
                Price: 100000
                BodyType: SPORT
                Max Speed: 360
                Engine Volume: 3.0
                Seating Capacity: TWO""";

        assertEquals(expectedDetails, details);
    }

    @Test
    public void testSortAutomobilesByPrice() {
        AutomobileUtils.sortAutomobilesByPrice(automobileList, true);

        assertEquals(SKODA_KODIAQ, automobileList.get(0).getBrand());
        assertEquals(TOYOTA_CAMRY, automobileList.get(1).getBrand());
        assertEquals(PORSCHE_911, automobileList.get(2).getBrand());
        assertEquals(KOENIGSEGG_ONE, automobileList.get(3).getBrand());
    }
}
