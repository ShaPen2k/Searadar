package org.example.searadar.mr231.convert;

import org.example.searadar.mr231.station.Mr231StationType;
import org.junit.jupiter.api.Test;
import ru.oogis.searadar.api.message.SearadarStationMessage;
import ru.oogis.searadar.api.message.TrackedTargetMessage;

import java.sql.Timestamp;
import java.util.List;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.*;

class Mr231ConverterTest {

    @Test
    void ConvertGetTTM_TEST() {
        String mr231_TTM = "$RATTM,66,28.71,341.1,T,57.6,024.5,T,0.4,4.1,N,b,L,,457362,А*42";
        Mr231StationType mr231 = new Mr231StationType();
        Mr231Converter converter = mr231.createConverter();
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
    void ConvertGetVHW_TEST() {
        String mr231_VHW = "$RAVHW,115.6,T,,,46.0,N,,*71";
        Mr231StationType mr231 = new Mr231StationType();
        Mr231Converter converter = mr231.createConverter();
        List<SearadarStationMessage> searadarMessages = converter.convert(mr231_VHW);

        StringJoiner joiner = new StringJoiner(", ");
        for (SearadarStationMessage message : searadarMessages) {
            joiner.add(message.toString());
        }

        String actual = joiner.toString();
        String excepted = "WaterSpeedHeadingMessage";
        assertEquals(excepted.substring(0, 24), actual.substring(0, 24));
    }

    @Test
    void ConvertGetRSD_TEST() {
        String mr231_RSD = "$RARSD,36.5,331.4,8.4,320.6,,,,,11.6,185.3,96.0,N,N,S*33";
        Mr231StationType mr231 = new Mr231StationType();
        Mr231Converter converter = mr231.createConverter();
        List<SearadarStationMessage> searadarMessages = converter.convert(mr231_RSD);

        StringJoiner joiner = new StringJoiner(", ");
        for (SearadarStationMessage message : searadarMessages) {
            joiner.add(message.toString());
        }

        String actual = joiner.toString();
        String excepted = "RadarSystemData";
        assertEquals(excepted.substring(0, 15), actual.substring(0, 15));
    }

}