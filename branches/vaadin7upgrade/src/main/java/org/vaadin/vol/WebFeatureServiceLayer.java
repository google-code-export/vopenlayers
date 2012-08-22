/**
 * 
 */
package org.vaadin.vol;


//@ClientWidget(VWebFeatureServiceLayer.class)
public class WebFeatureServiceLayer extends AbstractAutoPopulatedVectorLayer implements Layer {
    private String uri = "";
    private String featureType = "basic";
    private String featureNS;


    public WebFeatureServiceLayer() {

    }
    
//    public void paintContent(PaintTarget target) throws PaintException {
//        super.paintContent(target);
//        target.addAttribute("uri", uri);
//        target.addAttribute("featureType", featureType);
//        target.addAttribute("featureNS", featureNS);
//    }
    
    public void setUri(String uri) {
        this.uri = uri;
        requestRepaint();
    }

    public String getUri() {
        return uri;
    }

    public void setFeatureType(String featureType) {
        this.featureType = featureType;
        requestRepaint();
    }

    public String getFeatureType() {
        return featureType;
    }

    public void setFeatureNS(String ns) {
        this.featureNS = ns;
    }

    public String getFeatureNS() {
        return featureNS;
    }

}