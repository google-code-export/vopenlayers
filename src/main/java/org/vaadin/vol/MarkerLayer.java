/**
 * 
 */
package org.vaadin.vol;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.vaadin.server.LegacyPaint;
import com.vaadin.server.PaintException;
import com.vaadin.server.PaintTarget;
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.LegacyComponent;

//@ClientWidget(org.vaadin.vol.client.ui.VMarkerLayer.class)
public class MarkerLayer extends AbstractComponentContainer implements Layer, LegacyComponent {

    private List<Marker> markers = new LinkedList<Marker>();

    private String displayName = "Markers";

    public void addMarker(Marker m) {
        addComponent(m);
    }
    
    public void replaceComponent(Component oldComponent, Component newComponent) {
        throw new UnsupportedOperationException();
    }

    public Iterator<Component> getComponentIterator() {
        LinkedList<Component> list = new LinkedList<Component>(markers);
        return list.iterator();
    }

    @Override
    public void addComponent(Component c) {
        if (c instanceof Marker) {
            markers.add((Marker) c);
            super.addComponent(c);
            markAsDirty();
        } else {
            throw new IllegalArgumentException(
                    "MarkerLayer supports only markers");
        }
    }

    @Override
    public void removeComponent(Component c) {
        super.removeComponent(c);
        markers.remove(c);
        requestRepaint();
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void removeMarker(Marker marker) {
        removeComponent(marker);
    }

    @Override
    public int getComponentCount() {
        return markers.size();
    }

    @Override
    public Iterator<Component> iterator() {
        return getComponentIterator();
    }
    
    @Override
    public void paintContent(PaintTarget target) throws PaintException {
        target.addAttribute("name", getDisplayName());
        for (Marker m : markers) {
            LegacyPaint.paint(m, target);
        }
    }

    @Override
    public void changeVariables(Object source, Map<String, Object> variables) {
        // TODO Auto-generated method stub
        
    }
}