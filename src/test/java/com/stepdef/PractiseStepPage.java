package com.stepdef;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;

import com.page.PracticePage;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import util.ExcelReader;

public class PractiseStepPage {

	PracticePage pp;
	DriverFactory df;
	String excelpath = "src/test/java/com/testdata/InputData.xlsx";
	String radiobtn;
	String cntname;
	String dropdown;
	String coursename;
	String hideval;

	public PractiseStepPage() throws IOException {
		pp = new PracticePage(DriverFactory.getpage());
	}

	@Given("Open practice page site {string}")
	public void open_practice_page_site(String string) {
		DriverFactory.getpage().navigate("https://rahulshettyacademy.com/AutomationPractice/");
	}

	@When("Select testdata from excel {string} {int}")
	public void select_testdata_from_excel(String sheetname, Integer rownum)
			throws EncryptedDocumentException, IOException {
		ExcelReader reader = new ExcelReader();
		List<Map<String, String>> map = reader.getdata(excelpath, sheetname);
		radiobtn = map.get(rownum).get("radio button");
		cntname = map.get(rownum).get("country name");
		dropdown = map.get(rownum).get("drop down");
		coursename = map.get(rownum).get("course name");
		hideval = map.get(rownum).get("hide value");
		System.out.println(radiobtn);
		System.out.println(cntname);
		System.out.println(dropdown);
		System.out.println(coursename);
		System.out.println(hideval);
	}

	@And("Check for radio button")
	public void check_for_radio_button() {
		pp.clickRadiobtn(radiobtn);
	}

	@And("Select suggetion country name")
	public void select_suggetion_country_name() {
		pp.entercounrtyname(cntname);
	}

	@And("Select Dropdown example")
	public void select_dropdown_example() {
		pp.selectOption(dropdown);
	}

	@And("Select check box")
	public void select_check_box() {
		pp.selectCheckbx(dropdown);
	}

	@And("Check alert is handle")
	public void check_alert_is_handle() {
		pp.alertbx();
	}

	@And("Find course amount from table")
	public void find_course_amount_from_table() {

	}

	@And("Enter value in hide field")
	public void enter_value_in_hide_field() {
		pp.hideshowEx();
	}

	@And("Check new window is open or not")
	public void check_new_window_is_open_or_not() {
		pp.switchwindow();
	}

	@And("Check new tab is open or not")
	public void check_new_tab_is_open_or_not() {
		pp.switchtab();
	}

	@And("Mousehover on page")
	public void mousehover_on_page() {
		pp.mouseover();
	}

	@And("Switch to frame")
	public void switch_to_frame() {

	}

}
