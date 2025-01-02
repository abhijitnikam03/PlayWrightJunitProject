package apphook;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import com.microsoft.playwright.Page;
import com.page.PracticePage;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import util.ConfigReader;

public class AppHooks {

	DriverFactory df;
	protected Page page;
	protected Properties prop;
	PracticePage practPage;
	private ConfigReader configReader;
	
	@Before(order=0)
	public void setup() throws IOException {
		configReader = new ConfigReader();
		prop = configReader.init_prop();
		String browsername = prop.getProperty("browser").trim();
		System.out.println("Property name is" + prop);
		File file = new File("recordings/");
		df= new DriverFactory();
		df.initBrowser(browsername);	
		practPage = new PracticePage(page);

	}

	@After(order=0)
	public void Teardown(Scenario scenario) {
		page=DriverFactory.getpage();
		page.close();

	}
}
