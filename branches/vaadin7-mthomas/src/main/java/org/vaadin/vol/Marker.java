/**
 *
 */
package org.vaadin.vol;

import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Resource;
import com.vaadin.ui.AbstractComponent;

import org.vaadin.vol.client.MarkerState;

@SuppressWarnings("serial")
public class Marker extends AbstractComponent {

    public Marker(double lon, double lat) {
        this.getState().lon = lon;
        this.getState().lat = lat;
    }

    @Override
    public MarkerState getState() {
        return MarkerState.class.cast(super.getState());
    }

    public double getLon() {
        return getState().lon;
    }

    public double getLat() {
        return getState().lat;
    }

    public void setLon(double lon) {
        this.getState().lon = lon;
        markAsDirty();
    }

    public void setLat(double lat) {
        this.getState().lat = lat;
        markAsDirty();
    }

    public void setIcon(String url, int width, int height) {
        this.setIcon(new ExternalResource(url), width, height, Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public void setIcon(String url, int width, int height, int xOffset, int yOffset) {
        this.setIcon(new ExternalResource(url), width, height, xOffset, yOffset);
    }

    public void setIcon(Resource icon, int width, int height) {
        this.setIcon(icon, width, height, Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public void setIcon(Resource icon, int width, int height, int xOffset, int yOffset) {
        getState().icon_w = width;
        getState().icon_h = height;
        getState().icon_ox = xOffset;
        getState().icon_oy = yOffset;
        setIcon(icon); // also calls requestRepaint()
    }

    /*public void paintContent(PaintTarget target) throws PaintException {
        target.addAttribute("lon", lon);
        target.addAttribute("lat", lat);
        target.addAttribute("pr", projection);
        if(getIcon() != null && icon_h != 0 && icon_w != 0) {
            target.addAttribute("icon_w", icon_w);
            target.addAttribute("icon_h", icon_h);
            if(icon_ox != Integer.MIN_VALUE && icon_oy != Integer.MIN_VALUE)
            {
                target.addAttribute("icon_ox", icon_ox);
                target.addAttribute("icon_oy", icon_oy);
            }
        }
    }*/

    public void addClickListener(ClickListener listener) {
        addListener("click", ClickEvent.class, listener,
                ClickListener.clickMethod);
        markAsDirty();
    }

    public void removeClickListener(ClickListener listener) {
        removeListener(ClickEvent.class, listener);
    }

    /*@Override
    public void changeVariables(Object source, Map<String, Object> variables) {
        super.changeVariables(source, variables);
        if (variables.containsKey("click")) {
            fireEvent(new ClickEvent(this, null));
        }
    }*/

}
