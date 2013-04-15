package org.vaadin.vol.client.ui;

import java.util.Date;

import org.vaadin.vol.client.wrappers.Projection;
import org.vaadin.vol.client.wrappers.Vector;
import org.vaadin.vol.client.wrappers.geometry.Point;
import org.vaadin.vol.client.wrappers.layer.VectorLayer;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.UIDL;
import com.vaadin.terminal.gwt.client.VConsole;
import com.vaadin.terminal.gwt.client.ValueMap;

public class VAnimatedPointVector extends VAbstractVector {

    private JsArrayString keyArray;
    private ValueMap mapAttribute;
    private long startTime;
    private Projection mapProjection;

    @Override
    protected void createOrUpdateVector(UIDL childUIDL,
            ApplicationConnection client) {
        mapProjection = getMap().getProjection();

        mapAttribute = childUIDL.getMapAttribute("keyframes");
        keyArray = mapAttribute.getKeyArray();

        // Use first point as default
        // TODO this should support popping up at a specific time if first
        // keyframe is not at time 0!!
        Point p = Point.create(mapAttribute.getString(keyArray.get(0)));
        p.transform(getProjection(), mapProjection);

        JavaScriptObject style = null;
        ValueMap attributes = getAttributes();
        if (vector == null) {
            vector = Vector.create(p, attributes, style);
        } else {
            vector.setGeometry(p);
            vector.setStyle(style);
            vector.setAttributes(attributes);
        }

        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
            public void execute() {
                startTime = new Date().getTime();
                animationIndex = 0;
                nextAnimation();
            }
        });

    }

    int animationIndex = 0;

    private void nextAnimation() {
        try {
            if (isAttached() && animationIndex < keyArray.length()) {

                String firstTimeStamp = keyArray.get(animationIndex);
                String secondTimeStamp = keyArray.get(animationIndex + 1);
                animationIndex++;

                Point startPoint = Point.create(mapAttribute
                        .getString(firstTimeStamp));
                startPoint.transform(getProjection(), mapProjection);
                Point endPoint = Point.create(mapAttribute
                        .getString(secondTimeStamp));
                endPoint.transform(getProjection(), mapProjection);

                int nowFromStart = (int) (new Date().getTime() - startTime);
                int secondTime = Integer.parseInt(secondTimeStamp);
                int duration = secondTime - nowFromStart;

                getLayer().eraseFeature(vector);
                vector.setGeometry(startPoint);
                if (duration < 0) {
                    nextAnimation();
                } else {
                    animateTo(startPoint, endPoint, duration);
                }
            } else {
                VConsole.log("Animation done");
            }
        } catch (Exception e) {
            VConsole.log(e);
        }

    }

    private void animateTo(final Point startPoint, final Point endPoint,
            final int duration) {
        final VectorLayer layer = getLayer();
        new Animation() {
            @Override
            protected void onUpdate(double progress) {
                if (!isAttached()) {
                    cancel();
                    return;
                }
                double lon = startPoint.getLongitude() + progress
                        * (endPoint.getLongitude() - startPoint.getLongitude());
                double lat = startPoint.getLatitude() + progress
                        * (endPoint.getLatitude() - startPoint.getLatitude());
                Point p = Point.create(lon, lat);
                layer.eraseFeature(vector);
                vector.setGeometry(p);
                vector.redraw();
            }

            protected void onComplete() {
                nextAnimation();
            };

            protected double interpolate(double progress) {
                return progress;
            };

            protected void onCancel() {
                VConsole.log("Stopped animation.");
                layer.eraseFeature(vector);
            };
        }.run(duration);
    }

}
