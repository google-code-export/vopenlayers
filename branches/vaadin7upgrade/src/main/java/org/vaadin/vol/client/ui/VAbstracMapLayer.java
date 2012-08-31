package org.vaadin.vol.client.ui;

import org.vaadin.vol.client.wrappers.Map;
import org.vaadin.vol.client.wrappers.layer.Layer;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ApplicationConnection;
import com.vaadin.client.UIDL;
import com.vaadin.client.VConsole;

public abstract class VAbstracMapLayer<T extends Layer> extends Widget
        implements VLayer {

    public VAbstracMapLayer() {
        setElement(Document.get().createDivElement());
    }

    private T layer;
    protected boolean layerAttached = false;
    private String displayName;
    private String projection;

    public T getLayer() {
        if (layer == null) {
            layer = createLayer();
        }
        return layer;
    }

    abstract T createLayer();

    public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
        VConsole.error("VAbMapLayer");
        if (client.updateComponent(this, uidl, true)) {
            return;
        }

        displayName = uidl.hasAttribute("name") ? uidl
                .getStringAttribute("name") : null;
        projection = uidl.hasAttribute("projection") ? uidl
                .getStringAttribute("projection") : null;
        // we'll do this lazy, not in attach, so implementations can
        // customize parameters for layer constructors. Possible changes must be
        // dealt inimplementation.
        attachLayerToMap();
    }

    public void attachLayerToMap() {
        if (!layerAttached) {
            final Map map = getMap();
            T layer2 = getLayer();
            map.addLayer(layer2);
            layerAttached = true;
        }
    }

    protected Map getMap() {
        return ((VOpenLayersMap) getParent().getParent()).getMap();
    }

    @Override
    protected void onDetach() {
        VConsole.error("DETACHED");
        getMap().removeLayer(getLayer());
        super.onDetach();
    }

    @Override
    protected void onAttach() {
        VConsole.error("ATTACHED");
        super.onAttach();
    }

    protected String getProjection() {
        return projection;
    }

    protected String getDisplayName() {
        return displayName;
    }

}
