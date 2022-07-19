package qa8.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
    @Override
    public void write(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
        jsonWriter.jsonValue(DateTimeFormatter.ISO_DATE_TIME.format(localDateTime));
    }

    @Override
    public LocalDateTime read(JsonReader jsonReader) throws IOException {
        return DateTimeFormatter.ISO_DATE_TIME.parse(jsonReader.nextString(), LocalDateTime::from);
    }
}
