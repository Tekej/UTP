package zad1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Futil implements FileVisitor<Path> {
    BufferedWriter bufferW;

    public Futil(BufferedWriter bufferW) {
        this.bufferW = bufferW;
    }

    public static void processDir(String dirName, String resultFileName) {
        BufferedWriter bw = null;
        try {
            File resultFile = new File(resultFileName);
            if (resultFile.exists()) {
                resultFile.delete();
                resultFile.createNewFile();
            } else {
                resultFile.createNewFile();
            }

            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resultFile, true), StandardCharsets.UTF_8));

            Files.walkFileTree(new File(dirName).toPath(), new Futil(bw));

        } catch (IOException ignored) {
        } finally {
            try {
                bw.flush();
                bw.close();
            } catch (IOException ignored) {
            }
        }
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file.toFile()), "Cp1250"));
        String s;
        while ((s = br.readLine()) != null) {
            bufferW.write(s);
            bufferW.newLine();
        }
        return CONTINUE;
    }
}
