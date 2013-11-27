package org.vaadin.vol.client;

import org.vaadin.vol.StyleMap;

public class AutoPopulatedVectorLayerState extends LayerBaseState {

    public StyleMap stylemap;
    public String selectionMode;

    /**
     * this will be used to group vector layers to share the same SelectFeature
     * control
     */
    public String selectionCtrlId;

    /*if (stylemap != null) {
        stylemap.paint(target);
    }*/

    public boolean visibility;
    public String filterValue;
    public String filterType;
    public String filterProp;
    public boolean filterRefresh;

}
