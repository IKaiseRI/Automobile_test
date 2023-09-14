package exception;

public class ExceptionMessage {

    public static String getExceptionText(String characteristic, Object minimal, Object maximal) {
        return new StringBuilder()
                .append("The ").append(characteristic).append(" should be between ")
                .append(minimal).append(" and ").append(maximal)
                .toString();
    }
}
