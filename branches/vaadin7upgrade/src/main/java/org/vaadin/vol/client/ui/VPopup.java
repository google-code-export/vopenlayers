package org.vaadin.vol.client.ui;

import org.vaadin.vol.client.wrappers.GwtOlHandler;
import org.vaadin.vol.client.wrappers.LonLat;
import org.vaadin.vol.client.wrappers.Map;
import org.vaadin.vol.client.wrappers.Marker;
import org.vaadin.vol.client.wrappers.Projection;
import org.vaadin.vol.client.wrappers.Size;
import org.vaadin.vol.client.wrappers.popup.Popup;
import org.vaadin.vol.client.wrappers.popup.PopupAnchored;
import org.vaadin.vol.client.wrappers.popup.PopupAnchoredBubble;
import org.vaadin.vol.client.wrappers.popup.PopupFramed;
import org.vaadin.vol.client.wrappers.popup.PopupFramedCloud;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ApplicationConnection;
import com.vaadin.client.Paintable;
import com.vaadin.client.UIDL;

public class VPopup extends Widget implements Paintable {

    private enum PopupStyle {
        DEFAULT, ANCHORED, ANCHORED_BUBBLE, FRAMED, FRAMED_CLOUD
    }

    private Popup popup;
    private GwtOlHandler closeEventHandler = new GwtOlHandler() {
        @SuppressWarnings("rawtypes")
        public void onEvent(JsArray arguments) {
            popup.hide();
            client.updateVariable(pid, "close", "", true);
        }
    };
    private ApplicationConnection client;
    private Paintable paintable;
    private String pid;

    public VPopup() {
        setElement(Document.get().createDivElement());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.terminal.gwt.client.Paintable#updateFromUIDL(com.vaadin.terminal
     * .gwt.client.UIDL, com.vaadin.terminal.gwt.client.ApplicationConnection)
     */
    public void updateFromUIDL(final UIDL childUIDL,
            final ApplicationConnection client) {
        if (client.updateComponent(this, childUIDL, false)) {
            return;
        }
        this.client = client;
        this.pid = childUIDL.getId();

        /*
         * With Popups, we always remove the old one and add a new one.
         */

        if (popup != null) {
            getMap().removePopup(popup);
        }
        double lon = childUIDL.getDoubleAttribute("lon");
        double lat = childUIDL.getDoubleAttribute("lat");
        final Projection projection = Projection.get(childUIDL
                .getStringAttribute("pr"));
        LonLat point = LonLat.create(lon, lat);
        Projection projection2 = getMap().getProjection();
        point.transform(projection, projection2);

        paintable = (Paintable) client.getPaintable(childUIDL.getChildUIDL(0)).getWidget();

        String content = "Loading...";

        PopupStyle style = PopupStyle.valueOf(childUIDL
                .getStringAttribute("style"));

        // TODO remove marker dependency
        Marker anchor = null;
        if (childUIDL.hasAttribute("anchor")) {
//            // FIXME
//            Paintable paintableAttribute = childUIDL.getPaintableAttribute(
//                    "anchor", client);
//            if (paintableAttribute != null) {
//                VMarker vanchor = (VMarker) paintableAttribute;
//                anchor = vanchor.getMarker();
//            }
        }

        boolean closable = childUIDL.getBooleanAttribute("closable");

        final String pid = childUIDL.getId() + "popup";
        switch (style) {
        case ANCHORED:
            popup = PopupAnchored.create(pid, point, null, content, anchor,
                    closable, closeEventHandler);
            break;
        case ANCHORED_BUBBLE:
            popup = PopupAnchoredBubble.create(pid, point, null, content,
                    anchor, closable, closeEventHandler);
            break;
        case FRAMED:
            popup = PopupFramed.create(pid, point, null, content, anchor,
                    closable, closeEventHandler);
            break;
        case FRAMED_CLOUD:
            popup = PopupFramedCloud.create(pid, point, null, content, anchor,
                    closable, closeEventHandler);
            break;

        case DEFAULT:
        default:
            popup = Popup.create(pid, point, null, content, closable,
                    closeEventHandler);
            break;
        }

        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
            public void execute() {
                getMap().addPopup(popup);
                Element elementById = Document.get().getElementById(
                        pid + "_contentDiv");
                elementById.setInnerHTML("");
                VOpenLayersMap parent2 = (VOpenLayersMap) getParent()
                        .getParent();
                parent2.attachSpecialWidget((Widget) paintable, elementById);
                paintable.updateFromUIDL(childUIDL.getChildUIDL(0), client);
                int offsetHeight = ((Widget) paintable).getOffsetHeight();
                int offsetWidth = ((Widget) paintable).getOffsetWidth();
                popup.setSize(Size.create(offsetWidth, offsetHeight));
            }
        });

    }

    private Map getMap() {
        return ((VOpenLayersMap) getParent().getParent()).getMap();
    }

    public Popup getPopup() {
        return popup;
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        ((Widget) paintable).removeFromParent();
        popup.hide();
//        // FIXME
//        client.unregisterPaintable(paintable);
    }

}
