package equochromium;

import static com.teamdev.jxbrowser.engine.RenderingMode.HARDWARE_ACCELERATED;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.view.swt.BrowserView;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SampleSWT {
	private static final String URL = "https://docs.equo.dev/main/getting-started/introduction.html";
	private static final int WINDOW_HEIGHT = 500;
	private static final int WINDOW_WIDTH = 900;
	private static final String SAMPLE_NAME = "SampleSWT JX";

	public static void main(String[] args) {
		Engine engine = Engine.newInstance(HARDWARE_ACCELERATED);
		Browser browser = engine.newBrowser();

		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setText(SAMPLE_NAME);
		shell.setLayout(new GridLayout());

		BrowserView view = BrowserView.newInstance(shell, browser);
		view.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		browser.navigation().loadUrl(URL);

		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
