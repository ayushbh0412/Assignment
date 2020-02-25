package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class IOUtil {
    public static BufferedReader br;

    public static BufferedReader getBufferedReader() {
        if (br == null) {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        return br;
    }
}
