/**
 * 
 */
package org.vaadin.vol;

import com.vaadin.ui.AbstractComponent;

/**
 * OpenStreeMap layer that can be added to {@link OpenLayersMap}.
 */
//@ClientWidget(VOpenStreetMapLayer.class)
public class OpenStreetMapLayer extends AbstractComponent implements Layer {

    private String displayName;
    private String projection;

//    @Override
//    public void paintContent(PaintTarget target) throws PaintException {
//        super.paintContent(target);
//        if (projection != null) {
//            target.addAttribute("projection", projection);
//        }
//        if (displayName != null) {
//            target.addAttribute("name", displayName);
//        }
//    }

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

}