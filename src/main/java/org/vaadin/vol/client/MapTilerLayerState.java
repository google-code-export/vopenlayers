package org.vaadin.vol.client;

public class MapTilerLayerState extends NamedLayerState {

    public String uri = "";
    public String layers = "basic";
    public boolean isBaseLayer = true;
    public double opacity = 1.0;
    public boolean transparent = true;
    public double[] bounds;
    public int minZoom;
    public int maxZoom = -1;
}
