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

package org.vaadin.addon.paypal_button;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;

import elemental.json.JsonArray;

// This is the server-side UI component that provides public Java API
// for PayPalButton
@JavaScript({ "paypalbutton.js", "paypalbutton-connector.js",
        "https://www.paypalobjects.com/api/checkout.js" })
public class PayPalButton extends AbstractJavaScriptComponent {

    public PayPalButton() {
        addFunction("succeeded", new JavaScriptFunction() {
            @Override
            public void call(JsonArray arguments) {
                succeeded();
            }
        });
        addFunction("cancelled", new JavaScriptFunction() {
            @Override
            public void call(JsonArray arguments) {
                cancelled();
            }
        });
    }

    public PayPalButton(String env) {
        this();
        setEnvironment(env);
    }

    @Override
    protected PaypalButtonState getState() {
        return (PaypalButtonState) super.getState();
    }

    /**
     * NOTE: must be set before button is rendered.
     *
     * @param environment
     */
    public void setEnvironment(String environment) {
        if (environment == null) {
            throw new IllegalArgumentException(
                    "PayPal Environment cannot be null");
        }
        getState().environment = environment;
    }

    /**
     * NOTE: must be set before button is rendered.
     *
     * @param sandbox
     * @param production
     */
    public void setClient(String sandbox, String production) {
        if (sandbox == null || production == null) {
            throw new IllegalArgumentException("PayPal Client cannot be null");
        }
        getState().clientSandbox = sandbox;
        getState().clientProduction = production;
    }

    public void setAmount(String amount) {
        if (amount == null) {
            throw new IllegalArgumentException("PayPal Amount cannot be null");
        }
        getState().amount = amount;
    }

    public void setCurrency(String currency) {
        if (currency == null) {
            throw new IllegalArgumentException(
                    "PayPal Currency cannot be null");
        }
        getState().currency = currency;
    }

    protected void succeeded() {
        // override if needed
    }

    protected void cancelled() {
        // override if needed
    }
}
