package org.vaadin.vol;

import com.vaadin.ui.AbstractComponent;

import org.vaadin.vol.client.NamedLayerState;

abstract class AbstractNamedLayer extends AbstractComponent {

    public String getDisplayName() {
        return this.getState().displayName;
    }
    public void setDisplayName(String displayName) {
        this.getState().displayName = displayName;
        markAsDirty();
    }

    @Override
    public NamedLayerState getState() {
        return NamedLayerState.class.cast(super.getState());
    }
}
