package org.vaadin.vol.client.ui.vcom;

import org.vaadin.vol.GoogleSatelliteMapLayer;
import org.vaadin.vol.client.ui.VGoogleSatelliteMapLayer;

import com.vaadin.client.ui.Vaadin6Connector;
import com.vaadin.shared.ui.Connect;


@Connect(GoogleSatelliteMapLayer.class)
public class GoogleSatelliteMapLayerConnector extends Vaadin6Connector {

    @Override
    public VGoogleSatelliteMapLayer getWidget() {
        return (VGoogleSatelliteMapLayer) super.getWidget();
    }
    
}
