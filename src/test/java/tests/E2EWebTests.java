package tests;

import base.BaseTest;
import pages.flows.WebShopFlow;
import org.testng.annotations.Test;
import utils.ExcelUtils;

public class E2EWebTests extends BaseTest {

    @Test(groups = {"web","smoke"}, dataProvider = "searchData", dataProviderClass = ExcelUtils.class)
    public void web_e2e_buy_cod(String product, String brand, String sort) {
        new WebShopFlow().searchAddToCartPlaceOrderCOD(product);
        // You could add verification by reading any confirmation banner or stopping before confirm on prod
    }
}
