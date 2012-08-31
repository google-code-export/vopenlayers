package org.vaadin.vol.client.ui.vcom;

import org.vaadin.vol.PointVector;
import org.vaadin.vol.client.ui.VPointVector;

import com.vaadin.client.ui.Vaadin6Connector;
import com.vaadin.shared.ui.Connect;

@Connect(PointVector.class)
public class PointVectorConnector extends Vaadin6Connector {

    @Override
    public VPointVector getWidget() {
        VPointVector widget = (VPointVector) super.getWidget();
        widget.setConnector(this);
        return widget;
    }
    
    @Override
    public boolean delegateCaptionHandling() {
        return false;
    }

    
}
