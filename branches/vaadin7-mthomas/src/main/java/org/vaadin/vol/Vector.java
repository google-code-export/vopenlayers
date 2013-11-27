package org.vaadin.vol;

import com.vaadin.ui.AbstractComponent;

import java.util.Map;

import org.vaadin.vol.client.VectorState;

public abstract class Vector extends AbstractComponent {

    @Override
    public VectorState getState() {
        return VectorState.class.cast(super.getState());
    }

    public void setPoints(Point... points) {
        setPointsWithoutRepaint(points);
        markAsDirty();
    }

    protected void setPointsWithoutRepaint(Point... points) {
        this.getState().points = points;
    }

    public Point[] getPoints() {
        return getState().points;
    }

    public void setProjection(String projection) {
        this.getState().projection = projection;
    }

    public String getProjection() {
        if (getState().projection == null && getUI() != null) {
            OpenLayersMap parent2 = (OpenLayersMap)getParent().getParent();
            return parent2.getApiProjection();
        }
        return getState().projection;
    }

    /**
     * @return the custom style declaration assosicated with this Vector
     */
    public Style getCustomStyle() {
        if (getState().style == null)
            return null;
        Style style = new Style();
        for (Map.Entry<String, Object> entry : getState().style.getKeyValueMap().entrySet())
            style.setProperty(entry.getKey(), entry.getValue());
        return style;
    }

    /**
     * @param style
     *            the custom style declaration to be used for rendering this
     *            Vector
     */
    public void setCustomStyle(Style style) {
        this.getState().style = style.getStyleProperty();
        markAsDirty();
    }

    public void select() {
        if (getParent() != null) {
            ((VectorLayer)getParent()).setSelectedVector(this);
        }
    }

    /**
     * Vectors styleName does not modify CSS style name as the method does for
     * standard Components. Instead the style name defines rendered intent that
     * will be used by OpenLayers to style the Vector. Rendered intents can be
     * configured with {@link StyleMap}s.
     *
     * @see com.vaadin.ui.AbstractComponent#setStyleName(java.lang.String)
     */
    @Override
    public void setStyleName(String style) {
        super.setStyleName(style);
    }

    /**
     * Sets a custom renderer intent that OpenLayers should use to render the
     * vector. The default is 'default'.
     *
     *
     * @see StyleMap
     *
     * @param style
     *            the name of renderer intent.
     */
    public void setRenderIntent(String style) {
        setStyleName(style);
    }

    /**
     * @return the vectAttributes
     */
    public Attributes getAttributes() {
        return getState().vectAttributes;
    }

    /**
     * @param attributes
     *            the vectAttributes to set
     */
    public void setAttributes(Attributes attributes) {
        this.getState().vectAttributes = attributes;
    }

}
