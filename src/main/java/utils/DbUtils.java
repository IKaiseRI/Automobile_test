package utils;

import constant.DbConstant;
import constant.QueryConstant;
import entity.auto.Automobile;
import entity.auto.TechnicalCharacteristic;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class DbUtils {

    @SneakyThrows
    public static void insertIntoDatabase() {
        List<Automobile> automobileList = CsvUtils.getAutomobileCsv();

        try (
                Connection connection = DriverManager.getConnection(DbConstant.URL, DbConstant.USER, DbConstant.PASSWORD);
                Statement statement = connection.createStatement()
        ) {
            for (Automobile automobile : automobileList) {
                TechnicalCharacteristic technicalCharacteristic = automobile.getTechnicalCharacteristic();

                statement.execute(
                        new StringBuilder()
                                .append(QueryConstant.INSERT_INTO_TECHNICAL_CHARACTERISTIC_TABLE).append(" (")
                                .append(technicalCharacteristic.getId()).append(", ")
                                .append(technicalCharacteristic.getMaxSpeed()).append(", ")
                                .append(technicalCharacteristic.getEngineVolume()).append(", ")
                                .append(technicalCharacteristic.getSeatNumber().getValue()).append(")")
                                .toString()
                );

                statement.execute(
                        new StringBuilder()
                                .append(QueryConstant.INSERT_INTO_AUTOMOBILE_TABLE).append(" (")
                                .append(automobile.getId()).append(", ")
                                .append("'").append(automobile.getBrand()).append("'").append(", ")
                                .append(automobile.getPrice()).append(", ")
                                .append("'").append(automobile.getBodyType()).append("'").append(", ")
                                .append(technicalCharacteristic.getId()).append(")")
                                .toString()
                );
            }
        }
    }
}
