<%--
Renders widget's snippet with init data stored in request attribute 'reactWidgetInitData_${name}'.
Init data attribute can be set by initDataAttr.
--%>
<%@attribute name="name" required="true" type="java.lang.String"
             description="widget's name" %>
<%@attribute name="unionContainer" required="false" type="java.lang.String"
             description="id of div element that is used as the widget's container" %>
<%@attribute name="initDataAttr" required="false" type="java.lang.String"
             description="widget's init data" %>
<%@attribute name="initData" required="false" type="java.lang.Object"
             description="name of request attribute with init data. It has preference before initDataAttr." %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<c:set var="ns"><portlet:namespace/></c:set>

<%-- Determine final container name --%>
<c:set var="finalUnionContainer" value="${ns}${name}" />
<c:if test="${unionContainer != null}">
    <c:set var="finalUnionContainer" value="${unionContainer}" />
</c:if>

<%-- Determine final init data --%>
<c:set var="defaultInitDataAttr" value="reactWidgetInitData_${name}" />
<c:set var="defaultInitData" value="${requestScope[defaultInitDataAttr]}" />

<c:set var="finalInitData" value="" />
<c:if test="${defaultInitData != null}">
    <c:set var="finalInitData" value="${defaultInitData}" />
</c:if>
<c:if test="${initDataAttr != null}">
    <c:set var="finalInitData" value="${requestScope[initDataAttr]}" />
</c:if>
<c:if test="${initData != null}">
    <c:set var="finalInitData" value="${initData}" />
</c:if>

<%-- Render widget's snippet --%>
<div id="${finalUnionContainer}"></div>
<script data-union-widget="${name}" data-union-container="${finalUnionContainer}" type="application/json">
    ${finalInitData}
</script>
