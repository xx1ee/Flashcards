import java.util.*;

class MapUtils {

    public static SortedMap<String, Integer> wordCount(String[] strings) {
        SortedMap<String, Integer> map1 = new TreeMap<>();
        for (int i = 0; i < strings.length; i++) {
            if (map1.containsKey(strings[i]) == false) {
                map1.put(strings[i], 1);
            } else {
                int y = map1.get(strings[i]);
                y = y + 1;
                map1.put(strings[i], y);
            }
        }
        return map1;
    }

    public static void printMap(Map<String, Integer> map) {
        for (String key : map.keySet()) System.out.println(key + " : " + map.get(key));
    }

}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split(" ");
        MapUtils.printMap(MapUtils.wordCount(words));
    }
}