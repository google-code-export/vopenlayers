package org.vaadin.vol.client.ui.vcom;

import org.vaadin.vol.Marker;
import org.vaadin.vol.client.ui.VMarker;

import com.vaadin.client.ui.LegacyConnector;
import com.vaadin.shared.ui.Connect;

@Connect(Marker.class)
public class MarkerConnector extends LegacyConnector {

    @Override
    public VMarker getWidget() {
        VMarker widget = (VMarker) super.getWidget();
        widget.setConnector(this);
        return widget;
    }
    
    @Override
    public boolean delegateCaptionHandling() {
        return false;
    }

    
}
