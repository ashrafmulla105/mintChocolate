package com.neo.stepdefs;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.neo.actions.BrowserActions;
import com.neo.base.CommonPage;
import com.neo.drivers.DriverManager;
import com.neo.utilities.ExtentReportUtility;

import cucumber.api.java.en.Given;

public class AutomationPracticeSteps extends CommonPage {

	@FindBy(xpath = "(//a[@title='Dresses'])[2]/..")
	private WebElement dressesMenuBtn;

	@FindBy(id = "list")
	private WebElement listViewBtn;
//
	@FindBy(tagName = "iframe")
	private WebElement fancyFrame;

	@FindBy(xpath = "(//button[@type='button'])[2]")
	private WebElement fbShareBtn;

	@FindBy(xpath = "//h1[@itemprop='name']")
	private WebElement productName;

	@FindBy(name = "qty")
	private WebElement itemQuantityTxt;

	@FindBy(id = "wishlist_button")
	private WebElement wishListBtn;

	@FindBy(xpath = "//p[contains(text(),'Added to your wishlist')]")
	private WebElement addedToWishlistTxt;

	@FindBy(xpath = "//p[text()='Added to your wishlist.']/../../following-sibling::a")
	private WebElement wishlistTxtCloseBtn;

	@FindBy(name = "Submit")
	private WebElement addToCartBtn;

	@FindBy(xpath = "//*[contains(text(),'Total products')]/following-sibling::span")
	private WebElement totalProductPrice;

	@FindBy(xpath = "//*[contains(text(),'Total shipping')]/following-sibling::span")
	private WebElement totalShippingPrice;

	@FindBy(xpath = "(//*[contains(text(),'Total')]/following-sibling::span)[4]")
	private WebElement totalPrice;

	@FindBy(xpath = "//*[contains(text(),'Proceed to checkout')]")
	private WebElement proceedToCheckoutBtn;

	@FindBy(xpath = "(//a[@title='Proceed to checkout'])[2]")
	private WebElement shoppingCatProceedBtn;

	@FindBy(id = "cart_title")
	private WebElement shoppingCartTitle;

	@FindBy(xpath = "(//p[@class='product-name']/a)[2]")
	private WebElement shoppingCartProdName;

	@FindBy(id = "total_price_without_tax")
	private WebElement shoppingCartProdPrice;

	@FindBy(xpath = "//*[contains(text(),'Delivery address')]")
	private WebElement deliveryAddressHeader;

	@FindBy(className = "address_name")
	private WebElement deliveryAddressName;

	@FindBy(id = "cgv")
	private WebElement tncCheckBox;

	@FindBy(className = "cheque")
	private WebElement payByChequeLnk;

	@FindBy(xpath = "//span[text()='I confirm my order']")
	private WebElement confirmOrderBtn;

	@FindBy(className = "page-heading")
	private WebElement orderConfirmPageHeading;

	@FindBy(xpath = "//p[@class='alert alert-success']")
	private WebElement orderCompleteMsg;

	@FindBy(xpath = "//*[@class='price']/strong")
	private WebElement orderConfirmPrice;

	String productPrice = null;
	String selectedProductName = null;
	boolean isShoppingCartPresent = false;

	/**
	 * @Description Initialize the WebElements
	 */
	public void decorator() {
		PageFactory.initElements(DriverManager.get().getDriver(), this);
	}

	@Given("the user clicks {string}")
	public void the_user_clicks(String dressType) {
		try {
			decorator();
			actions.mouseOverElement(dressesMenuBtn, "Dresses menu button");
			WebElement summerDresses = dressesMenuBtn.findElement(By.xpath("//a[@title='" + dressType + "']"));
			actions.clickElementJS(summerDresses, "Summer Dresses");
		} catch (Exception e) {
			ExtentReportUtility.get().setReport(BrowserActions.FAIL,
					"Exception in class  : " + this.getClass() + "\n " + e.toString());
		}

	}

	@Given("the user changes view mode to {string}")
	public void the_user_changes_view_mode_to(String viewMode) {
		try {
			decorator();
			By byView = By.xpath("//li[@id='" + viewMode + "']");
			actions.clickDynamic(byView, viewMode);
		} catch (Exception e) {
			ExtentReportUtility.get().setReport(BrowserActions.FAIL,
					"Exception in class  : " + this.getClass() + "\n " + e.toString());
		}
	}

	@Given("the user clicks product quick view button")
	public void the_user_clicks_product_quick_view_button() {
		try {
			decorator();
			boolean isColorPresent = false;
			WebElement finalProduct = null;
			List<WebElement> productList = actions.getElementList(By.xpath("//link[contains(@href,'InStock')]"));

			for (WebElement prodList : productList) {
				try {
					isColorPresent = prodList.findElement(By.xpath(
							"//a[@class='color_pick' and contains(@style,'" + getTestJSONValue("prodColor") + "')]"))
							.isDisplayed();
				} catch (Exception e) {
					System.out.println("Color not found");
				}
				if (isColorPresent) {
					finalProduct = prodList;
					break;
				}
			}
			WebElement productColum = finalProduct.findElement(By.xpath("//parent::div"));

			actions.scrollDown();
			actions.mouseOverElement(productColum, "Product Image");

			WebElement quickView = productColum.findElement(By.xpath("//span[text()='Quick view']"));
			actions.clickElementJS(quickView, "Quick View");
		} catch (Exception e) {
			ExtentReportUtility.get().setReport(BrowserActions.FAIL,
					"Exception in class  : " + this.getClass() + "\n " + e.toString());
		}
	}

