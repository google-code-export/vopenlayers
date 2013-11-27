
package org.vaadin.vol;

import org.vaadin.vol.client.WellKnownTextLayerState;

public class WellKnownTextLayer extends AbstractAutoPopulatedVectorLayer implements Layer {


    public WellKnownTextLayer() {
        setDisplayName("WKT");
    }

    public WellKnownTextLayer(String wkt) {
        this();
        setWellKnownText(wkt);
    }

    @Override
    public WellKnownTextLayerState getState() {
        return WellKnownTextLayerState.class.cast(super.getState());
    }

    public void setWellKnownText(String wkt) {
        this.getState().wkt = wkt;
        markAsDirty();
    }
    public String getWellKnownText() {
        return this.getState().wkt;
    }

}
