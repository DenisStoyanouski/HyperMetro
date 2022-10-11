package metro;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GsonStreamApiRead {

    static Line currentLine = null;

    static String key = null;

    static boolean isTransfer = false;

    static String nameOfStation = null;

    static JsonToken nextToken;

        static void read(File file) throws IOException {



            try (JsonReader reader = new JsonReader(new FileReader(file))) {

                while (true) {
                    //We get the type of the next token with the peek method.
                    nextToken = reader.peek();
                    if (JsonToken.BEGIN_OBJECT.equals(nextToken)) {
                        reader.beginObject();
                    } else if (JsonToken.NAME.equals(nextToken) && currentLine == null) {
                        String currentLineName = reader.nextName();
                        currentLine = new Line(currentLineName);
                        Engine.metro.put(currentLineName, currentLine);
                    } else if (JsonToken.NAME.equals(nextToken) && key == null) {
                        key = reader.nextName();
                    } else if (JsonToken.NAME.equals(nextToken)) {
                        String fieldName = reader.nextName();
                        if ("name".equals(fieldName.replaceAll("\"",""))) {
                            nextToken = reader.peek();
                            nameOfStation = reader.nextString();
                            currentLine.addStationByIndex(Integer.parseInt(key), nameOfStation);
                        }
                        if ("transfer".equals(fieldName.replaceAll("\"",""))) {
                            nextToken = reader.peek();

                            if (nextToken.equals(JsonToken.NULL)) {
                                reader.nextNull();
                            }
                            if (nextToken.equals(JsonToken.BEGIN_OBJECT)) {
                                isTransfer = true;
                                handleJsonObject(reader);
                            }
                            if (nextToken.equals(JsonToken.BEGIN_ARRAY)) {
                                handleJsonArray(reader);
                            }
                        }
                    } else if (JsonToken.NULL.equals(nextToken)) {
                        reader.skipValue();
                    } else if (JsonToken.END_OBJECT.equals(nextToken)) {
                        reader.endObject();
                        if (isTransfer) {
                            isTransfer = false;
                        } else if (key != null) {
                            key = null;
                        } else {
                            currentLine = null;
                        }
                    } else if (JsonToken.END_DOCUMENT.equals(nextToken)) {
                        break;
                    }
                }
            }
            System.out.println("file is downloaded");
        }

    private static void handleJsonObject(JsonReader reader) throws IOException {
        reader.beginObject();
        String fieldName = null;
        String line = null;
        String station;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.BEGIN_ARRAY)) {
                handleJsonArray(reader);
            } else if (token.equals(JsonToken.END_OBJECT)) {
                reader.endObject();
                return;
            } else {
                if (token.equals(JsonToken.NAME)) {
                    //get the current token
                    fieldName = reader.nextName();
                }

                if ("line".equals(fieldName)) {
                    //move to next token
                    token = reader.peek();
                    line = reader.nextString();
                }

                if ("station".equals(fieldName)) {
                    //move to next token
                    token = reader.peek();
                    station = reader.nextString();
                    currentLine.getStationByName(nameOfStation).addTransfer(line, station);
                }
            }
        }
    }

    private static void handleJsonArray(JsonReader reader) throws IOException {
        reader.beginArray();
        String fieldName;

        while (true) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.END_ARRAY)) {
                reader.endArray();
                break;
            } else if (token.equals(JsonToken.BEGIN_OBJECT)) {
                reader.beginObject();
            } else if (token.equals(JsonToken.NAME)) {
                fieldName = reader.nextName();
                if ("line".equals(fieldName)) {
                    token = reader.peek();
                    String line = reader.nextString();
                }
                if ("station".equals(fieldName)) {
                    token = reader.peek();
                    String station = reader.nextString();
                }

            } else if (token.equals(JsonToken.END_OBJECT)) {
                reader.endObject();
            }
        }
    }
}
