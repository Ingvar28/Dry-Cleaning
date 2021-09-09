package ru.nosov.dry_cleaning.config;


import uk.co.jemos.podam.api.AbstractRandomDataProviderStrategy;
import uk.co.jemos.podam.api.AttributeMetadata;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DryCleaningDataProviderStrategy extends AbstractRandomDataProviderStrategy {

    private static final Random random = new Random(System.currentTimeMillis());

    public DryCleaningDataProviderStrategy() {
        super();
    }

//    @Override
//    public int getNumberOfCollectionElements(Class<?> type) {
//        if (Order.class.getName().equals(type.getName())) {
//            return 1 + random.nextInt(2);
//        } else if (Order.class.getName().equals(type.getName())) {
//            return 1 + random.nextInt(10);
//        }
//        return super.getNumberOfCollectionElements(type);
//    }
//
//    @Override
//    public String getStringValue(AttributeMetadata attributeMetadata) {
//
//    }
//
//    @Override
//    public Long getLong(AttributeMetadata attributeMetadata) {
//
//        if ("id".equals(attributeMetadata.getAttributeName())) {
//            return Long.valueOf(1 + random.nextInt(10));
//        }
//
//    }


    private enum FirstNames {
        JOHN,
        BRUCE,
        TOM,
        DMITRIY,
        IGOR,
        IVAN,
        SERGEY;

        private static final List<FirstNames> values = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int size = values.size();
        private static final Random random = new Random();

        public static String randomFirstNames() {
            return values.get(random.nextInt(size)).toString();
        }
    }

    private enum LastNames {
        WEAK,
        WAIN,
        JOHNS,
        MEDVEDEV,
        NOSOV,
        GROZNIY,
        ESENIN;

        private static final List<LastNames> values = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int size = values.size();
        private static final Random random = new Random();

        public static String randomLastNames() {
            return values.get(random.nextInt(size)).toString();
        }
    }


}
