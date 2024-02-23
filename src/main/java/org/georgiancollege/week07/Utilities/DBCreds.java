package org.georgiancollege.week07.Utilities;

import java.nio.file.Files;
import java.nio.file.Path;

public class DBCreds {
    public static String findUser() {
        try {
            Path filePath = Path.of("C:/OneDrive - Georgian College/Desktop/Java 800/Creds/user.txt");
            return Files.readString(filePath);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String findPass() {
        try {
            Path filePath = Path.of("C:/OneDrive - Georgian College/Desktop/Java 800/Creds/pass.txt");
            return Files.readString(filePath);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
