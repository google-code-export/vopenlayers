package org.vaadin.vol;

import org.vaadin.vol.client.OpenStreetMapLayerState;

/**
 * OpenStreeMap layer that can be added to {@link OpenLayersMap}.
 */
public class OpenStreetMapLayer extends AbstractLayerBase implements Layer {

    @Override
    public OpenStreetMapLayerState getState() {
        return OpenStreetMapLayerState.class.cast(super.getState());
    }

    public String getUrl() {
        return this.getState().url;
    }
    public void setUrl(String url) {
        this.getState().url = url;
    }

}
