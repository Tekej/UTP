package zad2;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Futil {
    public static void processDir(String dirName, String resultFileName) {
        File resultFile = new File(resultFileName);
        try {
            if (resultFile.exists()) {
                resultFile.delete();
                resultFile.createNewFile();
            } else {
                resultFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(resultFileName)), StandardCharsets.UTF_8))) {
            Files.walk(Paths.get(dirName)).filter(Files::isRegularFile).forEach(path -> {
                try {
                    Files.lines(path, Charset.forName("Cp1250"))
                            .forEach(s -> {
                                try {
                                    bw.write(s + "\n");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        bw.flush();
                        bw.close();
                    } catch (IOException ignored) {
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
