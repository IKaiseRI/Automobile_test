package constant;

import static constant.DbConstant.*;

public class QueryConstant {
    public static String INSERT_INTO_TECHNICAL_CHARACTERISTIC_TABLE =
            new StringBuilder()
                    .append("INSERT INTO ")
                    .append(TECHNICAL_CHARACTERISTIC_TABLE).append(" (")
                    .append(ID_COLUMN).append(", ")
                    .append(MAX_SPEED_COLUMN).append(", ")
                    .append(ENGINE_VOLUME_COLUMN).append(", ")
                    .append(SEAT_NUMBER_COLUMN).append(") ")
                    .append("VALUES")
                    .toString();

    public static String INSERT_INTO_AUTOMOBILE_TABLE =
            new StringBuilder()
                    .append("INSERT INTO ")
                    .append(AUTOMOBILE_TABLE).append(" (")
                    .append(ID_COLUMN).append(", ")
                    .append(BRAND_COLUMN).append(", ")
                    .append(PRICE_COLUMN).append(", ")
                    .append(BODY_TYPE_COLUMN).append(", ")
                    .append(TECHNICAL_CHARACTERISTIC_ID_COLUMN).append(") ")
                    .append("VALUES")
                    .toString();
}
