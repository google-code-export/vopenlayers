package org.vaadin.vol.client;

import com.vaadin.shared.Connector;

import java.util.LinkedList;
import java.util.List;

import org.vaadin.vol.StyleMap;

public class VectorLayerState extends NamedLayerState {

    public StyleMap stylemap;

    public Connector selectedVector;

    public String displayName = "Vector layer";

    public List<Connector> vectors = new LinkedList<Connector>();

    public String selectionMode = "NONE";

    public String drawingMode = "NONE";

    public String selectionCtrlId;             // Common SelectFeature control identifier
}
