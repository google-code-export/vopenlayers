package org.vaadin.vol;

import java.util.Map;

/**
 * Attibutes class wraps a JavaScript Object
 */
public class Attributes extends JsObject {

    /**
     * return Attributes as a Map arrays
     */
    public Map<String, Object> getAttributes() {
        return this.getKeyValueMap();
    }
}
