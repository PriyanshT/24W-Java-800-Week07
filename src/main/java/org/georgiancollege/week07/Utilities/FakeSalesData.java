package org.georgiancollege.week07.Utilities;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Formatter;

public class FakeSalesData {
    public static void createSQL(){
        SecureRandom secureRandom = new SecureRandom();

        // try with resource block
        try(
                Formatter formatter = new Formatter("fakeData.sql");
                ) {
            // Example - INSERT INTO bookSales(book_id, sold_date) VALUES (1, "2024-02-15");
            for (int i = 0; i < 500; i++) {
                int bookId = secureRandom.nextInt(1, 13);
                LocalDate dateSold = LocalDate.now().minusDays(secureRandom.nextInt(365));
                formatter.format("INSERT INTO bookSales(book_id, sold_date) VALUES (%d, '%s');\n", bookId, dateSold);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        createSQL();
//    }
}
