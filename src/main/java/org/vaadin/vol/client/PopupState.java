package org.vaadin.vol.client;

import com.vaadin.shared.AbstractComponentState;
import com.vaadin.shared.Connector;

import org.vaadin.vol.Point;

public class PopupState extends AbstractComponentState {

    public String projection = "EPSG:4326";
    public String popupstyle = "DEFAULT";
    public Point point = new Point(0, 0);
    public boolean closable = true;
    public Connector anchor;
}
