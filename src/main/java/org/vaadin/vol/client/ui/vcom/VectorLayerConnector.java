package org.vaadin.vol.client.ui.vcom;

import org.vaadin.vol.VectorLayer;
import org.vaadin.vol.client.ui.VVectorLayer;

import com.google.gwt.core.shared.GWT;
import com.vaadin.shared.ui.Connect;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.ComponentConnector;
import com.vaadin.terminal.gwt.client.Paintable;
import com.vaadin.terminal.gwt.client.UIDL;
import com.vaadin.terminal.gwt.client.ui.AbstractComponentContainerConnector;

@Connect(VectorLayer.class)
public class VectorLayerConnector extends AbstractComponentContainerConnector implements Paintable {
    
    @Override
    public VVectorLayer getWidget() {
        return (VVectorLayer) super.getWidget();
    }

    @Override
    protected VVectorLayer createWidget() {
        return GWT.create(VVectorLayer.class);
    }

    @Override
    public boolean delegateCaptionHandling() {
        return false;
    }


    @Override
    public void updateCaption(ComponentConnector connector) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
        getWidget().updateFromUIDL(uidl, client);
    }

}
