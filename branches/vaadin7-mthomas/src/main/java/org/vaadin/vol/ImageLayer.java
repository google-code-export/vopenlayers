
package org.vaadin.vol;

import org.vaadin.vol.client.ImageLayerState;

public class ImageLayer extends AbstractNamedLayer implements Layer {

    public ImageLayer(String url, int width, int height) {
        setUri(url);
        getState().imageWidth = width;
        getState().imageHeight = height;
    }

    @Override
    public ImageLayerState getState() {
        return ImageLayerState.class.cast(super.getState());
    }

    public void setUri(String uri) {
        this.getState().uri = uri;
        markAsDirty();
    }

    public void setBaseLayer(boolean isBaseLayer) {
        this.getState().isBaseLayer = isBaseLayer;
        markAsDirty();
    }

    public boolean isBaseLayer() {
        return this.getState().isBaseLayer;
    }

    public void setOpacity(double opacity) {
        this.getState().opacity = opacity;
        markAsDirty();
    }

    public double getOpacity() {
        return this.getState().opacity;
    }

    public String getUri() {
        return this.getState().uri;
    }

    public void setTransparent(boolean transparent) {
        this.getState().transparent = transparent;
    }

    public boolean getTransparent() {
        return this.getState().transparent;
    }

    public void setBounds(double... bounds) {
        this.getState().bounds = bounds;
    }

    public double[] getBounds() {
        return getState().bounds;
    }

}
