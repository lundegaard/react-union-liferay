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
import java.util.Map;

/**
 * Interface that provides information about React Widget.
 *
 * @author Roman Srom (roman.srom@lundegaard.eu)
 */
interface ReactWidgetAware {

    /**
     * Name of supported widget.
     *
     * @return name of supported widget
     */
    String getWidgetName();

    /**
     * Returns widget's init data.
     *
     * @param request
     * @return widget's init data
     */
    Object getWidgetInitData(RenderRequest request);

    /**
     * Returns map of (widgetName, widgetInitData).
     *
     * @param request
     * @return map of (widgetName, widgetInitData)
     */
    Map<String, Object> getWidgetsInitData(RenderRequest request);

}
