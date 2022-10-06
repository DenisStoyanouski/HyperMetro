package metro;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GsonStreamApiRead {

        static void read(File file) {
            String lineName = null;
            String stationName;
            int indexOfStation = 0;
            Line line = null;

            try (JsonReader reader = new JsonReader(new FileReader(file))) {

                while (true) {
                    //We get the type of the next token with the peek method.
                    JsonToken nextToken = reader.peek();
                    if (JsonToken.BEGIN_OBJECT.equals(nextToken)) {
                        reader.beginObject();
                    } else if (JsonToken.NAME.equals(nextToken) && lineName == null) {
                        lineName = reader.nextName();
                        line = new Line(lineName);
                        Engine.metro.put(lineName, line);
                    } else if (JsonToken.NAME.equals(nextToken)) {
                        indexOfStation = Integer.parseInt(reader.nextName());
                    } else if (JsonToken.STRING.equals(nextToken)) {
                        stationName = reader.nextString();
                        assert line != null;
                        line.addByIndex(indexOfStation, new Station(stationName));
                    } else if (JsonToken.END_OBJECT.equals(nextToken)) {
                        reader.endObject();
                        lineName = null;
                    } else if (JsonToken.NUMBER.equals(nextToken)) {
                        long value = reader.nextLong();
                        System.out.println(value);
                    } else if (JsonToken.END_DOCUMENT.equals(nextToken)) {
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error! Such a file doesn't exist!");
            }

        }
}
