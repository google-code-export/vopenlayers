package org.vaadin.vol.demo;

import org.vaadin.vol.OpenLayersMap;
import org.vaadin.vol.OpenStreetMapLayer;
import org.vaadin.vol.PointVector;
import org.vaadin.vol.VectorLayer;

import com.vaadin.ui.Component;

public class PlayGround extends AbstractVOLTest {

    @Override
    public String getDescription() {
        return "Dippadai!";
    }

    @Override
    Component getMap() {
        OpenLayersMap map = new OpenLayersMap();

        map.addLayer(new OpenStreetMapLayer());
        // map.setHeight("500px");
        // map.setWidth("500px");
        map.setSizeFull();

        VectorLayer vectorLayer = new VectorLayer();
        PointVector pointVector = new PointVector(0, 0);
        vectorLayer.addComponent(pointVector);
        map.addLayer(vectorLayer);
        return map;
    }

}
