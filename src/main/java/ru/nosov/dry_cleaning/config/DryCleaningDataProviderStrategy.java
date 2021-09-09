package ru.nosov.dry_cleaning.config;

import uk.co.jemos.podam.api.AbstractRandomDataProviderStrategy;

import java.util.Random;

public class DryCleaningDataProviderStrategy extends AbstractRandomDataProviderStrategy {

    private static final Random random = new Random(System.currentTimeMillis());

    public DryCleaningDataProviderStrategy() {
        super();
    }


}
