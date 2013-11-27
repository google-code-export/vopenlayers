
package org.vaadin.vol;

import org.vaadin.vol.client.WebFeatureServiceLayerState;

public class WebFeatureServiceLayer extends AbstractAutoPopulatedVectorLayer implements Layer {

    @Override
    public WebFeatureServiceLayerState getState() {
        return WebFeatureServiceLayerState.class.cast(super.getState());
    }

    public void setUri(String uri) {
        this.getState().uri = uri;
        markAsDirty();
    }
    public String getUri() {
        return this.getState().uri;
    }

    public void setFeatureType(String featureType) {
        this.getState().featureType = featureType;
        markAsDirty();
    }
    public String getFeatureType() {
        return this.getState().featureType;
    }

    public void setFeatureNS(String ns) {
        this.getState().featureNS = ns;
    }

    public String getFeatureNS() {
        return this.getState().featureNS;
    }
}
