package org.vaadin.vol;

import com.vaadin.server.PaintException;
import com.vaadin.server.PaintTarget;

//@ClientWidget(VWebMapServiceLayerStyled.class)
public class WebMapServiceLayerStyled extends WebMapServiceLayer {

	private String sld = "";

	public WebMapServiceLayerStyled() {

	}

	public void paintContent(PaintTarget target) throws PaintException {
		super.paintContent(target);
		target.addAttribute("sld", sld);
	}

	public String getSld() {
		return sld;
	}

	public void setSld(String sld) {
		this.sld = sld;
		requestRepaint();
	}
}
