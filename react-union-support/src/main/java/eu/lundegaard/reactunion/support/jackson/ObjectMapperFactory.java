/*
 * Copyright (C) Lundegaard, s.r.o. 2018 - All Rights Reserved
 * Proprietary and confidential. Unauthorized copying of this file, via any medium is strictly prohibited.
 */
package eu.lundegaard.reactunion.support.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Roman Srom (roman.srom@lundegaard.eu)
 */
public class ObjectMapperFactory {

    private static ObjectMapper instance;

    public static ObjectMapper getInstance() {
        if (instance == null) {
            synchronized (ObjectMapperFactory.class) {
                if (instance == null) {
                    instance = new ObjectMapper();
                }
            }
        }
        return instance;
    }

}
