
package org.vaadin.vol;

import com.vaadin.shared.Connector;
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.Component;
import com.vaadin.util.ReflectTools;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

import org.vaadin.vol.client.VectorLayerState;

public class VectorLayer extends AbstractComponentContainer implements Layer {

    public enum SelectionMode {
        NONE, SIMPLE
        // MULTI, MULTI_WITH_AREA_SELECTION etc
    }

    public enum DrawingMode {
        NONE, LINE, AREA, RECTANGLE, CIRCLE, POINT, MODIFY
    }

    @Override
    public VectorLayerState getState() {
        return VectorLayerState.class.cast(super.getState());
    }

    public void addVector(Vector m) {
        addComponent(m);
    }

    public void replaceComponent(Component oldComponent, Component newComponent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getComponentCount() {
        return this.getState().vectors.size();
    }

    @Override
    public Iterator<Component> iterator() {
        ArrayList<Component> list = new ArrayList<Component>(getState().vectors.size());
        for (Connector connector : getState().vectors) {
            list.add((Component)connector);
        }
        return list.iterator();
    }


    @Override
    public void addComponent(Component c) {
        if (c instanceof Vector) {
            getState().vectors.add((Vector)c);
            super.addComponent(c);
        } else {
            throw new IllegalArgumentException(
                    "VectorLayer supports only Vectors");
        }
    }

    @Override
    public void removeComponent(Component c) {
        getState().vectors.remove(c);
        super.removeComponent(c);
        if (getState().selectedVector == c) {
            getState().selectedVector = null;
            fireEvent(new VectorUnSelectedEvent(this, (Vector) c));
        }
        markAsDirty();
    }

    public void setDrawingMode(DrawingMode drawingMode) {
        getState().drawingMode = drawingMode.toString();
        markAsDirty();
    }

    public DrawingMode getDrawingMode() {
        return DrawingMode.valueOf(getState().drawingMode);
    }

    /*@Override
    public void changeVariables(Object source, Map<String, Object> variables) {
        super.changeVariables(source, variables);
        // support other drawing modes than area
        // TODO make events fired when new object is drawn/edited
        if (variables.containsKey("vertices")) {
            String[] object = (String[]) variables.get("vertices");
            Point[] points = new Point[object.length];
            for (int i = 0; i < points.length; i++) {
                points[i] = Point.valueOf(object[i]);
            }

            if (drawingMode == DrawingMode.LINE) {
                PolyLine polyline = new PolyLine();
                polyline.setPoints(points);
                newVectorPainted(polyline);
            } else if (drawingMode == DrawingMode.AREA
                    || drawingMode == DrawingMode.RECTANGLE
                    || drawingMode == DrawingMode.CIRCLE) {
                Area area = new Area();
                area.setPoints(points);
                newVectorPainted(area);
            } else if (drawingMode == DrawingMode.MODIFY) {
                Vector vector = (Vector) variables.get("modifiedVector");
                if (vector != null) {
                    vector.setPointsWithoutRepaint(points);
                    vectorModified(vector);
                } else {
                    Logger.getLogger(getClass().getName())
                            .severe("Vector modified event didn't provide related vector!?");
                }
            }
        }
        if (drawingMode == DrawingMode.POINT && variables.containsKey("x")) {
            Double x = (Double) variables.get("x");
            Double y = (Double) variables.get("y");
            PointVector point = new PointVector(x, y);
            newVectorPainted(point);
        }
        if (variables.containsKey("vusel")) {
            Vector object = (Vector) variables.get("vusel");
            if (selectedVector == object) {
                selectedVector = null;
            }
            VectorUnSelectedEvent vectorSelectedEvent = new VectorUnSelectedEvent(
                    this, object);
            fireEvent(vectorSelectedEvent);
        }
        if (variables.containsKey("vsel")) {
            Vector object = (Vector) variables.get("vsel");
            selectedVector = object;
            VectorSelectedEvent vectorSelectedEvent = new VectorSelectedEvent(
                    this, object);
            fireEvent(vectorSelectedEvent);
        }
    }*/

    private void vectorModified(Vector object2) {
        VectorModifiedEvent vectorModifiedEvent = new VectorModifiedEvent(this, object2);
        fireEvent(vectorModifiedEvent);
    }

    protected void newVectorPainted(Vector vector) {
        VectorDrawnEvent vectorDrawnEvent = new VectorDrawnEvent(this, vector);
        fireEvent(vectorDrawnEvent);
        markAsDirty();
    }

    public interface VectorDrawnListener {

        public final Method method = ReflectTools.findMethod(
          VectorDrawnListener.class, "vectorDrawn",
          VectorDrawnEvent.class);

        public void vectorDrawn(VectorDrawnEvent event);

    }

    public void addVectorDrawnListener(VectorDrawnListener listener) {
        addListener(VectorDrawnEvent.class, listener,
                VectorDrawnListener.method);
    }
    @Deprecated
    public void addListener(VectorDrawnListener listener) {
        this.addVectorDrawnListener(listener);
    }

    public void removeVectorDrawnListener(VectorDrawnListener listener) {
        removeListener(VectorDrawnEvent.class, listener,
                VectorDrawnListener.method);
    }
    @Deprecated
    public void removeListener(VectorDrawnListener listener) {
        this.removeVectorDrawnListener(listener);
    }

    public interface VectorModifiedListener {

        public final Method method = ReflectTools.findMethod(
                VectorModifiedListener.class, "vectorModified",
                VectorModifiedEvent.class);

        public void vectorModified(VectorModifiedEvent event);

    }

    public void addVectorModifiedListener(VectorModifiedListener listener) {
        addListener(VectorModifiedEvent.class, listener,
                VectorModifiedListener.method);
    }
    @Deprecated
    public void addListener(VectorModifiedListener listener) {
        this.addVectorModifiedListener(listener);
    }

    public void removeVectorModifiedListener(VectorModifiedListener listener) {
        removeListener(VectorModifiedEvent.class, listener,
                VectorModifiedListener.method);
    }
    @Deprecated
    public void removeListener(VectorModifiedListener listener) {
        this.removeVectorModifiedListener(listener);
    }

    public void setDisplayName(String displayName) {
        this.getState().displayName = displayName;
    }

    public String getDisplayName() {
        return getState().displayName;
    }

    /**
     * @return the stylemap
     */
    public StyleMap getStyleMap() {
        return getState().stylemap;
    }

    /**
     * @param stylemap
     *            the stylemap to set
     */
    public void setStyleMap(StyleMap stylemap) {
        this.getState().stylemap = stylemap;
        markAsDirty();
    }

    public String getSelectionCtrlId() {
        return getState().selectionCtrlId;
    }

    public void setSelectionCtrlId(String selectionCtrlId) {
        this.getState().selectionCtrlId = selectionCtrlId;
    }

    public void setSelectionMode(SelectionMode selectionMode) {
        this.getState().selectionMode = selectionMode.toString();
        markAsDirty();
    }

    public SelectionMode getSelectionMode() {
        return SelectionMode.valueOf(getState().selectionMode);
    }

    public class VectorDrawnEvent extends Event {

        private Vector vector;

        public VectorDrawnEvent(Component source, Vector vector) {
            super(source);
            setVector(vector);
        }

        private void setVector(Vector vector) {
            this.vector = vector;
        }

        public Vector getVector() {
            return vector;
        }

    }

    public class VectorModifiedEvent extends Event {

        private Vector vector;

        public VectorModifiedEvent(Component source, Vector vector) {
            super(source);
            setVector(vector);
        }

        private void setVector(Vector vector) {
            this.vector = vector;
        }

        public Vector getVector() {
            return vector;
        }

    }

    public interface VectorSelectedListener {

        public final String EVENT_ID = "vsel";

        public final Method method = ReflectTools.findMethod(
                VectorSelectedListener.class, "vectorSelected",
                VectorSelectedEvent.class);

        public void vectorSelected(VectorSelectedEvent event);

    }

    public void addVectorSelectedListener(VectorSelectedListener listener) {
        addListener(VectorSelectedListener.EVENT_ID, VectorSelectedEvent.class,
                listener, VectorSelectedListener.method);
    }
    @Deprecated
    public void addListener(VectorSelectedListener listener) {
        this.addVectorSelectedListener(listener);
    }

    public void removeVectorSelectedListener(VectorSelectedListener listener) {
        removeListener(VectorSelectedListener.EVENT_ID,
                VectorSelectedEvent.class, listener);
    }
    @Deprecated
    public void removeListener(VectorSelectedListener listener) {
        this.addVectorSelectedListener(listener);
    }

    public class VectorSelectedEvent extends Event {

        private Vector vector;

        public VectorSelectedEvent(Component source, Vector vector) {
            super(source);
            setVector(vector);
        }

        private void setVector(Vector vector) {
            this.vector = vector;
        }

        public Vector getVector() {
            return vector;
        }

    }

    public interface VectorUnSelectedListener {

        public final String EVENT_ID = "vusel";

        public final Method method = ReflectTools.findMethod(
                VectorUnSelectedListener.class, "vectorUnSelected",
                VectorUnSelectedEvent.class);

        public void vectorUnSelected(VectorUnSelectedEvent event);

    }

    public void addVectorUnSelectedListener(VectorUnSelectedListener listener) {
        addListener(VectorUnSelectedListener.EVENT_ID,
                VectorUnSelectedEvent.class, listener,
                VectorUnSelectedListener.method);
    }
    @Deprecated
    public void addListener(VectorUnSelectedListener listener) {
        this.addVectorUnSelectedListener(listener);
    }

    public void removeVectorUnSelectedListener(VectorUnSelectedListener listener) {
        removeListener(VectorUnSelectedListener.EVENT_ID, VectorUnSelectedEvent.class, listener);
    }
    @Deprecated
    public void removeListener(VectorUnSelectedListener listener) {
        this.removeVectorUnSelectedListener(listener);
    }

    public Vector getSelectedVector() {
        return (Vector)getState().selectedVector;
    }

    public void setSelectedVector(Vector selectedVector) {
        if (this.getState().selectedVector != selectedVector) {
            if (this.getState().selectedVector != null) {
                fireEvent(new VectorUnSelectedEvent(this, (Vector)this.getState().selectedVector));
            }
            this.getState().selectedVector = selectedVector;
            if (selectedVector != null) {
                fireEvent(new VectorSelectedEvent(this, selectedVector));
            }
            markAsDirty();
        }
    }

    public class VectorUnSelectedEvent extends Event {

        private Vector vector;

        public VectorUnSelectedEvent(Component source, Vector vector) {
            super(source);
            setVector(vector);
        }

        private void setVector(Vector vector) {
            this.vector = vector;
        }

        public Vector getVector() {
            return vector;
        }

    }

}
