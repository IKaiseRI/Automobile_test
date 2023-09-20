import utils.DbUtils;

public class Main {
    public static void main(String[] args) {
       // Triggers the reading from the CSV files and inserts into DB
        DbUtils.insertIntoDatabase();
    }
}
