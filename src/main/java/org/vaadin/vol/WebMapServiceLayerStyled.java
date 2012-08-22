package org.vaadin.vol;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;

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
