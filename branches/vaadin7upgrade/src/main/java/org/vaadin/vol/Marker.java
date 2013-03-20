/**
 * 
 */
package org.vaadin.vol;

import java.util.Map;

import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.PaintException;
import com.vaadin.server.PaintTarget;
import com.vaadin.server.Resource;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.LegacyComponent;

@SuppressWarnings("serial")
public class Marker extends AbstractComponent implements LegacyComponent {

    private double lon;
    private double lat;
    private String projection = "EPSG:4326";
    private int icon_w;
    private int icon_h;

    public Marker(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
        requestRepaint();
    }

    public void setLat(double lat) {
        this.lat = lat;
        requestRepaint();
    }

    public void setIcon(String url, int width, int height) {
        setIcon(new ExternalResource(url));
        icon_w = width;
        icon_h = height;
        requestRepaint();
    }

    public void setIcon(Resource icon, int width, int height) {
        setIcon(icon);
        icon_w = width;
        icon_h = height;
    }

    public void paintContent(PaintTarget target) throws PaintException {
        target.addAttribute("lon", lon);
        target.addAttribute("lat", lat);
        target.addAttribute("pr", projection);
        if (getIcon() != null && icon_h != 0 && icon_w != 0) {
            target.addAttribute("icon_w", icon_w);
            target.addAttribute("icon_h", icon_h);
        }
    }

    public void addClickListener(ClickListener listener) {
        addListener("click", ClickEvent.class, listener,
                ClickListener.clickMethod);
        requestRepaint();
    }

    public void removeClickListener(ClickListener listener) {
        removeListener(ClickEvent.class, listener);
    }

    @Override
    public void changeVariables(Object source, Map<String, Object> variables) {
        if (variables.containsKey("click")) {
            fireEvent(new ClickEvent(this, null));
        }
    }

}