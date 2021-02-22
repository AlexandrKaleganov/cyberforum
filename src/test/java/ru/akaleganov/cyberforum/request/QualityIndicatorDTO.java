package ru.akaleganov.cyberforum.request;


import java.time.Instant;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class QualityIndicatorDTO {
    private UUID id;

    private String name;

    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE_TIME
    )
    private Instant insTime;

    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE_TIME
    )
    private Instant updTime;

    /**
     * короткое наименование
     */
    private String shortName;

    private QualityIndicatorType qualityIndicatorType;

    /**
     * некий код из справочников
     */
    private String code;

}
