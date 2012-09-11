package org.vaadin.vol.client.ui.vcom;

import org.vaadin.vol.GoogleTerrainMapLayer;
import org.vaadin.vol.client.ui.VGoogleTerrainMapLayer;

import com.vaadin.client.ui.LegacyConnector;
import com.vaadin.shared.ui.Connect;

@Connect(GoogleTerrainMapLayer.class)
public class GoogleTerrainMapLayerConnector extends LegacyConnector {

    @Override
    public VGoogleTerrainMapLayer getWidget() {
        return (VGoogleTerrainMapLayer) super.getWidget();
    }
    
}
