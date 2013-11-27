

package org.vaadin.vol;

import org.vaadin.vol.client.WebMapServiceLayerState;

public class WebMapServiceLayer extends AbstractLayerBase implements Layer {

    @Override
    public WebMapServiceLayerState getState() {
        return WebMapServiceLayerState.class.cast(super.getState());
    }

    public String getStyles() {
        return this.getState().layerStyles;
    }

    public void setStyles(String styles) {
        this.getState().layerStyles = styles;
        markAsDirty();
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

    public void setServiceType(String name) {
        this.getState().type = name;
        markAsDirty();
    }

    public String getServiceType() {
        return this.getState().type;
    }

    public String getFeatureID() {
        return this.getState().featureId;
    }

    public void setLayers(String layers) {
        this.getState().layers = layers;
        markAsDirty();
    }

    public void resetFeatures() {
        this.getState().featureId = "";
    }

    public void addFeatureID(String featureid) {
        if ("".equals(this.getState().featureId)) {
            this.getState().featureId = featureid;
        } else {
            this.getState().featureId += "," + featureid;
        }
    }

    public String getLayer() {
        return this.getState().layers;
    }

    public void setFormat(String format) {
        this.getState().format = format;
        markAsDirty();
    }

    public String getFormat() {
        return this.getState().format;
    }

    public void setTransparent(boolean transparent) {
        this.getState().transparent = transparent;
        markAsDirty();
    }

    public boolean getTransparent() {
        return this.getState().transparent;
    }

    public void setCqlFilter(String cqlFilter) {
        this.getState().cqlFilter = cqlFilter;
        markAsDirty();
    }

    public String getCqlFilter() {
        return this.getState().cqlFilter;
    }

    public boolean isSingleTile() {
        return this.getState().isSingleTile;
    }

    public void setSingleTile(boolean isSingleTile) {
        this.getState().isSingleTile = isSingleTile;
    }

    public String getViewparams() {
       return this.getState().viewParams;
    }

    public void setViewparams(String viewparams) {
        this.getState().viewParams = viewparams;
      markAsDirty();
    }
}
