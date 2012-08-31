
package org.vaadin.vol.client.ui;

import org.vaadin.vol.client.wrappers.Marker;

import com.vaadin.client.Paintable;

public interface VMarkable extends Paintable {
    Marker getMarker();
}
