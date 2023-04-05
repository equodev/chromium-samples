package equochromium;

import com.equo.chromium.ChromiumBrowser;

public class SampleStandalone {
	public static void main(String[] args) {
		ChromiumBrowser.standalone("https://docs.equo.dev/main/getting-started/introduction.html");
		ChromiumBrowser.startBrowsers();
	}
}
