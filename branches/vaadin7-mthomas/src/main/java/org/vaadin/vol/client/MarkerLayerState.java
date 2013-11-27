package org.vaadin.vol.client;

import com.vaadin.shared.Connector;

import java.util.LinkedList;

public class MarkerLayerState extends NamedLayerState {

    public LinkedList<Connector> markers = new LinkedList<Connector>();
}
