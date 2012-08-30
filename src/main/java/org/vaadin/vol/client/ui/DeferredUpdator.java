package org.vaadin.vol.client.ui;

import java.util.ArrayList;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;

/**
 * This is a really stupid workaround for really stupid feature in Vaadin 7
 * widget backwards "compatibility".
 * 
 */
public class DeferredUpdator {

    private static ArrayList<VAbstractVector> vectors = new ArrayList<VAbstractVector>();

    private static boolean deferred = false;

    public static void update(VAbstractVector vAbstractVector) {
        vectors.add(vAbstractVector);
        if (!deferred) {
            Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                @Override
                public void execute() {
                    for (VAbstractVector v : vectors) {
                        v.reallyDoUpdate();
                    }
                    vectors.clear();
                    deferred = false;
                }
            });
            deferred = true;
        }
    }

}
