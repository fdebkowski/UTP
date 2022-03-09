package zad2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FVisitor{
    private final BufferedWriter writer;

    public FVisitor(Path outP) throws IOException {
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outP.toFile()), StandardCharsets.UTF_8));
    }

    public void cleanup() throws IOException {
        writer.close();
    }

    public void visitFile(String path) throws IOException {
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "cp1250"));

        String ln;

        if ((ln = br.readLine()) != null) {
            do {
                writer.write(ln);
                writer.newLine();
            } while ((ln = br.readLine()) != null);
        }

        br.close();
    }

    public List<String> listAllFiles(String dirName) {

        Path start = Paths.get(dirName);
        List<String> collect;

        try (Stream<Path> stream = Files.walk(start, Integer.MAX_VALUE)) {
            collect = stream
                    .filter(Files::isRegularFile)
                    .map(String::valueOf)
                    .sorted()
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            collect = new ArrayList<>();
        }

        return collect;
    }
}