	@Given("click on facebook share button")
	public void click_on_facebook_share_button() {
		try {
			decorator();
			actions.switchToFrame(fancyFrame);
			actions.clickElementJS(fbShareBtn, "Facebook Share button");
			actions.switchToNextWindow();
			assertTrue(actions.getURL().contains(getTestJSONValue("NavigatedURL")));
			ExtentReportUtility.get().setReport(BrowserActions.PASS,
					"Facebook URL is verified : " + getTestJSONValue("NavigatedURL"));
			actions.closeWindow();
			actions.switchToNextWindow();
		} catch (Exception e) {
			ExtentReportUtility.get().setReport(BrowserActions.FAIL,
					"Exception in class  : " + this.getClass() + "\n " + e.toString());
		}
	}

	@Given("the user add the product to cart")
	public void the_user_add_the_product_to_cart() {
		try {
			decorator();
			actions.switchToFrame(fancyFrame);
			selectedProductName = productName.getText();
			actions.sendKeys(itemQuantityTxt, "Product Quantity", getTestJSONValue("itemQuantity"));
			actions.click(wishListBtn, "WishList Button");
			actions.verifyElementPresent(addedToWishlistTxt, "Added to your wishlist success message");
			actions.click(wishlistTxtCloseBtn, "WishList text close icon");
			actions.click(addToCartBtn, "Add to cart button");
		} catch (Exception e) {
			ExtentReportUtility.get().setReport(BrowserActions.FAIL,
					"Exception in class  : " + this.getClass() + "\n " + e.toString());
		}

	}

	@Given("the user verifies the price details")
	public void the_user_verifies_the_price_details() {
		try {
			decorator();
			actions.switchToParentFrame();
			actions.verifyElementPresent(totalProductPrice, "Total Product Price  : " + totalProductPrice.getText());
			actions.verifyElementPresent(totalShippingPrice, "Total Shipping Price: " + totalShippingPrice.getText());
			actions.verifyElementPresent(totalPrice, "Total Price : " + totalPrice.getText());
			productPrice = totalPrice.getText();
		} catch (Exception e) {
			ExtentReportUtility.get().setReport(BrowserActions.FAIL,
					"Exception in class  : " + this.getClass() + "\n " + e.toString());
		}

	}

	@Given("the user clicks on proceed to checkout button")
	public void the_user_clicks_on_proceed_to_checkout_button() {
		try {
			decorator();
			List<WebElement> checkoutBtn = actions
					.getElementList(By.xpath("//*[contains(text(),'Proceed to checkout')]"));
			for (WebElement button : checkoutBtn) {
				if (actions.verifyVisibilityOfElement(button)) {
					button.click();
				}
			}
		} catch (Exception e) {
			ExtentReportUtility.get().setReport(BrowserActions.FAIL,
					"Exception in class  : " + this.getClass() + "\n " + e.toString());
		}
	}

	@Given("The user verifies the order details")
	public void the_user_verifies_the_order_details() {
		try {
			decorator();
			actions.isElementPresent(shoppingCartTitle);
			System.out.println(shoppingCartTitle.getText());
			assertTrue(shoppingCartTitle.getText().contains("SHOPPING-CART SUMMARY"));
			ExtentReportUtility.get().setReport(BrowserActions.PASS,
					shoppingCartTitle.getText() + " Header is verified");
			assertEquals(selectedProductName, shoppingCartProdName.getText());
			ExtentReportUtility.get().setReport(BrowserActions.PASS,
					shoppingCartProdName.getText() + " product name is verified");

			actions.scrollDown();
			assertEquals(productPrice, shoppingCartProdPrice.getText());
			ExtentReportUtility.get().setReport(BrowserActions.PASS,
					shoppingCartProdPrice.getText() + " product price is verified");

			actions.verifyElementPresent(deliveryAddressHeader, "Delivery Address Header");
			assertEquals(deliveryAddressName.getText(), getTestJSONValue("addressName"));
			ExtentReportUtility.get().setReport(BrowserActions.PASS,
					deliveryAddressName.getText() + " delivery address name is verified");

			isShoppingCartPresent = true;
		} catch (Exception e) {
			ExtentReportUtility.get().setReport(BrowserActions.FAIL,
					"Exception in class  : " + this.getClass() + "\n " + e.toString());
		}

	}

	@Given("the user agree the terms and conditions and checkout")
	public void the_user_agree_the_terms_and_conditions_and_checkout() {
		try {
			decorator();
			actions.scrollToElement(tncCheckBox);
			actions.clickElementJS(tncCheckBox, "Terms and conditions checkbox");
			the_user_clicks_on_proceed_to_checkout_button();
			actions.click(payByChequeLnk, "Pay By Cheque Link");
			actions.click(confirmOrderBtn, "I Confirm My Order button");
		} catch (Exception e) {
			ExtentReportUtility.get().setReport(BrowserActions.FAIL,
					"Exception in class  : " + this.getClass() + "\n " + e.toString());
		}

	}

	@Given("the user verifies the amount on order confirmation page")
	public void the_user_verifies_the_amount_on_order_confirmation_page() {
		try {
			decorator();
			actions.scrollDown();
			assertEquals("ORDER CONFIRMATION", orderConfirmPageHeading.getText());
			ExtentReportUtility.get().setReport(BrowserActions.PASS,
					orderConfirmPageHeading.getText() + " header is verified");
			assertEquals("Your order on My Store is complete.", orderCompleteMsg.getText());
			ExtentReportUtility.get().setReport(BrowserActions.PASS,
					orderCompleteMsg.getText() + " success message is verified");
			assertEquals(productPrice, orderConfirmPrice.getText());
			ExtentReportUtility.get().setReport(BrowserActions.PASS,
					orderConfirmPrice.getText() + " order confirm price is verified");
		} catch (Exception e) {
			ExtentReportUtility.get().setReport(BrowserActions.FAIL,
					"Exception in class  : " + this.getClass() + "\n " + e.toString());
		}

	}

}
