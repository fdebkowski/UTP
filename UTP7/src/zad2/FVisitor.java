package zad2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class FVisitor implements FileVisitor<Path> {
    private final BufferedWriter writer;

    public FVisitor(Path outP) throws IOException {
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outP.toFile()), StandardCharsets.UTF_8));
    }

    public void cleanup() throws IOException {
        writer.close();
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes bFAttrs) throws IOException {
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(new FileInputStream(path.toString()), "cp1250"));

        String ln;

        if ((ln = br.readLine()) != null) {
            do {
                writer.write(ln);
                writer.newLine();
            } while ((ln = br.readLine()) != null);
        }

        br.close();

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path path, IOException e) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes bFAttrs) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path path, IOException e) {
        return FileVisitResult.CONTINUE;
    }
}