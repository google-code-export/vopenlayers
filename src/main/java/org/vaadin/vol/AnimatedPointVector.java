package org.vaadin.vol;

import java.util.Map;
import java.util.TreeMap;

import org.vaadin.vol.client.ui.VAnimatedPointVector;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.ClientWidget;

@ClientWidget(VAnimatedPointVector.class)
public class AnimatedPointVector extends Vector {
    
        private Map<Integer, Point> keyframes = new TreeMap<Integer, Point>();
        
        public void addKeyFrame(int time, Point p) {
            keyframes.put(time, p);
            requestRepaint();
        }
	
	public AnimatedPointVector(double x, double y) {
		setPoints(new Point(x, y));
	}
	
	public AnimatedPointVector() {
		setPoints(new Point(0, 0));
	}
	
	public Point getPoint() {
		return getPoints()[0];
	}
	
	/**
	 * Note that for PointVector only one points is needed and handled.
	 * 
	 * @see org.vaadin.vol.Vector#setPoints(org.vaadin.vol.Point[])
	 */
	@Override
	public void setPoints(Point... points) {
		super.setPoints(points);
	}

	
	@Override
	public void paintContent(PaintTarget target) throws PaintException {
	    target.addAttribute("keyframes", keyframes);
	    super.paintContent(target);
	}

    public void clearKeyFrames() {
        keyframes.clear();
    }

}
