package org.example.searadar.mr231_3.convert;

import org.example.searadar.mr231_3.station.Mr231_3StationType;
import org.junit.jupiter.api.Test;
import ru.oogis.searadar.api.message.InvalidMessage;
import ru.oogis.searadar.api.message.SearadarStationMessage;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.*;
class Mr231_3ConverterTest {

    @Test
    void ConvertGetTTM_TEST() {
        String mr231_TTM = "$RATTM,46,05.14,123.4,T,52.8,139.5,T,6.3,-96.6,N,b,L,,496256,A*7f";
        Mr231_3StationType mr231 = new Mr231_3StationType();
        Mr231_3Converter converter = mr231.createConverter();
        List<SearadarStationMessage> searadarMessages = converter.convert(mr231_TTM);

        StringJoiner joiner = new StringJoiner(", ");
        for (SearadarStationMessage message : searadarMessages) {
            joiner.add(message.toString());
        }

        String actual = joiner.toString();
        String excepted = "TrackedTargetMessage";
        assertEquals(excepted.substring(0, 20), (actual.substring(0, 20)));
    }

    @Test
    void ConvertGetRSD_TEST() {
        String mr231_3RSD = "$RARSD,50.5,309.9,64.8,132.3,,,,,52.6,155.0,48.0,K,N,S*28";
        Mr231_3StationType mr231_3 = new Mr231_3StationType();
        Mr231_3Converter converter = mr231_3.createConverter();
        List<SearadarStationMessage> searadarMessages = converter.convert(mr231_3RSD);

        StringJoiner joiner = new StringJoiner(", ");
        for (SearadarStationMessage message : searadarMessages) {
            joiner.add(message.toString());
        }

        String actual = joiner.toString();
        String excepted = "RadarSystemData";
        assertEquals(excepted.substring(0, 15), actual.substring(0, 15));
    }

}