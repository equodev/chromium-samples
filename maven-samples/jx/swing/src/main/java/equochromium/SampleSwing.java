package equochromium;

import static com.teamdev.jxbrowser.engine.RenderingMode.HARDWARE_ACCELERATED;
import static javax.swing.SwingUtilities.invokeLater;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.equo.chromium.ChromiumBrowser;
import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.view.swing.BrowserView;

public class SampleSwing {
	private static final long serialVersionUID = 1L;
	private static final String URL = "https://docs.equo.dev/main/getting-started/introduction.html";
	private static final int WINDOW_HEIGHT = 500;
	private static final int WINDOW_WIDTH = 900;
	private static final String SAMPLE_NAME = "SampleSwing JX";

	public static void main(String[] args) {
		Engine engine = Engine.newInstance(HARDWARE_ACCELERATED);
		Browser browser = engine.newBrowser();

		invokeLater(() -> {
			BrowserView view = BrowserView.newInstance(browser);
			JFrame frame = new JFrame(SAMPLE_NAME);
			frame.setDefaultCloseOperation(
				System.getProperty("os.name").toLowerCase().contains("win")
				? WindowConstants.EXIT_ON_CLOSE
				: WindowConstants.DISPOSE_ON_CLOSE
			);
			frame.add(view, BorderLayout.CENTER);
			frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
			frame.setVisible(true);
		});

		browser.navigation().loadUrl(URL);
	}
}
