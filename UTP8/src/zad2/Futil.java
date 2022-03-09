package zad2;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Futil {
    public static void processDir(String dirName, String resultFileName) {
        try {
            FVisitor FVisitor = new FVisitor(Paths.get("./" + resultFileName));

            FVisitor.listAllFiles(dirName).forEach(f -> {
                try {
                    FVisitor.visitFile(f);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });

            FVisitor.cleanup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}