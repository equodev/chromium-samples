package equochromium;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.equo.chromium.ChromiumBrowser;
import com.equo.chromium.swt.Browser;

public class SampleSWT {
	private static final String URL = "https://docs.equo.dev/main/getting-started/introduction.html";

	public static void main(String[] args) throws ClassNotFoundException {
		if (args.length > 0 && "true".equals(args[0])) {
			System.setProperty("chromium.debug", "true");
		}

		ChromiumBrowser.earlyInit();
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(1, false));

		if (args.length > 0 && "true".equals(args[0])) {
			shell.setText("Windowless");
			ChromiumBrowser browser = ChromiumBrowser.windowless(URL);
			Button button = new Button(shell, SWT.PUSH);
			button.setText("Print page paragraph");
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					browser.executeJavacript(
							"console.log(document.getElementsByClassName(\"paragraph\")[0].children[0].innerHTML)");
				}
			});
			shell.setSize(300, 100);
		} else {
			shell.setText(SampleSWT.class.getSimpleName());
			Browser browser = new Browser(shell, SWT.NONE);
			browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			browser.setUrl(URL);
		}
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
}
