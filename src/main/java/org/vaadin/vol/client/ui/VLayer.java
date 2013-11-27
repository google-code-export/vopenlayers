package org.vaadin.vol.client.ui;

import com.vaadin.client.Paintable;

import org.vaadin.vol.client.wrappers.layer.Layer;

public interface VLayer extends Paintable {

    Layer getLayer();

}
