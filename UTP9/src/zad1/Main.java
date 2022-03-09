package zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        List<Item> items = Collections.synchronizedList(new ArrayList<>());
        AtomicInteger sum = new AtomicInteger();
        sum.set(0);

        Thread addThread = new Thread(() -> {
            try {
                FileReader fr = new FileReader("Towary.txt");
                BufferedReader br = new BufferedReader(fr);
                String line;
                int counter = 0;
                while ((line = br.readLine()) != null) {
                    int id = Integer.parseInt(line.split(" ")[0]);
                    int weight = Integer.parseInt(line.split(" ")[1]);
                    items.add(new Item(id, weight));
                    counter++;
                    if (counter % 200 == 0) {
                        System.out.println("utworzono " + counter + " obiektów");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread sumThread = new Thread(() -> {
            Integer lastId, startId;
            lastId = -1;
            while (true) {
                for (int i = 0; i < items.size(); i++) {
                    if (i > lastId) {
                        sum.addAndGet(items.get(i).weight);
                        if ((i + 1) % 100 == 0)
                            System.out.println("policzono wage " + (i + 1) + " produktów");
                        lastId = i;
                    }
                }
                if (lastId == items.size()-1 && !addThread.isAlive()) {
                    break;
                }
            }
            System.out.println(sum);
        });

        addThread.start();

        sumThread.start();
    }
}
