package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOUtil {
    public static BufferedReader br;

    public static BufferedReader getBufferedReader() {
        if (br == null) {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        return br;
    }

    public static int getIntegerInput(BufferedReader br) {
        int num = 0;
        try {
            num = Integer.parseInt(br.readLine());
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        return num;
    }

    public static String getStringInput(BufferedReader br) {
        String data = null;
        try {
            data = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
