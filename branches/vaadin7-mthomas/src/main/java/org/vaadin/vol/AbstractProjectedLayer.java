package org.vaadin.vol;

import org.vaadin.vol.client.ProjectedLayerState;

abstract class AbstractProjectedLayer extends AbstractNamedLayer {

    public String getProjection() {
        return this.getState().projection;
    }
    public void setProjection(String projection) {
        this.getState().projection = projection;
    }

    @Override
    public ProjectedLayerState getState() {
        return ProjectedLayerState.class.cast(super.getState());
    }
}
