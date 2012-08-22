package org.vaadin.vol.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public abstract class AbstractVOLTest extends CssLayout {

    protected VerticalLayout content;

    public AbstractVOLTest() {
        setSizeFull();
        content = new VerticalLayout();
        addComponent(content);
    }
    
    public VerticalLayout getContent() {
        return content;
    }

    @Override
    public void attach() {
        super.attach();
        setup();
        Notification.show(getClass().getSimpleName() + " : " + getDescription());
    }

    protected void setup() {
        Component map = getMap();
        content.setSizeFull();
        content.addComponent(map);
        content.setExpandRatio(map, 1);
    }

    abstract Component getMap();

    /*
     * This set of classes is used when a public demo app is built
     */
    @SuppressWarnings("unchecked")
    static Class<? extends AbstractVOLTest>[] a = new Class[] { Demo.class,
            ActionHandlers.class, BingMapTypes.class, ImageLayerExample.class,
            MarkerAddingAndRemoving.class, OpenStreetMapTypes.class,
            StyleMapAddUniqueValueRules.class,
            UsHighWaysOnTopOfSpherialMercator.class, VectorAnimation.class,
            WellKnownTextTest.class, StyledWmsWithOpacityAndPointInfo.class };
    static Collection<Class<? extends AbstractVOLTest>> suitableOnlineDemos = Collections
            .synchronizedSet(new HashSet<Class<? extends AbstractVOLTest>>(
                    Arrays.asList(a)));

    public boolean isSuitebleOnlineDemo() {
        return suitableOnlineDemos.contains(getClass());
    }

    public static ArrayList<Class<? extends AbstractVOLTest>> getDemoClasses() {
        return new ArrayList<Class<? extends AbstractVOLTest>>(
                suitableOnlineDemos);
    }

}
