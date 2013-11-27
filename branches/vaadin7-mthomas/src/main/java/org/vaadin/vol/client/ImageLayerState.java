package org.vaadin.vol.client;

public class ImageLayerState extends NamedLayerState {

    public String uri = "";
    public boolean isBaseLayer = true;
    public double opacity = 1.0;
    public boolean transparent = true;
    public double[] bounds = new double[] { -180d, -90d, 180d, 90d };
    public int imageHeight;
    public int imageWidth;
}
