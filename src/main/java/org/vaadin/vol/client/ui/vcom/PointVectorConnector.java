package org.vaadin.vol.client.ui.vcom;

import org.vaadin.vol.PointVector;
import org.vaadin.vol.client.ui.VPointVector;

import com.vaadin.shared.ui.Connect;
import com.vaadin.terminal.gwt.client.ui.Vaadin6Connector;

@Connect(PointVector.class)
public class PointVectorConnector extends Vaadin6Connector {

    @Override
    public VPointVector getWidget() {
        return (VPointVector) super.getWidget();
    }
    
    @Override
    public boolean delegateCaptionHandling() {
        return false;
    }

    
}
