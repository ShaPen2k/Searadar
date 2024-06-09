package org.example.searadar.mr231_3.station;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.example.searadar.mr231_3.convert.Mr231_3Converter;
import ru.oogis.searadar.api.convert.SearadarExchangeConverter;
import ru.oogis.searadar.api.station.AbstractStationType;

import java.nio.charset.Charset;

/**
 * Данный класс представляет собой тип станции "МР-231-3", который используется для инициализации объектов и конвертеров для обмена данными с сетью Searadar.
 * <p>
 * Класс содержит приватные константы STATION_TYPE и CODEC_NAME, которые определяют тип и название кодека станции соответственно.
 * <p>
 * Метод doInitialize() не реализован и предполагается для выполнения инициализации станции, такой как настройка соединения или настройка кодека обмена данными.
 * <p>
 * Метод createConverter() возвращает новый объект Mr231Converter, который предназначен для преобразования входящих сообщений в объекты SearadarStationMessage согласно типам сообщений "TTM" и "RSD".
 *
 */

public class Mr231_3StationType {

    private static final String STATION_TYPE = "МР-231-3";
    private static final String CODEC_NAME = "mr231_3";


    protected void doInitialize() {
        TextLineCodecFactory textLineCodecFactory = new TextLineCodecFactory(
                Charset.defaultCharset(),
                LineDelimiter.UNIX,
                LineDelimiter.CRLF
        );

    }


    public Mr231_3Converter createConverter                                                                                                                                                        () {
        return new Mr231_3Converter();
    }
}
