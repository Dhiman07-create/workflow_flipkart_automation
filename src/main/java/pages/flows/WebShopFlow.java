package pages.flows;

import pages.web.*;

import static org.assertj.core.api.Assertions.assertThat;

public class WebShopFlow implements ShopFlow {
    @Override
    public void searchAddToCartPlaceOrderCOD(String query) {
        HomePageWeb home = new HomePageWeb().open();
        var results = home.search(query);
        assertThat(results.hasResults()).as("Search results should appear").isTrue();
        var pdp = results.openFirstProduct();
        var cart = pdp.addToCart();
        var checkout = cart.placeOrder();
        checkout.ensureLoggedInOrGuest()
                .chooseDefaultAddress()
                .chooseCODAndStopIfProd();
    }
}
