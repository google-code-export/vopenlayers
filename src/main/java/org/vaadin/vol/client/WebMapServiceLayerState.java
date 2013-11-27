package org.vaadin.vol.client;

public class WebMapServiceLayerState extends LayerBaseState {

    public String uri = "";
    public String type = "wms";
    public String layers = "basic";
    public String cqlFilter;
    public boolean isBaseLayer = true;
    public double opacity = 1.0;
    public boolean transparent = true;
    public boolean isSingleTile;
    public String featureId = "";
    public String format = "image/jpeg";
    public String layerStyles;
    public String viewParams;
}
