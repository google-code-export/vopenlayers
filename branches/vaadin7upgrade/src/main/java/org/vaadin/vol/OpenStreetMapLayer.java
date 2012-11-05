/**
 * 
 */
package org.vaadin.vol;

import java.util.Map;

import com.vaadin.server.PaintException;
import com.vaadin.server.PaintTarget;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.LegacyComponent;

/**
 * OpenStreeMap layer that can be added to {@link OpenLayersMap}.
 */
//@ClientWidget(VOpenStreetMapLayer.class)
public class OpenStreetMapLayer extends AbstractComponent implements Layer, LegacyComponent {

    private String displayName;
    private String projection;

    @Override
    public void paintContent(PaintTarget target) throws PaintException {
        if (projection != null) {
            target.addAttribute("projection", projection);
        }
        if (displayName != null) {
            target.addAttribute("name", displayName);
        }
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setProjection(String projection) {
        this.projection = projection;
    }

    public String getProjection() {
        return projection;
    }
    
    @Override
    public void attach() {
        super.attach();
        requestRepaint();
    }

    @Override
    public void changeVariables(Object source, Map<String, Object> variables) {
        // TODO Auto-generated method stub
        
    }

}