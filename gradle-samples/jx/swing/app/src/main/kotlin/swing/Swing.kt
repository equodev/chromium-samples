
package Swing

import com.teamdev.jxbrowser.engine.RenderingMode.HARDWARE_ACCELERATED
import javax.swing.SwingUtilities.invokeLater

import java.awt.BorderLayout

import javax.swing.JFrame
import javax.swing.WindowConstants

import com.equo.chromium.ChromiumBrowser
import com.teamdev.jxbrowser.browser.Browser
import com.teamdev.jxbrowser.engine.Engine
import com.teamdev.jxbrowser.view.swing.BrowserView

private const val serialVersionUID = 1L
private const val URL = "https://docs.equo.dev/main/getting-started/introduction.html"
private const val WINDOW_HEIGHT = 500
private const val WINDOW_WIDTH = 900
private const val SAMPLE_NAME = "SampleSwing JX"
class SampleSwing {}

fun main() {
	val engine = Engine.newInstance(HARDWARE_ACCELERATED)
	val browser = engine.newBrowser()

	invokeLater {
		val view = BrowserView.newInstance(browser)
		val frame = JFrame(SAMPLE_NAME)
		frame.setDefaultCloseOperation(
			if (System.getProperty("os.name").toLowerCase().contains("win")) WindowConstants.DISPOSE_ON_CLOSE 
			else WindowConstants.EXIT_ON_CLOSE)
		frame.add(view, BorderLayout.CENTER)
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT)
		frame.setVisible(true)
	}

	browser.navigation().loadUrl(URL)
}
