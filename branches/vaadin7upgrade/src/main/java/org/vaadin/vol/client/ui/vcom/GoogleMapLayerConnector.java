package org.vaadin.vol.client.ui.vcom;

import org.vaadin.vol.GoogleStreetMapLayer;
import org.vaadin.vol.client.ui.VGoogleStreetMapLayer;

import com.vaadin.client.ui.Vaadin6Connector;
import com.vaadin.shared.ui.Connect;

@Connect(GoogleStreetMapLayer.class)
public class GoogleMapLayerConnector extends Vaadin6Connector {

    @Override
    public VGoogleStreetMapLayer getWidget() {
        return (VGoogleStreetMapLayer) super.getWidget();
    }
    
}
