import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectionTest {

    @Test
    public void readNormal(){
        database_connection dc = new database_connection();
        dc.readFromDatabase(
                "student_info",
                new String[]{"student_ID","student_name", "student_password"},
                1,
                "normal"
        );

        // Access the data map using the getter method.
        Map<Integer, Map<String, Object>> dataMap = dc.getDataMap();

        // Print the data map.
        if (dataMap != null) {
            for (Map.Entry<Integer, Map<String, Object>> entry : dataMap.entrySet()) {
                int rowId = entry.getKey();
                Map<String, Object> rowData = entry.getValue();
                System.out.println("Row ID: " + rowId);
                for (Map.Entry<String, Object> dataEntry : rowData.entrySet()) {
                    String columnName = dataEntry.getKey();
                    Object value = dataEntry.getValue();
                    System.out.println(columnName + ": " + value);
                }
                System.out.println();
            }
        }

        assertNotNull(dataMap); // Ensure dataMap is not null
        assertFalse(dataMap.isEmpty()); // Ensure dataMap is not empty
    }

    @Test
    public void readSpecific(){
        database_connection dc = new database_connection();
        dc.readFromDatabase(
                "student_info",
                new String[]{"student_ID","student_name", "student_password"},
                1,
                "specific"
        );

        // Access the data map using the getter method.
        Map<Integer, Map<String, Object>> dataMap = dc.getDataMap();

        // Print the data map.
        if (dataMap != null) {
            for (Map.Entry<Integer, Map<String, Object>> entry : dataMap.entrySet()) {
                int rowId = entry.getKey();
                Map<String, Object> rowData = entry.getValue();
                System.out.println("Row ID: " + rowId);
                for (Map.Entry<String, Object> dataEntry : rowData.entrySet()) {
                    String columnName = dataEntry.getKey();
                    Object value = dataEntry.getValue();
                    System.out.println(columnName + ": " + value);
                }
                System.out.println();
            }
        }

        assertNotNull(dataMap); // Ensure dataMap is not null
        assertFalse(dataMap.isEmpty()); // Ensure dataMap is not empty
    }

    @Test
    public void writeAdd(){
        database_connection dc = new database_connection();
        HashMap<String, Object> hm = new HashMap<String, Object>();
        // (student_name, student_password, student_email, student_phone_number)
        hm.put("student_name", "mark vivian");
        hm.put("student_password", "1234mark");
        hm.put("student_email", "markvivian@gmail.com");
        hm.put("student_phone_number", "123-456-7890");

        dc.writeToDatabase(
                "student_info",
                "add",
                "student_ID",
                0,
                hm
        );
    }

    @Test
    public void writeDelete(){
        database_connection dc = new database_connection();
        HashMap<String, Object> hm = new HashMap<String, Object>();

        dc.writeToDatabase(
                "student_info",
                "delete",
                "student_ID",
                7,
                hm
        );
    }

    @Test
    public void Update(){
        database_connection dc = new database_connection();
        HashMap<String, Object> hm = new HashMap<String, Object>();
        hm.put("student_password", "vivian1234");
        dc.updateDatabase(
                "student_info",
                "student_ID",
                4,
                hm
        );
    }
}
