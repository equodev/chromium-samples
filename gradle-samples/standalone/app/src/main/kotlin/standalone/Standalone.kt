
package Standalone

import com.equo.chromium.ChromiumBrowser

class Standalone {}

fun main() {
	ChromiumBrowser.standalone("https://docs.equo.dev/main/getting-started/introduction.html");
	ChromiumBrowser.startBrowsers();
}
