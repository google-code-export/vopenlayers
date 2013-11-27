package org.vaadin.vol;

import org.vaadin.vol.client.WebMapServiceLayerStyledState;

public class WebMapServiceLayerStyled extends WebMapServiceLayer {

    @Override
    public WebMapServiceLayerStyledState getState() {
        return WebMapServiceLayerStyledState.class.cast(super.getState());
    }

    public String getSld() {
        return this.getState().sld;
    }

    public void setSld(String sld) {
        this.getState().sld = sld;
        markAsDirty();
    }
}
