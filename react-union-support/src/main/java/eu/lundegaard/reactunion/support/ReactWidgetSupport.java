/*
 * Copyright (C) Lundegaard, s.r.o. 2018 - All Rights Reserved
 * Proprietary and confidential. Unauthorized copying of this file, via any medium is strictly prohibited.
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
