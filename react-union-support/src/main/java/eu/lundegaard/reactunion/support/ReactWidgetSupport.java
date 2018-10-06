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
package eu.lundegaard.reactunion.support;

import javax.portlet.RenderRequest;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.lundegaard.reactunion.support.jackson.ReactDataMapper;

import static eu.lundegaard.reactunion.support.ReactUnionConstants.*;

/**
 * Support class for the work with a React widget.
 * You must either override {@link #getWidgetsInitData(RenderRequest)} or
 * ({@link #getWidgetInitData(RenderRequest)} and {@link #getWidgetName()}).
 *
 * If you render only one widget in your portlet it is preferable to implement
 * ({@link #getWidgetInitData(RenderRequest)} and {@link #getWidgetName()}).
 * If you render multiple widgets in your portlet then implement {@link #getWidgetsInitData(RenderRequest)} method.
 * You should return Map with widget names as keys and widget init data as values.
 *
 * @author Roman Srom (roman.srom@lundegaard.eu)
 */
public abstract class ReactWidgetSupport implements ReactWidgetAware {

    private static final Logger LOG = LoggerFactory.getLogger(ReactWidgetSupport.class);

    public static final String UNKNOWN_WIDGET = "unknownWidget";

    /**
     * Sets widget's init data to the request attribute 'reactWidgetInitData_${unionWidget}'.
     *
     * @param request portlet render request
     */
    public void setWidgetsInitData(RenderRequest request) {
        final Map<String, Object> widgetsInitData = getWidgetsInitData(request);
        if (widgetsInitData != null) {
            ReactDataMapper reactDataMapper = new ReactDataMapper();
            widgetsInitData.forEach((widgetName, widgetData) -> {
                LOG.debug("Setting init data for widget {}", widgetName);
                request.setAttribute(ATTR_WIDGET_INIT_DATA_PREFIX + widgetName,
                        reactDataMapper.getJson(widgetData));
            });
        }
    }

    /**
     * Returns Map with widget names as keys and widget init data as values. The default implementation calls
     * ({@link #getWidgetInitData(RenderRequest)} and {@link #getWidgetName()}) to create the map with one entry.
     *
     * @param request portlet request
     * @return Map with widget names as keys and widget init data as values
     */
    @Override
    public Map<String, Object> getWidgetsInitData(RenderRequest request) {
        Map<String, Object> widgetsInitData = new HashMap<>();
        final Object widgetInitData = getWidgetInitData(request);
        widgetsInitData.put(getWidgetName(), widgetInitData);
        return widgetsInitData;
    }

    /**
     * Returns widget's init data.
     *
     * @param request portlet request
     * @return widget's init data
     */
    @Override
    public Object getWidgetInitData(RenderRequest request) {
        return null;
    }

    /**
     * Returns widget's name.
     *
     * @return widget's name
     */
    @Override
    public String getWidgetName() {
        return UNKNOWN_WIDGET;
    }
}
