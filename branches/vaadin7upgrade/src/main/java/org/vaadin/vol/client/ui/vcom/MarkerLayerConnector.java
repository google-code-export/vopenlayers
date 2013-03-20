package org.vaadin.vol.client.ui.vcom;

import org.vaadin.vol.MarkerLayer;
import org.vaadin.vol.client.ui.VMarkerLayer;
import org.vaadin.vol.client.ui.VVectorLayer;

import com.google.gwt.core.shared.GWT;
import com.vaadin.client.ApplicationConnection;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.Paintable;
import com.vaadin.client.UIDL;
import com.vaadin.client.ui.AbstractComponentContainerConnector;
import com.vaadin.shared.ui.Connect;

@Connect(MarkerLayer.class)
public class MarkerLayerConnector extends AbstractComponentContainerConnector implements Paintable {
    
    @Override
    public VMarkerLayer getWidget() {
        return (VMarkerLayer) super.getWidget();
    }

    @Override
    protected VMarkerLayer createWidget() {
        return GWT.create(VMarkerLayer.class);
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

    @Override
    public void onConnectorHierarchyChange(
            ConnectorHierarchyChangeEvent connectorHierarchyChangeEvent) {
        // TODO Auto-generated method stub
        
    }

}
