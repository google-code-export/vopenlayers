package org.vaadin.vol.client.ui.vcom;

import org.vaadin.vol.Area;
import org.vaadin.vol.client.ui.VArea;

import com.vaadin.client.ui.LegacyConnector;
import com.vaadin.shared.ui.Connect;

@Connect(Area.class)
public class AreaConnector extends LegacyConnector {

    @Override
    public VArea getWidget() {
        return (VArea) super.getWidget();
    }
    
    @Override
    public boolean delegateCaptionHandling() {
        return false;
    }
    
}
