window.org_vaadin_addon_paypal_button_PayPalButton = function() {

    // Create the component
    var component =
        new paypalbutton.PayPalButton(this.getElement());
    var connector = this;

    paypal.Button.render({
        
        env: connector.getState().environment,
        client: {
        	sandbox: connector.getState().clientSandbox,
        	production: connector.getState().clientProduction
        },

        payment: function(resolve, reject) {
        
            var env    = this.props.env;
            var client = this.props.client;

        	return paypal.rest.payment.create(env, client, {
        		transactions: [
        			{
        				amount: { total: connector.getState().amount, currency: connector.getState().currency }
        			}
        		]
        	});
        },
        commit: true, // Optional: show a 'Pay Now' button in the checkout flow

        onAuthorize: function(data, actions) {
        	return actions.payment.execute().then(function() {
        		connector.succeeded();
        	});
        },
        
        onCancel: function(data, actions) {
        	connector.cancelled();
        }
        
    }, '#paypal-button');

}