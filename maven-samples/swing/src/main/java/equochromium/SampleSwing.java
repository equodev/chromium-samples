package equochromium;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.equo.chromium.ChromiumBrowser;

public class SampleSwing extends JFrame {
	private static final long serialVersionUID = 1L;

	private SampleSwing() {
		setDefaultCloseOperation(
			System.getProperty("os.name").toLowerCase().contains("win") ? EXIT_ON_CLOSE : DISPOSE_ON_CLOSE
		);
		ChromiumBrowser browser = ChromiumBrowser.swing(getContentPane(), BorderLayout.CENTER,
				"https://docs.equo.dev/main/getting-started/introduction.html");
		setTitle("Sample Swing");
		setSize(800, 600);
		setVisible(true);
	}

	public static void main(String[] args) {
		new SampleSwing();
	}
}

