package map;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        /*SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>(10);
        for (int i = 0; i < 8; i++) {
            map.put(i, i);
            //map.put(-i, -i);
            //map.put(i*10, i*10);
        }
        System.out.println(map.show());*/

        SimpleHashMap<Integer, Integer> map2 = new SimpleHashMap<>(10);
        Random r = new Random();
        for (int i = 0; i < 8; i++) {
            map2.put(r.nextInt(), r.nextInt());
        }
        System.out.println("-----");
        System.out.println(map2.show());
    }
}
