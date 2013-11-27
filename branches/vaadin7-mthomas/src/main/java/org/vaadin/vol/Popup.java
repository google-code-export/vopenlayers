/**
 *
 */
package org.vaadin.vol;

import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.util.ReflectTools;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;

import org.vaadin.vol.client.PopupState;

@SuppressWarnings("serial")
public class Popup extends AbstractComponentContainer {

    public class CloseEvent extends Event {
        public CloseEvent() {
            super(Popup.this);
        }
    }

    public interface CloseListener {
        public void onClose(CloseEvent event);

        final Method method = ReflectTools.findMethod(CloseListener.class,
          "onClose", CloseEvent.class);
        final String id = "close";
    }

    public enum PopupStyle {
        DEFAULT, ANCHORED, ANCHORED_BUBBLE, FRAMED, FRAMED_CLOUD
    }

    private Component content;

    public Popup(double lon, double lat, String content) {
        getState().point = new Point(lon, lat);
        setContent(content);
    }

    public Popup(String content) {
        setContent(content);
    }

    public Popup(Component content) {
        addComponent(content);
    }

    public Popup() {
        this("");
    }

    @Override
    public PopupState getState() {
        return PopupState.class.cast(super.getState());
    }

    public double getLon() {
        return getState().point.getLon();
    }

    public double getLat() {
        return getState().point.getLat();
    }

    public void setLon(double lon) {
        getState().point.setLon(lon);
        markAsDirty();
    }

    public void setLat(double lat) {
        getState().point.setLat(lat);
        markAsDirty();
    }

    public void setContent(String content) {
        Label c = new Label(content, ContentMode.HTML);
        c.setSizeUndefined();
        addComponent(c);
        markAsDirty();
    }

    @Override
    public void addComponent(Component c) {
        if(c == null)  {
            setContent("");
        } else {
            if(content != null) {
                removeAllComponents();
            }
            super.addComponent(c);
            content = c;
        }
    }

    @Override
    public void beforeClientResponse(boolean initial) {
        super.beforeClientResponse(initial);

        PopupStyle current = this.getPopupStyle();
        if (getState().anchor == null
          && (current == PopupStyle.FRAMED
          || current == PopupStyle.ANCHORED || current == PopupStyle.ANCHORED_BUBBLE)) {
            throw new IllegalStateException(
              "Anchor element hasn't been defined, but is required for this type of popup.");
        }
    }

    /*@Override
    public void paintContent(PaintTarget target) throws PaintException {
        super.paintContent(target);
        target.addAttribute("lon", point.getLon());
        target.addAttribute("lat", point.getLat());
        target.addAttribute("pr", projection);
        target.addAttribute("style", popupstyle.toString());
        target.addAttribute("closable", isClosable());
        if (anchor == null
                && (popupstyle == PopupStyle.FRAMED
                        || popupstyle == PopupStyle.ANCHORED || popupstyle == PopupStyle.ANCHORED_BUBBLE)) {
            throw new IllegalStateException(
                    "Anchor elemen hasn't been defined, but is required for this type of popup.");
        }
        if (anchor != null) {
            target.addAttribute("anchor", anchor);
        }
        content.paint(target);
    }*/

    public void addClickListener(ClickListener listener) {
        addListener("click", ClickEvent.class, listener,
          ClickListener.clickMethod);
    }

    public void removeClickListener(ClickListener listener) {
        removeListener(ClickEvent.class, listener);
    }

    /*@Override
    public void changeVariables(Object source, Map<String, Object> variables) {
        super.changeVariables(source, variables);
        if (variables.containsKey("close")) {
            if (hasListeners(CloseEvent.class)) {
                fireEvent(new CloseEvent());
            } else {
                Component parent2 = getParent();
                if (parent2 instanceof OpenLayersMap) {
                    OpenLayersMap olm = (OpenLayersMap) parent2;
                    olm.removeComponent(this);
                }
            }
        }
    }*/

    public void setPopupStyle(PopupStyle style) {
        getState().popupstyle = style.toString();
        markAsDirty();
    }

    public PopupStyle getPopupStyle() {
        return PopupStyle.valueOf(getState().popupstyle);
    }

    public void setAnchor(Marker marker) {
        getState().anchor = marker;
        markAsDirty();
    }

    public void addCloseListener(CloseListener listener) {
        super.addListener(CloseListener.id, CloseEvent.class, listener,
          CloseListener.method);
    }
    @Deprecated
    public void addListener(CloseListener listener) {
        this.addCloseListener(listener);
    }

    public void removeCloseListener(CloseListener listener) {
        super.removeListener(CloseListener.id, CloseEvent.class, listener);
    }
    @Deprecated
    public void removeListener(CloseListener listener) {
        this.removeCloseListener(listener);
    }

    public boolean isClosable() {
        return getState().closable;
    }

    public void setClosable(boolean closable) {
        this.getState().closable = closable;
    }

    public void replaceComponent(Component oldComponent, Component newComponent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getComponentCount() {
        return 1;
    }

    @Override
    public Iterator<Component> iterator() {
        return Collections.singleton(content).iterator();
    }

    @Override
    public void removeComponent(Component c) {
        if (c == content) {
            super.removeComponent(c);
            content = null;
        } else {
            throw new IllegalArgumentException(
                    "Given component is not in this popup");
        }
    }

    public void setContent(Table table) {
        addComponent(table);
    }
}
