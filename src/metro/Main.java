package metro;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String fileName = args[0];
        Pattern pattern = Pattern.compile("\\b.+(?<!\\.)\\.json\\b");
        Matcher matcher = pattern.matcher(fileName);
        if (!matcher.matches()) {
            System.out.println("Incorrect file");
            System.exit(0);
        } else {
            fileName = String.format(".\\%s",fileName);
        }
        Engine.readFile(fileName);


    }
}
