package org.vaadin.vol.client;

import com.vaadin.shared.AbstractComponentState;

import org.vaadin.vol.Attributes;
import org.vaadin.vol.JsObject;
import org.vaadin.vol.Point;

public class VectorState extends AbstractComponentState {

    public String projection;

    public Point[] points;

    public JsObject style;

    public Attributes vectAttributes;
}
