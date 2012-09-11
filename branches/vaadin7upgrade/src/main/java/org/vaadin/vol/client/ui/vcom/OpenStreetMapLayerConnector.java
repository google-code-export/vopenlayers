package org.vaadin.vol.client.ui.vcom;

import org.vaadin.vol.OpenStreetMapLayer;
import org.vaadin.vol.client.ui.VOpenStreetMapLayer;

import com.vaadin.client.ui.LegacyConnector;
import com.vaadin.shared.ui.Connect;

@Connect(OpenStreetMapLayer.class)
public class OpenStreetMapLayerConnector extends LegacyConnector {

    @Override
    public VOpenStreetMapLayer getWidget() {
        return (VOpenStreetMapLayer) super.getWidget();
    }
    
}
