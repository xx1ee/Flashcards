import java.lang.reflect.Array;
import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String w1 = sc.nextLine();
        List<String> myList = new ArrayList<String>(Arrays.asList(w1.split(" ")));
        String w2 = sc.nextLine();
        List<String> myList1 = new ArrayList<String>(Arrays.asList(w2.split(" ")));
        if (myList.containsAll(myList1) && myList.size() > myList1.size()) {
            System.out.println("You get money");
        } else {
            System.out.println("You are busted");
        }
    }
}