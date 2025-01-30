package apphook;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.junit.BeforeClass;

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

	static {
		System.out.println("Inside Before class");
        String folderPath = "./reports/";
        File folder = new File(folderPath);
        if (deleteDirectory(folder)) {
            System.out.println("Folder deleted successfully.");
        } else {
            System.out.println("Failed to delete the folder or it does not exist.");
        }
    }

	@Before(order = 0)
	public void setup(Scenario scenario) throws IOException {
		String tagName = scenario.getSourceTagNames().iterator().next();
		String sanitizedTagName = tagName.replaceAll("[^a-zA-Z0-9-_]", "_").substring(1);
		configReader = new ConfigReader();
		prop = configReader.init_prop();
		String browsername = prop.getProperty("browser").trim();
		System.out.println("Property name is" + prop);
		File file = new File("recordings/");
		df = new DriverFactory();
		System.out.println("Scenario name is" + sanitizedTagName);
		df.initBrowser(browsername, sanitizedTagName);
		practPage = new PracticePage(page);
	}

	@After(order = 0)
	public void Teardown(Scenario scenario) {
		df.close_browser();
		page = DriverFactory.getpage();
		page.close();

	}

	private static boolean deleteDirectory(File directory) {
		if (directory.isDirectory()) {
			File[] files = directory.listFiles();
			if (files != null) { // Ensure it is not null
				for (File file : files) {
					deleteDirectory(file); // Recursive call for each file/directory
				}
			} else {
				System.out.println("File not deleted");
			}
		}
		return directory.delete(); // Delete the directory or file
	}
}
