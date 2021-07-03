package com.example.crudwithvaadin.login;


import com.example.crudwithvaadin.MainView;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.concurrent.atomic.AtomicBoolean;

@Route("")
@RouteAlias(value = "login")
@PageTitle(value = "login Cakap.id")
public class LoginView extends Composite<LoginOverlay> {


    public LoginView(){

//        Same Way
//        VerticalLayout layout = getContent();
//        layout.add(new LoginForm());
//        layout.setSizeFull();
//        layout.setAlignItems(FlexComponent.Alignment.CENTER);
//        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        LoginOverlay loginOverlay = getContent();
        loginOverlay.setTitle("Cakap.id");
        loginOverlay.setDescription("Suarakan hatimu");
        loginOverlay.setOpened(true);

        //BackEnd
        loginOverlay.addLoginListener(event -> {
            if ("user".equals(event.getUsername()) && "123".equals(event.getPassword())) {
                UI.getCurrent().navigate("userView");
            } else if ("admin".equals(event.getUsername()) && "123".equals(event.getPassword())) {
                UI.getCurrent().navigate(MainView.class);
            } else {
                loginOverlay.setError(true);
//                Notification.show("Username atau Password salah");
                loginOverlay.setEnabled(true);
            }
        });




    }

}
