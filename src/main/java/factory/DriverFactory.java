package factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class DriverFactory {

	static Properties prop;
	public static Playwright playwright;
	Browser browser;
	BrowserContext browsercontext;
	Page page;

	public static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
	public static ThreadLocal<BrowserContext> tlBrowsercontext = new ThreadLocal<>();
	public static ThreadLocal<Page> tlPage = new ThreadLocal<>();
	public static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();

	public static Playwright getplaywright() {
		return tlPlaywright.get();
	}

	public static Browser getbrowser() {
		return tlBrowser.get();
	}

	public static BrowserContext getbrowsercontext() {
		return tlBrowsercontext.get();
	}

	public static Page getpage() {
		return tlPage.get();
	}
	

	public static Page initBrowser(String browsername,String scenario) throws IOException {
		System.out.println("Browser name is" + browsername);
		tlPlaywright.set(Playwright.create());

		switch (browsername.toLowerCase()) {
		case "chromium":
			tlBrowser.set(getplaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(true)));
			break;
		case "firefox":
			tlBrowser.set(getplaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "safari":
			tlBrowser.set(getplaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "chrome":
			tlBrowser
					.set(getplaywright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(true)));
			break;
		default:
			System.out.println("Please provide exact browser name....");
			break;
		}

		tlBrowsercontext.set(getbrowser().newContext(new Browser.NewContextOptions().
				setRecordVideoDir(Paths.get("reports/test-Report/video/"+scenario)).setRecordVideoSize(640, 480)));
		tlPage.set(getbrowsercontext().newPage());
		return getpage();
	}
	
	public void close_browser() {
		tlBrowsercontext.get().close();
		tlBrowser.get().close();
		
	}

	public Properties init_property() throws IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/config/config.properties");
		prop = new Properties();
		prop.load(fis);
		System.out.println("proprty data" + prop.getProperty("Browser"));
		return prop;
	}

	public static String takeScreenshot() {
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		byte[] buffer = getpage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		String base64Path = Base64.getEncoder().encodeToString(buffer);

		return base64Path;
	}

}
