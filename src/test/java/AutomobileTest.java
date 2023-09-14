import entity.auto.Automobile;
import entity.auto.BodyType;
import entity.auto.SeatNumber;
import entity.auto.TechnicalCharacteristic;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.AutomobileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static constant.AutomobileConstant.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AutomobileTest {
    private static final List<Automobile> automobileList = new ArrayList<>();

    @BeforeAll
    public static void initialise_list_of_automobiles() {
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
    public void given_first_automobile_seat_number_when_getting_number_then_assert_number() {
        assertEquals(SeatNumber.TWO, AutomobileUtils.getSeatingCapacity(automobileList.get(0)));
    }

    @Test
    public void given_two_automobiles_when_comparing_max_speed_then_should_assert_result_value() {
        assertEquals(1, AutomobileUtils.compareByMaxSpeed(automobileList.get(0), automobileList.get(1)));
    }

    @Test
    public void given_automobiles_list_when_getting_new_list_by_type_then_should_assert_list_size() {
        assertEquals(1, AutomobileUtils.findAllByType(automobileList, BodyType.SEDAN).size());
    }

    @Test
    public void given_automobile_fuel_when_getting_efficiency_then_should_assert_efficiency() {
        assertEquals(120.0, AutomobileUtils.calculateFuelEfficiency(automobileList.get(0)));
    }

    @Test
    public void given_speed_value_when_getting_valid_value_then_assert_speed() {
        TechnicalCharacteristic automobileMaxSpeed = new TechnicalCharacteristic();
        automobileMaxSpeed.setMaxSpeed(200);

        assertEquals(200, automobileMaxSpeed.getMaxSpeed());
    }

    @Test
    public void given_engine_volume_when_getting_valid_value_then_assert_engine_volume() {
        TechnicalCharacteristic automobileEngineVolume = new TechnicalCharacteristic();
        automobileEngineVolume.setEngineVolume(2.0f);

        assertEquals(2.0f, automobileEngineVolume.getEngineVolume());
    }

    @Test
    public void given_suv_automobile_when_calculating_annual_road_tax_then_should_assert_tax_value() {
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

        assertEquals(3_000.0, AutomobileUtils.calculateAnnualRoadTax(suv));
    }

    @Test
    public void given_sedan_automobile_when_calculating_annual_road_tax_then_should_assert_tax_value() {
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

        assertEquals(2_800.0, AutomobileUtils.calculateAnnualRoadTax(sedan), 0.01);
    }

    @Test
    public void given_default_automobile_when_calculating_annual_road_tax_then_should_assert_tax_value() {
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

        assertEquals(12_000, AutomobileUtils.calculateAnnualRoadTax(sport));
    }

    @Test
    public void given_automobile_info_when_getting_all_details_then_should_assert_expected_details() {
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

        assertEquals("""
                        Brand: Porsche 911
                        Price: 100000
                        BodyType: SPORT
                        Max Speed: 360
                        Engine Volume: 3.0
                        Seating Capacity: TWO""",
                AutomobileUtils.getDetails(automobile)
        );
    }

    @Test
    public void given_automobiles_list_when_getting_sorted_list_then_assert_sorted_list() {
        List<Automobile> copyOfAutomobileList = new ArrayList<>(automobileList);
        AutomobileUtils.sortAllByPrice(copyOfAutomobileList, true);

        assertEquals(
                List.of(
                        SKODA_KODIAQ,
                        TOYOTA_CAMRY,
                        PORSCHE_911,
                        KOENIGSEGG_ONE
                ),
                copyOfAutomobileList.stream()
                        .map(Automobile::getBrand)
                        .collect(Collectors.toList())
        );
    }

    @Test
    public void given_number_of_seats_when_getting_valid_value_then_assert_number_of_seats() {
        TechnicalCharacteristic automobileNumberOfSeats = new TechnicalCharacteristic();

        automobileNumberOfSeats.setSeatNumber(
                Arrays.asList(1, 2, 4, 5)
                        .get(new Random().nextInt(Arrays.asList(1, 2, 4, 5).size()))
        );

        assertTrue(
                List.of(
                        SeatNumber.ONE,
                        SeatNumber.TWO,
                        SeatNumber.FOUR,
                        SeatNumber.FIVE
                ).contains(automobileNumberOfSeats.getSeatNumber())
        );
    }
}
