package org.vaadin.vol.client.ui.vcom;

import org.vaadin.vol.OpenStreetMapLayer;
import org.vaadin.vol.client.ui.VOpenStreetMapLayer;

import com.vaadin.client.ui.Vaadin6Connector;
import com.vaadin.shared.ui.Connect;

@Connect(OpenStreetMapLayer.class)
public class OpenStreetMapLayerConnector extends Vaadin6Connector {

    @Override
    public VOpenStreetMapLayer getWidget() {
        return (VOpenStreetMapLayer) super.getWidget();
    }
    
}
