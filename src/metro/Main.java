package metro;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        File file;
        file = new File(fileName);
        Engine.readFile(file);


    }
}
