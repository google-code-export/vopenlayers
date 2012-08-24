package org.vaadin.vol.client.ui.vcom;

import org.vaadin.vol.GoogleStreetMapLayer;
import org.vaadin.vol.client.ui.VGoogleStreetMapLayer;

import com.vaadin.shared.ui.Connect;
import com.vaadin.terminal.gwt.client.ui.Vaadin6Connector;

@Connect(GoogleStreetMapLayer.class)
public class GoogleMapLayerConnector extends Vaadin6Connector {

    @Override
    public VGoogleStreetMapLayer getWidget() {
        return (VGoogleStreetMapLayer) super.getWidget();
    }
    
}
