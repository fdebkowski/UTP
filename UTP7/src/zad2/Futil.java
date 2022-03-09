package zad2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Futil {
    public static void processDir(String dirName, String resultFileName) {
        try {
            Path mainDir;
            mainDir = Paths.get(dirName);
            FVisitor FVisitor = new FVisitor(Paths.get("./" + resultFileName));
            Files.walkFileTree(mainDir, FVisitor);
            FVisitor.cleanup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}