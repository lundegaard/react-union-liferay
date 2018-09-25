# react-union-support

Java library for supporting the work with react union.

## Classes

* `ReactWidgetSupport` - helps with transforming initial data to json format.

## Examples

### ReactWidgetSupport

*ReactWidgetSupport:*

In your portlet application create own class (e. g. `HeroReactWidgetSupport`) that extends `ReactWidgetSupport`. Specify _widget name_ and _initial data_.

```java
@Component(
        immediate = true,
        service = HeroReactWidgetSupport.class
)
public class HeroReactWidgetSupport extends ReactWidgetSupport {

    @Override
    public Object getWidgetInitData(RenderRequest request) {
        final ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        if (themeDisplay != null) {
            final PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
            HeroConfiguration heroConfiguration = null;
            try {
                heroConfiguration = portletDisplay.getPortletInstanceConfiguration(HeroConfiguration.class);
                InitData initData = new InitData();
                initData.setTextation(new Textation(heroConfiguration.heading(), heroConfiguration.content()));
                return initData;
            } catch (ConfigurationException e) {
                throw new RuntimeException("Configuration error", e);
            }
        }
        return null;
    }

    @Override
    public String getWidgetName() {
        return "content";
    }
}
```

*HeroPortlet:*

In your portlet controller reference _ReactWidgetSupport_ and call `setWidgetsInitData`.

```java
@Component(
	configurationPid = HeroPortletKeys.CONFIGURATION,
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + HeroPortletKeys.HERO,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class HeroPortlet extends MVCPortlet {

	@Reference
	private HeroReactWidgetSupport reactWidgetSupport;

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		reactWidgetSupport.setWidgetsInitData(renderRequest);

		super.render(renderRequest, renderResponse);
	}

}
```
