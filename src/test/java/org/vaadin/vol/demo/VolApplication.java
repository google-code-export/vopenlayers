package org.vaadin.vol.demo;

import java.io.File;
import java.util.ArrayList;

import org.jsoup.nodes.Element;

import com.vaadin.Application;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.BootstrapFragmentResponse;
import com.vaadin.server.BootstrapListener;
import com.vaadin.server.BootstrapPageResponse;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.UIProvider;
import com.vaadin.server.WrappedRequest;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.UI;

public class VolApplication extends Application {

    private Container testClassess;

    public VolApplication() {
        
        addUIProvider(new UIProvider() {
            
            @Override
            public UI instantiateUI(Application application, Class<? extends UI> type,
                    WrappedRequest request) {
                UI root = new UI() {

                    @Override
                    protected void init(WrappedRequest request) {
                        String name = request.getRequestPathInfo().substring(1);
                        try {

                            String className = getClass().getPackage().getName() + "."
                                    + name;
                            Class<?> forName = Class.forName(className);
                            if (forName != null) {
                                AbstractVOLTest newInstance = (AbstractVOLTest) forName
                                        .newInstance();
                                setContent(newInstance);
                                return;
                            }
                        } catch (ClassNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        CssLayout cssLayout = new CssLayout();
                        loadTestClasses(cssLayout);
                        setContent(cssLayout);
                    }
                };
                return root;
            }
            
            @Override
            public Class<? extends UI> getUIClass(Application application,
                    WrappedRequest request) {
                return UI.class;
            }
        });
        
        addBootstrapListener(new BootstrapListener() {

            @Override
            public void modifyBootstrapPage(BootstrapPageResponse response) {
                Element h = response.getDocument().getElementsByTag("head").get(0);

                Element s = response.getDocument().createElement("script");
                s.attr("src", "http://openlayers.org/api/OpenLayers.js");
                h.appendChild(s);
                
                s = response.getDocument().createElement("script");
                s.attr("src", "http://maps.google.com/maps/api/js?v=3.2&sensor=false");
                h.appendChild(s);
                

            }

            @Override
            public void modifyBootstrapFragment(
                    BootstrapFragmentResponse response) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void loadTestClasses(ComponentContainer window) {
        window.addComponent(new Label(
                "Note, all tests below might not work! "
                        + "They are mostly code examples and tests that might e.g."
                        + " require a local or some custom configured map server. "
                        + "See more and source codes available at: "
                        + "http://code.google.com/p/vopenlayers/source/browse/#svn%2Ftrunk%2Fsrc%2Ftest%2Fjava%2Forg%2Fvaadin%2Fvol%2Fdemo"));
        if (testClassess != null) {
            return;
        }
        testClassess = listTestClasses();
        Table table = new Table("Test cases", testClassess);
        table.addGeneratedColumn("name", new ColumnGenerator() {
            public Object generateCell(Table source, Object itemId,
                    Object columnId) {
                String name = (String) source.getItem(itemId)
                        .getItemProperty(columnId).getValue();
                Link link = new Link(name,
                        new ExternalResource(getURL() + name));
                link.setTargetName("_new");
                return link;
            }
        });
        table.addGeneratedColumn("description", new ColumnGenerator() {
            public Object generateCell(Table source, Object itemId,
                    Object columnId) {
                String description = (String) source.getItem(itemId)
                        .getItemProperty(columnId).getValue();
                return new Label(description);
            }
        });
        table.setWidth("100%");
        table.setColumnExpandRatio("description", 1);
        window.addComponent(table);
    }

    private Container listTestClasses() {
        IndexedContainer indexedContainer = new IndexedContainer();
        indexedContainer.addContainerProperty("name", String.class, "");
        indexedContainer.addContainerProperty("description", String.class, "");

        File file = new File("./src/test/java/org/vaadin/vol/demo");
        if (file.exists()) {
            indexedContainer.addContainerProperty("Suitble as online demo",
                    Boolean.class, Boolean.FALSE);
            File[] listFiles = file.listFiles();
            for (File f : listFiles) {
                try {
                    String name = f.getName();
                    String simpleName = name
                            .substring(0, name.indexOf(".java"));
                    String fullname = "org.vaadin.vol.demo." + simpleName;
                    Class<?> forName = Class.forName(fullname);
                    addTest(indexedContainer, simpleName, forName);
                } catch (Exception e) {
                    // e.printStackTrace();
                }

            }
        } else {
            ArrayList<Class<? extends AbstractVOLTest>> demoClasses = AbstractVOLTest
                    .getDemoClasses();
            for (Class<? extends AbstractVOLTest> class1 : demoClasses) {
                try {
                    addTest(indexedContainer, class1.getSimpleName(), class1);
                } catch (Exception e) {
                    // NOP
                }
            }
        }

        return indexedContainer;
    }

    private void addTest(IndexedContainer indexedContainer, String simpleName,
            Class<?> forName) throws InstantiationException,
            IllegalAccessException {
        if (AbstractVOLTest.class.isAssignableFrom(forName)) {
            AbstractVOLTest newInstance = (AbstractVOLTest) forName
                    .newInstance();
            Object id = indexedContainer.addItem();
            Item item = indexedContainer.getItem(id);
            item.getItemProperty("name").setValue(simpleName);
            // TODO load class and add description (also to test
            // cases)
            item.getItemProperty("description").setValue(
                    newInstance.getDescription());
            item.getItemProperty("Suitble as online demo").setValue(
                    newInstance.isSuitebleOnlineDemo());
        }
    }

}
