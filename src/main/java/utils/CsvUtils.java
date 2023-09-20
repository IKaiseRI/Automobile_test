package utils;

import constant.CsvPathConstant;
import entity.auto.Automobile;
import entity.auto.SeatNumber;
import entity.auto.TechnicalCharacteristic;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {

    @SneakyThrows
    private static List<TechnicalCharacteristic> getTechnicalCharacteristicCsv() {
        List<TechnicalCharacteristic> techCharacteristics = new ArrayList<>();

        try (
                Reader reader = Files.newBufferedReader(Paths.get(CsvPathConstant.TECHNICAL_CHARACTERISTIC_CSV_PATH));
                CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim())
        ) {
            List<String> headers = parser.getHeaderNames();

            for (CSVRecord record : parser) {
                int id = Integer.parseInt(record.get(headers.indexOf("id")));
                int maxSpeed = Integer.parseInt(record.get(headers.indexOf("max-speed")));
                float engineVolume = Float.parseFloat(record.get(headers.indexOf("engine-volume")));
                int seatNumber = Integer.parseInt(record.get(headers.indexOf("seat-number")));

                if (TechnicalCharacteristic.isTechnicalCharacteristicValid(maxSpeed, engineVolume, seatNumber)) {
                    techCharacteristics.add(
                            new TechnicalCharacteristic(id, maxSpeed, engineVolume, SeatNumber.getSeatNumberFromInteger(seatNumber))
                    );
                }
            }
        }
        return techCharacteristics;
    }

    @SneakyThrows
    public static List<Automobile> getAutomobileCsv() {
        List<Automobile> automobileList = new ArrayList<>();
        List<TechnicalCharacteristic> technicalCharacteristicList = getTechnicalCharacteristicCsv();

        try (
                Reader reader = Files.newBufferedReader(Paths.get(CsvPathConstant.AUTOMOBILE_CSV_PATH));
                CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim())
        ) {
            List<String> headers = parser.getHeaderNames();

            for (CSVRecord record : parser) {
                int id = Integer.parseInt(record.get(headers.indexOf("id")));
                String brand = record.get(headers.indexOf("brand"));
                int price = Integer.parseInt(record.get(headers.indexOf("price")));
                String bodyType = record.get(headers.indexOf("body-type"));
                int techId = Integer.parseInt(record.get(headers.indexOf("technical-characteristic-id")));

                if (Automobile.isValidAutomobile(price, bodyType) &&
                        AutomobileUtils.isTechnicalCharacteristicIdValid(technicalCharacteristicList, techId)
                ) {
                    automobileList.add(
                            new Automobile(
                                    id, brand, price, bodyType,
                                    technicalCharacteristicList.stream()
                                            .filter(technicalCharacteristic -> technicalCharacteristic.getId() == techId)
                                            .findAny().get()
                            )
                    );
                }
            }
        }
        return automobileList;
    }
}
