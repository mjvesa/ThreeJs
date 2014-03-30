package com.github.mjvesa.threejs.demo;

import javax.servlet.annotation.WebServlet;

import com.github.mjvesa.threejs.ThreeJs;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalSplitPanel;

@Theme("demo")
@Title("Three Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    private CssLayout objView;

    public enum OBJECT {
        VAADIN, CASTLE, MOLECULE;
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class,
            widgetset = "com.github.mjvesa.threejs.demo.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        HorizontalLayout hl = new HorizontalLayout();
        hl.setSizeFull();
        hl.addComponent(createObjectSelect());
        objView = createObjectView();
        hl.addComponent(objView);
        hl.setExpandRatio(objView, 1);
        setContent(hl);
    }

    private CssLayout createObjectView() {
        CssLayout cl = new CssLayout();
        cl.setStyleName("demoContentLayout");
        cl.setSizeFull();
        return cl;
    }

    private Component createObjectSelect() {
        ComboBox cb = new ComboBox();
        cb.addItem(OBJECT.VAADIN);
        cb.addItem(OBJECT.MOLECULE);
        cb.addItem(OBJECT.CASTLE);
        cb.setImmediate(true);

        cb.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(ValueChangeEvent event) {

                OBJECT obj = (OBJECT) event.getProperty().getValue();

                final ThreeJs three = new ThreeJs();

                switch (obj) {
                case VAADIN:
                    // three.loadObj(new ThemeResource("3whd.obj"), new
                    // ThemeResource(
                    // "steel.jpg"), 0x303030, 0xdddddd, 0xffffff, 30);
                    three.loadObj(new ThemeResource("vaadin.obj"), 0x303030,
                            0xdddddd, 0xffffff, 30);
                    three.setSizeFull();

                    three.addDirectionalLight("light", 0xffffff, 0.5);
                    three.setDirectionalLightPosition("light", 0, 0, 2);

                    break;
                case MOLECULE:
                    three.loadObj(new ThemeResource("3whb.obj"), 0x303030,
                            0xdddddd, 0xffffff, 30);
                    three.setSizeFull();

                    three.addDirectionalLight("light", 0xffffff, 0.5);
                    three.setDirectionalLightPosition("light", 0, 0, 2);

                    break;
                case CASTLE:
                    three.loadObj(new ThemeResource("castle.obj"), 0x303030,
                            0xdddddd, 0xffffff, 30);
                    three.setSizeFull();

                    three.addDirectionalLight("light", 0xffffff, 0.5);
                    three.setDirectionalLightPosition("light", 0, 0, 2);

                    break;

                }
                objView.removeAllComponents();
                objView.addComponent(three);
                three.startAnimation();
            }
        });

        return cb;
    }
}
