package org.vaadin.vol.client.ui.vcom;

import org.vaadin.vol.OpenLayersMap;
import org.vaadin.vol.client.ui.VOpenLayersMap;

import com.google.gwt.core.shared.GWT;
import com.vaadin.client.ApplicationConnection;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.Paintable;
import com.vaadin.client.UIDL;
import com.vaadin.client.ui.AbstractComponentContainerConnector;
import com.vaadin.shared.ui.Connect;

@Connect(OpenLayersMap.class)
public class OpenLayersMapConnector extends AbstractComponentContainerConnector implements Paintable {
    
    @Override
    public VOpenLayersMap getWidget() {
        return (VOpenLayersMap) super.getWidget();
    }

    @Override
    protected VOpenLayersMap createWidget() {
        return GWT.create(VOpenLayersMap.class);
    }

    @Override
    public void updateCaption(ComponentConnector connector) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
        getWidget().setImmediate(getState().immediate);
        getWidget().updateFromUIDL(uidl, client);
    }

    @Override
    public void onConnectorHierarchyChange(
            ConnectorHierarchyChangeEvent connectorHierarchyChangeEvent) {
        // TODO Auto-generated method stub
        
    }

//    @Override
//    public void onStateChanged(StateChangeEvent stateChangeEvent) {
//        super.onStateChanged(stateChangeEvent);
//        Util.browserDebugger();
//        Control control = PanZoomBar.create();
//        getWidget().getMap().addControl(control);
//        
//        List<ComponentConnector> childComponents2 = getChildComponents();
//        for (ComponentConnector componentConnector : childComponents2) {
//            getWidget().add(componentConnector.getWidget());
//        }
//        
//    }
//
//    @Override
//    public void updateCaption(ComponentConnector connector) {
//        // NOP
//    }

}
