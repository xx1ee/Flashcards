import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Map <Character, Integer> map1 = new HashMap<>();
        Map <Character, Integer> map2 = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        String w1 = (sc.nextLine()).toLowerCase();
        String w2 = (sc.nextLine()).toLowerCase();
        for (int i = 0; i < w1.length(); i++) {
            if (map1.containsKey(w1.charAt(i)) == false) {
                map1.put(w1.charAt(i), 1);
            } else {
                int y = map1.get(w1.charAt(i));
                y = y + 1;
                map1.put(w1.charAt(i), y);
            }
        }
        for (int i = 0; i < w2.length(); i++) {
            if (map2.containsKey(w2.charAt(i)) == false) {
                map2.put(w2.charAt(i), 1);
            } else {
                int y = map2.get(w2.charAt(i));
                y = y + 1;
                map2.put(w2.charAt(i), y);
            }
        }
        int del = 0;
        for (char ch : map1.keySet()) {
            if (map2.containsKey(ch)) {
                del+=Math.abs(map1.get(ch) - map2.get(ch));
            } else {
                del +=map1.get(ch);
                //map1.remove(ch);
            }
        }
        for (char ch : map2.keySet()) {
            if (map1.containsKey(ch) == false) {
                del +=map2.get(ch);
            }
        }
        System.out.println(del);
    }
}