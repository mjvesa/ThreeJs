package com.github.mjvesa.threejs.demo;

import com.github.mjvesa.threejs.ThreeJs;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
@Title("Three Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class,
            widgetset = "com.github.mjvesa.threejs.demo.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        // Initialize our new UI component
        final ThreeJs three = new ThreeJs();
        three.loadObj(new ThemeResource("vddin.obj"), new ThemeResource(
                "steel.jpg"), 0x303030, 0xdddddd, 0xffffff, 30);
        three.startAnimation();
        three.setSizeFull();
        
        three.addDirectionalLight("light", 0xffffff, 0.5);
        three.setDirectionalLightPosition("light", 0, 0, 2);

        // Show it in the middle of the screen
        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();
        layout.addComponent(three);
        layout.setExpandRatio(three, 1);
        layout.setComponentAlignment(three, Alignment.MIDDLE_CENTER);
        setContent(layout);

    }
}
