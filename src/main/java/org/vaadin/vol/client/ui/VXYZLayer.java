package org.vaadin.vol.client.ui;

import com.vaadin.client.ApplicationConnection;
import com.vaadin.client.UIDL;

import org.vaadin.vol.client.wrappers.layer.XYZLayer;

public class VXYZLayer extends VAbstracMapLayer<XYZLayer> {

    private String uri;
    private boolean spheriacalMercator;

    @Override
    public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
        if (!uidl.hasAttribute("cached")) {
            uri = uidl.getStringAttribute("uri");
            spheriacalMercator = uidl.getBooleanAttribute("spheriacalMercator");
        }
        super.updateFromUIDL(uidl, client);
    }

    @Override
    XYZLayer createLayer() {
        return XYZLayer.create(getDisplayName(), uri, spheriacalMercator, getAttribution());
    }

}
