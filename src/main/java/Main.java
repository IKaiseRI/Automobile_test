import constant.CsvPathConstant;
import entity.auto.TechnicalCharacteristic;
import utils.CsvUtils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<TechnicalCharacteristic> techCharacteristics = CsvUtils.readTechnicalCharacteristicCsv(CsvPathConstant.TECHNICAL_CHARACTERISTIC_CSV_PATH);

        techCharacteristics.forEach(System.out::println);
    }
}
