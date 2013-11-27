/**
 *
 */
package org.vaadin.vol;

import com.vaadin.shared.Connector;
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.Component;

import java.util.ArrayList;
import java.util.Iterator;

import org.vaadin.vol.client.MarkerLayerState;

public class MarkerLayer extends AbstractComponentContainer implements Layer {

    public MarkerLayer() {
        getState().displayName = "Markers";
    }

    @Override
    public MarkerLayerState getState() {
        return MarkerLayerState.class.cast(super.getState());
    }

    public void addMarker(Marker m) {
        addComponent(m);
    }

    public void replaceComponent(Component oldComponent, Component newComponent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getComponentCount() {
        return getState().markers.size();
    }

    @Override
    public Iterator<Component> iterator() {
        ArrayList<Component> list = new ArrayList<Component>(getState().markers.size());
        for (Connector connector : getState().markers) {
            list.add((Component)connector);
        }
        return list.iterator();
    }

    @Override
    public void addComponent(Component c) {
        if (c instanceof Marker) {
            getState().markers.add(c);
            super.addComponent(c);
        } else {
            throw new IllegalArgumentException(
                    "MarkerLayer supports only markers");
        }
    }

    @Override
    public void removeComponent(Component c) {
        super.removeComponent(c);
        getState().markers.remove(c);
        markAsDirty();
    }

    public void removeMarker(Marker marker) {
        removeComponent(marker);
    }
}
