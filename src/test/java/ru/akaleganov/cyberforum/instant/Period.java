package ru.akaleganov.cyberforum.instant;

import java.time.Instant;

import lombok.Builder;
import lombok.Data;

/**
 * Период в инстанте.
 */
@Data
@Builder
public class Period {

    private Instant startPeriod;

    private Instant endPeriod;

}
