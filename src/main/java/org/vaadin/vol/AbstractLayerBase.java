package org.vaadin.vol;

import com.vaadin.ui.Component;
import com.vaadin.util.ReflectTools;

import java.lang.reflect.Method;

import org.vaadin.vol.client.LayerBaseState;

/*
 * Layer base class to handle the basic layer events
 *
 *  The move events currently not handled. They should handled in map.
 *  I don't implement listeners for add or remove layer because I found no
 *  practical use case :-D
 */
abstract public class AbstractLayerBase extends AbstractProjectedLayer {

    /*
     * add a listener to layers 'loadstart' event
     */
    public void addLoadStartListener(LoadStartListener listener) {
        addListener(LoadStartListener.EVENT_ID, LoadStartEvent.class, listener,
          LoadStartListener.method);
    }
    @Deprecated
    public void addListener(LoadStartListener listener) {
        this.addLoadStartListener(listener);
    }

    public void removeLoadStartListener(LoadStartListener listener) {
        removeListener(LoadStartListener.EVENT_ID, LoadStartEvent.class,
                listener);
    }
    @Deprecated
    public void removeListener(LoadStartListener listener) {
        this.removeLoadStartListener(listener);
    }

    /*
     * add a listener to layers 'loadend' event
     */
    public void addLoadEndListener(LoadEndListener listener) {
        addListener(LoadEndListener.EVENT_ID, LoadEndEvent.class, listener,
                LoadEndListener.method);
    }
    @Deprecated
    public void addListener(LoadEndListener listener) {
        this.addLoadEndListener(listener);
    }

    public void removeLoadEndListener(LoadEndListener listener) {
        removeListener(LoadEndListener.EVENT_ID, LoadEndEvent.class, listener);
    }
    @Deprecated
    public void removeListener(LoadEndListener listener) {
        this.removeLoadEndListener(listener);
    }

    /*
     * add a listener to layers 'visabilitychanged' event
     */
    public void addVisibilityChangeListener(VisibilityChangedListener listener) {
        addListener(VisibilityChangedListener.EVENT_ID,
                VisibilityChangedEvent.class, listener,
                VisibilityChangedListener.method);
    }
    @Deprecated
    public void addListener(VisibilityChangedListener listener) {
        this.addVisibilityChangeListener(listener);
    }

    public void removeVisibilityChangedListener(VisibilityChangedListener listener) {
        removeListener(VisibilityChangedListener.EVENT_ID,
                VisibilityChangedEvent.class, listener);
    }
    @Deprecated
    public void removeListener(VisibilityChangedListener listener) {
        this.removeVisibilityChangedListener(listener);
    }

    /*@SuppressWarnings("unchecked")
    @Override
    public void changeVariables(Object source, Map<String, Object> variables) {
        super.changeVariables(source, variables);
        if (variables.get(LoadStartListener.EVENT_ID) != null) {
            LoadStartEvent event = new LoadStartEvent(this,
                    (String) variables.get(LNAME));
            fireEvent(event);
        } else if (variables.get(LoadEndListener.EVENT_ID) != null) {
            LoadEndEvent event = new LoadEndEvent(this,
                    (String) variables.get(LNAME));
            fireEvent(event);
        } else if (variables.get(VisibilityChangedListener.EVENT_ID) != null) {
            VisibilityChangedEvent event = new VisibilityChangedEvent(this,
                    (String) variables.get(LNAME),
                    (Boolean) variables.get(LVIS));
            fireEvent(event);
        }
    }*/

    @Override
    public LayerBaseState getState() {
        return LayerBaseState.class.cast(super.getState());
    }

    public String getAttribution() {
        return this.getState().attribution;
    }

    /**
     * Sets the attribution text for layer. Feature is not functional in all layers.
     *
     * @param attribution
     */
    public void setAttribution(String attribution) {
        this.getState().attribution = attribution;
    }

    public interface LoadStartListener {
        public final String EVENT_ID = "llstart";

        public final Method method = ReflectTools.findMethod(
          LoadStartListener.class, "loadStart", LoadStartEvent.class);

        public void loadStart(LoadStartEvent event);
    }

    public interface LoadEndListener {
        public final String EVENT_ID = "llend";

        public final Method method = ReflectTools.findMethod(
                LoadEndListener.class, "loadEnd", LoadEndEvent.class);

        public void loadEnd(LoadEndEvent event);
    }

    public interface VisibilityChangedListener {
        public final String EVENT_ID = "lvis";

        public final Method method = ReflectTools.findMethod(
                VisibilityChangedListener.class, "visibilityChanged",
                VisibilityChangedEvent.class);

        public void visibilityChanged(VisibilityChangedEvent event);
    }

    public class LayerBaseEvent extends Event {
        private String layerName;

        public LayerBaseEvent(Component source, String layerName) {
            super(source);
            this.layerName = layerName;
        }

        public String getLayerName() {
            return layerName;
        }
    }

    public class LoadStartEvent extends LayerBaseEvent {
        public LoadStartEvent(Component source, String layerName) {
            super(source, layerName);
        }
    }

    public class LoadEndEvent extends LayerBaseEvent {
        public LoadEndEvent(Component source, String layerName) {
            super(source, layerName);
        }
    }

    public class VisibilityChangedEvent extends LayerBaseEvent {
        private boolean visible;

        public VisibilityChangedEvent(Component source, String layerName, Boolean visible) {
            super(source, layerName);

            // false only in case visible variable not set
            this.visible = visible != null && visible;
        }

        public boolean isVisible() {
            return this.visible;
        }
    }

    private final static String LNAME = "lname";
    private final static String LVIS = "lisvis";
}
