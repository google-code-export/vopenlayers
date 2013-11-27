package org.vaadin.vol.client.ui;

import com.vaadin.client.ApplicationConnection;
import com.vaadin.client.UIDL;

import org.vaadin.vol.client.wrappers.layer.OpenStreetMapLayer;

public class VOpenStreetMapLayer extends VAbstracMapLayer<OpenStreetMapLayer> {

    private String url;

    @Override
    OpenStreetMapLayer createLayer() {
        if (url != null)
            return OpenStreetMapLayer.createWithUrl(getDisplayName(),
                    getProjection(), url);
        else
            return OpenStreetMapLayer.create(getDisplayName(), getProjection());
    }

    @Override
    public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
        url = uidl.hasAttribute("url") ? uidl.getStringAttribute("url") : null;
        super.updateFromUIDL(uidl, client);
    }
}
