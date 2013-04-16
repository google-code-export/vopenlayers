package org.vaadin.vol.client.ui.vcom;

import com.vaadin.client.ui.LegacyConnector;
import com.vaadin.shared.ui.Connect;

import org.vaadin.vol.WebMapServiceLayer;
import org.vaadin.vol.client.ui.VWebMapServiceLayer;

@Connect(WebMapServiceLayer.class)
public class WebMapServiceLayerConnector extends LegacyConnector {

    @Override
    public VWebMapServiceLayer getWidget() {
        return (VWebMapServiceLayer) super.getWidget();
    }
}
