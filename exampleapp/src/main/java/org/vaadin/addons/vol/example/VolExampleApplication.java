/* 
 * Copyright 2009 IT Mill Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.vaadin.addons.vol.example;

import org.vaadin.vol.Area;
import org.vaadin.vol.GoogleStreetMapLayer;
import org.vaadin.vol.Marker;
import org.vaadin.vol.MarkerLayer;
import org.vaadin.vol.OpenLayersMap;
import org.vaadin.vol.OpenStreetMapLayer;
import org.vaadin.vol.Point;
import org.vaadin.vol.Popup;
import org.vaadin.vol.Popup.CloseEvent;
import org.vaadin.vol.Popup.CloseListener;
import org.vaadin.vol.Popup.PopupStyle;
import org.vaadin.vol.Vector;
import org.vaadin.vol.VectorLayer;
import org.vaadin.vol.VectorLayer.DrawingMode;
import org.vaadin.vol.VectorLayer.VectorDrawnEvent;
import org.vaadin.vol.VectorLayer.VectorDrawnListener;
import org.vaadin.vol.VectorLayer.VectorModifiedEvent;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * Demonstration how to build a simple Vaadin application using OpenStreeMap.
 */
@SuppressWarnings("serial")
public class VolExampleApplication extends com.vaadin.Application {
    private final VerticalLayout layout = new VerticalLayout();
    private final HorizontalLayout controls = new HorizontalLayout();
    private final Window mainWindow = new Window("Vol example Application",
            layout);

    @Override
    public void init() {
        setMainWindow(mainWindow);
        final OpenLayersMap map = new OpenLayersMap();
        map.setImmediate(true); // update extent and zoom to server as they
                                // change

        /*
         * Open street maps layer as a base layer. Note importance of the order,
         * OSM layer now sets the projection to Spherical Mercator. If added eg.
         * after markers or vectors, they might render with bad values.
         */
        OpenStreetMapLayer osm = new OpenStreetMapLayer();

        GoogleStreetMapLayer googleStreets = new GoogleStreetMapLayer();

        /*
         * This is used in example to demonstrate drawing capabilities.
         */
        final VectorLayer vectorLayer = new VectorLayer();

        /*
         * Draw triangle over vaadin hq.
         */

        Point[] points = new Point[3];
        points[0] = new Point(22.29, 60.45);
        points[1] = new Point(22.30, 60.46);
        points[2] = new Point(22.31, 60.45);

        Area area = new Area();
        area.setPoints(points);

        vectorLayer.addVector(area);

        // Define a Marker Layer
        MarkerLayer markerLayer = new MarkerLayer();

        // Defining a new Marker

        final Marker marker = new Marker(22.30083, 60.452541);
        // URL of marker Icon
        marker.setIcon("http://dev.vaadin.com/chrome/site/vaadin-trac.png", 60,
                20);

        // Add some server side integration when clicking a marker
        marker.addClickListener(new ClickListener() {
            public void click(ClickEvent event) {
                final Popup popup = new Popup(marker.getLon(), marker.getLat(),
                        "Vaadin HQ is <em>here</em>!");
                popup.setAnchor(marker);
                popup.setPopupStyle(PopupStyle.FRAMED_CLOUD);
                popup.addListener(new CloseListener() {
                    public void onClose(CloseEvent event) {
                        map.removeComponent(popup);
                    }
                });
                map.addPopup(popup);
            }
        });

        // Add the marker to the marker Layer
        markerLayer.addMarker(marker);
        map.setCenter(22.30, 60.452);
        map.setZoom(15);

        map.setSizeFull();

        OptionGroup drawingMode = new OptionGroup();
        for (DrawingMode l : VectorLayer.DrawingMode.values()) {
            drawingMode.addItem(l);
        }
        drawingMode.addListener(new Property.ValueChangeListener() {
            public void valueChange(ValueChangeEvent event) {
                DrawingMode mode = (DrawingMode) event.getProperty().getValue();
                if (mode == DrawingMode.MODIFY || mode == DrawingMode.AREA
                        || mode == DrawingMode.LINE
                        || mode == DrawingMode.POINT
                        || mode == DrawingMode.NONE) {
                    vectorLayer.setDrawindMode(mode);
                } else {
                    mainWindow
                            .showNotification("Sorry, feature is on TODO list. Try area.");
                }
            }
        });
        drawingMode.setValue(DrawingMode.NONE);
        drawingMode.setImmediate(true);

        vectorLayer.addListener(new VectorDrawnListener() {
            public void vectorDrawn(VectorDrawnEvent event) {
                /*
                 * Drawn vectors are passed to VectroDrawnListeners.
                 * 
                 * Here we justs simply add them to vector layer to persist them
                 * (other wise new vectors are dropped).
                 */

                Vector vector = event.getVector();
                vectorLayer.addVector(vector);
                vectorLayer.getWindow().showNotification(
                        "Vector drawn:" + vector);
            }
        });

        vectorLayer.addListener(new VectorLayer.VectorModifiedListener() {
            public void vectorModified(VectorModifiedEvent event) {
                vectorLayer.getWindow().showNotification(
                        "Vector modified:" + event.getVector());
            }
        });

        // add layers
        map.addLayer(osm);
        map.addLayer(googleStreets);

        map.addLayer(vectorLayer);
        map.addLayer(markerLayer);

        controls.addComponent(drawingMode);

        layout.setSizeFull();
        layout.addComponent(controls);
        layout.addComponent(map);
        layout.setExpandRatio(map, 1);

    }
}
