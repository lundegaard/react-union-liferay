# react-union-taglib

JSP tag library containing tags which render the React widget. Purpose is to remove the need of boilerplate code which consists of a script tag and a container element.

**Boilerplate code:**
```jsp
<c:set var="ns"><portlet:namespace/></c:set>

<div id="${ns}content"></div>
<script data-union-widget="content" data-union-container="${ns}content" type="application/json">
    {
        "textation": {
            "heading": "${heading}",
            "content": "${content}"
        }
    }
</script>
```

## Tags

* `widget` - renders the React widget with initial data

## Examples

### The simplest usage

The simplest usage is just to write the `widget` tag and specify the widget name, e. g. _content_. 

```jsp
<react-union:widget unionWidget="content" />
```

It renders react widget _content_ to the `<div>` element with the `${ns}content` _id_, creates `<script>` tag and inserts initial data from the `reactWidgetInitData_${NAME}`, e.g. `reactWidgetInitData_content`. This is equivalent to:

```jsp
<div id="${ns}content"></div>
<script data-union-widget="content" data-union-container="${ns}content" type="application/json">
    ${reactWidgetInitData_content}
</script>
```

### Custom init data

You can specify custom init data directly in JSP by `initData` attribute.

```jsp
<react-union:widget unionWidget="content" initData='{
	"textation": {
		"heading": "Init data heading",
		"content": "Init data content"
	}
}' />
```

### Custom attribute containing initial data

You can specify custom init data attribute which contains the data. The data are usually inserted in the portlet controller.

*JSP:*
```jsp
<react-union:widget unionWidget="content" initDataAttr="customInitData" />
```

*Portlet controller:*

```java
@Override
public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
	renderRequest.setAttribute("customInitData", "{\n" +
			"\"textation\": {\n" +
			"    \"heading\": \"Init data heading\",\n" +
			"    \"content\": \"Init data content\"\n" +
			"  }\n" +
			"}\n");

	super.render(renderRequest, renderResponse);
}
```
