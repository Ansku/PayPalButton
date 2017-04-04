/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.vaadin.addon.paypal_button.demo;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addon.paypal_button.PayPalButton;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
@Title("PaypalButton Add-on Demo")
@SuppressWarnings("serial")
@Widgetset("com.vaadin.DefaultWidgetSet")
public class DemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    private PayPalConfig paypal = new PayPalConfig();
    private TextField amountField;
    private PayPalButton paypalButton;

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        amountField = new TextField("Price (EUR)", e -> updatePayment());

        paypalButton = new PayPalButton(paypal.getEnvironment()) {
            @Override
            protected void succeeded() {
                System.out.println("PAYMENT SUCCEEDED");
            };

            @Override
            protected void cancelled() {
                System.out.println("PAYMENT CANCELLED");
            };
        };
        paypalButton.setClient(paypal.getRestAPIClientIdSandbox(),
                paypal.getRestAPIClientIdProduction());

        amountField.setValue("250");

        layout.addComponents(amountField, paypalButton);
    }

    private void updatePayment() {
        String price = amountField.getValue();
        if (price != null && !price.isEmpty()) {
            paypalButton.setAmount(price);
            paypalButton.setCurrency("EUR");
        }
    }
}
