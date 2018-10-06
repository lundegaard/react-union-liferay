/*
 * MIT License
 *
 * Copyright (c) 2018 Lundegaard, spol. s r.o.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eu.lundegaard.reactunion.support.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.lundegaard.reactunion.support.exception.ReactSupportException;

/**
 * Serves to map data between Java and React.
 *
 * @author Roman Srom (roman.srom@lundegaard.eu)
 */
public class ReactDataMapper {

    private static final Logger LOG = LoggerFactory.getLogger(ReactDataMapper.class);

    /**
     * Returns JSON representation of the object. It uses Jackson library to create JSON format.
     *
     * @param object
     * @return
     */
    public String getJson(Object object) {
        ObjectMapper objectMapper = ObjectMapperFactory.getInstance();
        try {
            final String json = objectMapper.writeValueAsString(object);
            LOG.debug("Serialized object {} to json {}", object, json);
            return json;
        } catch (JsonProcessingException e) {
            throw new ReactSupportException("Serialization to json error", e);
        }
    }
}
