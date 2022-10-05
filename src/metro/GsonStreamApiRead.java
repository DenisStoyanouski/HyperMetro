package metro;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GsonStreamApiRead {

        private void read(String fileName) {
            Map<Integer, String> line1 = new HashMap<>();
            Map <Integer, String> line2 = new HashMap<>();
            String line = null;
            String key = null;
            /*String fileName = "src/metro.json";*/

            try (JsonReader reader = new JsonReader(new FileReader(fileName))) {

                while (true) {
                    //We get the type of the next token with the peek method.
                    JsonToken nextToken = reader.peek();
                    if (JsonToken.BEGIN_OBJECT.equals(nextToken)) {
                        reader.beginObject();
                    } else if (JsonToken.NAME.equals(nextToken) && line == null) {
                        line = reader.nextName();
                    } else if (JsonToken.NAME.equals(nextToken)) {
                        key = reader.nextName();
                    } else if (JsonToken.STRING.equals(nextToken)) {
                        String value = reader.nextString();
                        if ("line 1".equals(line) && key != null) {
                            line1.put(Integer.parseInt(key), value);
                        } else if ("line 2".equals(line) && key != null) {
                            line2.put(Integer.parseInt(key), value);
                        }
                        key = null;
                    } else if (JsonToken.END_OBJECT.equals(nextToken)) {
                        reader.endObject();
                        line = null;
                    } else if (JsonToken.NUMBER.equals(nextToken)) {
                        long value = reader.nextLong();
                        System.out.println(value);
                    } else if (JsonToken.END_DOCUMENT.equals(nextToken)) {
                        break;
                    }
                }
                System.out.println("line 1");
                for (var Entry : line1.entrySet()) {
                    System.out.printf("%d : %s%n", Entry.getKey(), Entry.getValue());
                }
                System.out.println("line 2");
                for (var Entry : line2.entrySet()) {
                    System.out.printf("%d : %s%n", Entry.getKey(), Entry.getValue());
                }
            } catch (IOException e) {
                System.out.println("Error! Such a file doesn't exist!");
            }
        }
}
