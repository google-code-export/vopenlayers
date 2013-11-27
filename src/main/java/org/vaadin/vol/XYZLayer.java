
package org.vaadin.vol;

import org.vaadin.vol.client.XYZLayerState;

public class XYZLayer extends AbstractLayerBase implements Layer {

    @Override
    public XYZLayerState getState() {
        return XYZLayerState.class.cast(super.getState());
    }

    public void setUri(String uri) {
        this.getState().uri = uri;
        markAsDirty();
    }
    public String getUri() {
        return this.getState().uri;
    }

    @Override
    public void setDisplayName(String displayName) {
        setCaption(displayName);
        super.setDisplayName(displayName);
    }

    public boolean isSphericalMercator() {
        return this.getState().sphericalMercator;
    }
    public void setSphericalMercator(boolean sphericalMercator) {
        this.getState().sphericalMercator = sphericalMercator;
    }
}
