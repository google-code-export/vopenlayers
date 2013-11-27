package org.vaadin.vol;

import org.vaadin.vol.client.BingMapLayerState;

/**
 * BingMap layer that can be added to {@link OpenLayersMap}. Require API key
 * from bingmapportal.com
 *
 * <p>
 * Note that no settings can be changed after the layer has been drawn for the
 * first time.
 */
public class BingMapLayer extends AbstractNamedLayer implements Layer {

    public enum Type {
        Road, Aerial, AerialWithLabels
        // , Birdseye, BirdseyeWithLabels
    }


    public BingMapLayer() {
    }

    public BingMapLayer(String apikey) {
        setApikey(apikey);
    }

    @Override
    public BingMapLayerState getState() {
        return BingMapLayerState.class.cast(super.getState());
    }

    @Override
    public void beforeClientResponse(boolean initial) {
        super.beforeClientResponse(initial);

        if (this.getState().apikey == null) {
            throw new IllegalStateException("Bing maps API key must be set!");
        }
    }

    public void setApikey(String apikey) {
        this.getState().apikey = apikey;
    }

    public String getApikey() {
        return this.getState().apikey;
    }

    public void setType(Type t) {
        this.getState().type = t.toString();
    }

    public Type getType() {
        return Type.valueOf(this.getState().type);
    }

}
