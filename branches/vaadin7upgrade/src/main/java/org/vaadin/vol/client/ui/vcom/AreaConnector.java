package org.vaadin.vol.client.ui.vcom;

import org.vaadin.vol.Area;
import org.vaadin.vol.client.ui.VArea;

import com.vaadin.shared.ui.Connect;
import com.vaadin.terminal.gwt.client.ui.Vaadin6Connector;

@Connect(Area.class)
public class AreaConnector extends Vaadin6Connector {

    @Override
    public VArea getWidget() {
        return (VArea) super.getWidget();
    }
    
    @Override
    public boolean delegateCaptionHandling() {
        return false;
    }
    
}
