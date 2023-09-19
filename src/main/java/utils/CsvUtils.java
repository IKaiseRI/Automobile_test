package utils;

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
    public static List<TechnicalCharacteristic> readTechnicalCharacteristicCsv(String filePath) {
        List<TechnicalCharacteristic> techCharacteristics = new ArrayList<>();

        try (
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
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
                    techCharacteristics.add(new TechnicalCharacteristic(id, maxSpeed, engineVolume, SeatNumber.getSeatNumberFromInteger(seatNumber)));
                }
            }
        }

        return techCharacteristics;
    }
}
