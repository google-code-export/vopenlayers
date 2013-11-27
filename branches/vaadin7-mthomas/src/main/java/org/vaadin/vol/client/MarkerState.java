package org.vaadin.vol.client;

import com.vaadin.shared.AbstractComponentState;

public class MarkerState extends AbstractComponentState {

    public double lon;
    public double lat;
    public String projection = "EPSG:4326";
    public int icon_w;
    public int icon_h;
    public int icon_ox = Integer.MIN_VALUE; // Integer.MIN_VALUE means ignore explicit offset
    public int icon_oy = Integer.MIN_VALUE; // Integer.MIN_VALUE means ignore explicit offset
}
