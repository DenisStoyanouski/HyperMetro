package metro;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String fileName = args[0];
        Pattern pattern = Pattern.compile(".+(?<!\\.)\\.json\\b");
        Matcher matcher = pattern.matcher(fileName);
        File file = null;
        if (!matcher.matches()) {
            System.out.println("Incorrect file");
            System.exit(0);
        } else {
            fileName = fileName.replaceAll("\\/", "\\\\").replaceFirst("\\.", ".\\\\HyperMetro\\\\task");
            file = new File(fileName);
        }
        Engine.readFile(file);


    }
}
