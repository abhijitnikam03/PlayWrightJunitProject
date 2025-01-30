package com.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class PracticePage {

	Page page;

	// ####################### Page constructor #######################

	public PracticePage(Page page) {
		this.page = page;
	}

	// ####################### Locator #######################

	private String label = "label";
	private String countrytxtbx = "Type to Select Countries";
	private String countryname = "#ui-id-3";
	private String selectoption = "#dropdown-class-example";
	private String chkbx = "#checkBoxOption1";
	private String hideshow = "//input[@name='show-hide']";
	private String hidebtn = "Hide";
	private String showbtn = "Show";
	private String search = "input[name='search']";
	private String searchicon = "div#search button";
	private String searchPageHeader = "div#content h1";
	private String chkbox = "#checkBoxOption1";
	private String newwindowrdbtn = "#checkBoxOption1";

	// ####################### Page Action #######################

	public void clickRadiobtn(String rbtn) {
		page.locator(label).filter(new Locator.FilterOptions().setHasText(rbtn)).getByRole(AriaRole.RADIO).check();
	}

	public void entercounrtyname(String cname) {
		page.getByPlaceholder(countrytxtbx).click();
		page.getByPlaceholder(countrytxtbx).fill(cname);
		page.locator(countryname).click();
	}

	public void selectOption(String option) {
		page.locator(selectoption).selectOption(option);
	}

	public void selectCheckbx(String cbx) {
		page.locator(chkbx).check();
	}

	public void enterName(String name) {
		page.getByPlaceholder("Enter Your Name").click();
		page.getByPlaceholder("Enter Your Name").fill("abhijit");
	}

	public void dialogbx() {
		page.onceDialog(dialog -> {
			System.out.println(String.format("Dialog message: %s", dialog.message()));
			dialog.dismiss();
		});
	}

	public void hideshowEx() {
		page.locator(hideshow).click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(hidebtn)).click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(showbtn)).click();
		if (page.isVisible(hideshow)) {
			page.locator(hideshow).fill("abhijit");
			System.out.println("inside loop");
		}
	}

	public void switchwindow() {
		Page page1 = page.waitForPopup(() -> {
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Open Window")).click();
		});
		page1.getByText("info@qaclickacademy.com").nth(1).click();
		page1.close();
	}

	public void switchtab() {
		Page page2 = page.waitForPopup(() -> {
			page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Open Tab")).click();
		});
		page2.getByText("info@qaclickacademy.com").nth(1).click();
		page2.close();
	}

	public void mouseover() {
		page.locator("#mousehover").hover();
		page.waitForTimeout(2000);
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Top")).click();
	}

	public void alertbx() {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Confirm")).click();
		page.onceDialog(dialog -> {
			System.out.println(String.format("Dialog message: %s", dialog.message()));
			dialog.dismiss();
		});
	}
}
