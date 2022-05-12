package flashcards;

import java.io.*;
import java.util.*;
class Cards {
    private final Scanner sc = new Scanner(System.in);
    public StringBuilder log = new StringBuilder();
    private final Map<String, String> map = new LinkedHashMap<String, String>();
    private final Map<String, Integer> error = new LinkedHashMap<String, Integer>();
    private final List hardest_error = new ArrayList();
    public void game(String im, String ex) {
        while (true) {
            if (im != "") {
                import_(im);
            }
            System.out.println("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            log.append("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):\r\n");
            String input = sc.nextLine();
            switch (input) {
                case "add":
                    log.append("add\r\n");
                    add();
                    break;

                case "remove":
                    log.append("remove\r\n");
                    remove();
                    break;

                case "import":
                    log.append("import\r\n");
                    import_(im);
                    break;

                case "export":
                    log.append("export\r\n");
                    export(ex);
                    break;

                case "reset stats":
                    log.append("reset stats\r\n");
                    reset();
                    break;

                case "ask":
                    log.append("ask\r\n");
                    ask();
                    break;

                case "hardest card":
                    log.append("hardest card\r\n");
                    hardest();
                    break;

                case "log":
                    log.append("log\r\n");
                    log1();
                    break;

                case "exit":
                    log.append("exit\r\n");
                    System.out.println("Bye bye!");
                    log.append("Bye bye!");
                    if (ex != "") {
                        export(ex);
                    }
                    return;
            }
        }
    }
    public void add() {
        String term;
        String dest;
        Scanner sc_ = new Scanner(System.in);
        System.out.println("Card:");
        log.append("Card:\r\n");
        term = sc_.nextLine();
        log.append(term + "\r\n");
        if (map.containsKey(term)) {
            System.out.println("The card \"" + term + "\" already exists");
            log.append("The card \"" + term + "\" already exists\r\n");
            return;
        } else {
            System.out.println("The definition of the card:");
            log.append("The definition of the card:\r\n");
            dest = sc_.nextLine();
            log.append(dest + "\r\n");
            if (map.containsValue(dest)) {
                System.out.println("The definition \"" + dest + "\" already exists");
                log.append("The definition \"" + dest + "\" already exists\r\n");
                return;
            } else {
                map.put(term, dest);
                error.put(term, 0);
                System.out.println("The pair (\"" + term + "\":\"" + dest + "\") has been added");
                log.append("The pair (\"" + term + "\":\"" + dest + "\") has been added\r\n");
            }
        }
    }
    public void ask() {
        System.out.println("How many times to ask?");
        log.append("How many times to ask?\r\n");
        int times = sc.nextInt();
        log.append(times + "\r\n");
        int count = 0;
        if (count < times + 1) {
            for (String key : map.keySet()) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Print the definition of \"" + key + "\":");
                log.append("Print the definition of \"" + key + "\":\r\n");
                count++;
                String ans = scanner.nextLine();
                log.append(ans + "\r\n");
                if (map.get(key).equals(ans) == true) {
                    System.out.println("Correct!");
                    log.append("Correct!");
                } else if (map.containsValue(ans) == true) {
                    for (String key1 : map.keySet()) {
                        if ((map.get(key1)).equals(ans)) {
                            System.out.println("Wrong. The right answer is \"" + map.get(key) + "\"" + " but your definition is correct for \"" + key1 + "\".");
                            log.append("Wrong. The right answer is \"" + map.get(key) + "\"" + " but your definition is correct for \"" + key1 + "\".\r\n");
                            int x = error.get(key);
                            x++;
                            error.put(key, x);
                        }
                    }
                } else {
                    System.out.println("Wrong. The right answer is \"" + map.get(key) + "\".");
                    log.append("Wrong. The right answer is \"" + map.get(key) + "\".\r\n");
                    int x = error.get(key);
                    x++;
                    error.put(key, x);
                }
            }
        }
    }
    public void hardest() {
        int max = 0;
        for (String key : error.keySet()) {
            if (error.get(key) > max) {
                max = error.get(key);
            }
        }
        for (String key : error.keySet()) {
            if (error.get(key).equals(max) && max != 0) {
                hardest_error.add(key);
            }
        }
        if (hardest_error.size() == 1 && max != 0) {
            System.out.println("The hardest card is \"" + hardest_error.get(0) + "\". You have "+ max +" errors answering it");
            log.append("The hardest card is \"" + hardest_error.get(0) + "\". You have "+ max +" errors answering it");
        } else {
            if (hardest_error.size() != 0 && max != 0) {
                System.out.print("The hardest cards are ");
                log.append("The hardest cards are ");
                for (int i = 0 ; i < hardest_error.size() ; i++) {
                    if (i >=1) {
                        System.out.print(", ");
                        log.append(", ");
                    }
                    System.out.print("\"" + hardest_error.get(i) + "\"");
                    log.append("\"" + hardest_error.get(i) + "\"");
                }
                System.out.println(". You have " + max + " errors answering them");
                log.append(". You have " + max + " errors answering them\r\n");
            } else {
                System.out.println("There are no cards with errors.");
                log.append("There are no cards with errors.\r\n");
            }
        }
    }
    public void remove() {
        System.out.println("Which card?");
        log.append("Which card?\r\n");
        String term = sc.nextLine();
        if (map.containsKey(term) == true) {
            map.remove(term);
            error.remove(term);
            System.out.println("The card has been removed.");
            log.append("The card has been removed.\r\n");
        } else {
            System.out.println("Can't remove \"" + term + "\": there is no such card.");
            log.append("Can't remove \"" + term + "\": there is no such card.\r\n");
        }
    }
    public void reset() {
        for (String key : error.keySet()) {
            error.put(key, 0);
        }
        System.out.println("Card statistics have been reset.");
        log.append("Card statistics have been reset.\r\n");
    }
    public void log1() {
        System.out.println("File name:");
        log.append("File name:\r\n");
        String pathToFileIn = sc.nextLine();
        log.append(pathToFileIn + "\r\n");
        File fileOut = new File(pathToFileIn);
        try (FileWriter writer = new FileWriter(fileOut)) {
            writer.write(log.toString());
            System.out.println("The log has been saved.");
            log.append("The log has been saved.\r\n");
            writer.write("The log has been saved.\r\n");
            writer.write("\r\n");
        } catch (IOException e) {
            System.out.printf("Error, an exception occurred %s", e.getMessage());
            log.append("Error, an exception occurred "+ e.getMessage() + "\r\n");
        }
    }
    public void import_(String im) {
        String pathToFileIn;
        if (im == "") {
            System.out.println("File name:");
            log.append("File name:\r\n");
            pathToFileIn = sc.nextLine();
        } else {
            pathToFileIn = im;
        }
        log.append(pathToFileIn + "\r\n");
        File fileIn = new File(pathToFileIn);
        int cards = 0;
        try (Scanner scanner = new Scanner(fileIn)) {
            while (scanner.hasNext()) {
                String str = scanner.nextLine();
                String[] arr = str.split(":");
                map.put(arr[0], arr[1]);
                error.put(arr[0], Integer.parseInt(arr[2]));
                cards++;
            }
            System.out.printf("%d cards have been loaded.\n", cards);
            log.append(cards + " cards have been loaded.\n");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            log.append("File not found.\r\n");
        }
    }
    public void export(String ex) {
        String pathToFileOut;
        if (ex == "") {
            System.out.println("File name:");
            log.append("File name:\r\n");
            pathToFileOut = sc.nextLine();
        } else {
            pathToFileOut = ex;
        }
        log.append(pathToFileOut + "\r\n");
        File fileOut = new File(pathToFileOut);
        int cards = 0;
        try (FileWriter writer = new FileWriter(fileOut)) {
            for (var str : map.entrySet()) {
                writer.write(str.getKey() + ":" + str.getValue()  + ":" + error.get(str.getKey()) + "\r\n");
                cards++;
            }
            System.out.printf("%d cards have been saved.\n", cards);
            log.append(cards + " cards have been saved.\r\n");
        } catch (IOException e) {
            System.out.printf("Error, an exception occurred %s", e.getMessage());
            log.append("Error, an exception occurred " + e.getMessage() + "\r\n");
        }
    }
}
public class Main {
    public static void main(String[] args) {
    String im = "";
    String ex = "";
    Cards cards = new Cards();
    for (int i = 0; i < args.length; i++) {
        if (args[i].equals("-import")) {
            im = args[i + 1];
        } else if (args[i].equals("-export")) {
            ex = args[i + 1];
        }
    }
    cards.game(im, ex);
    }
}
