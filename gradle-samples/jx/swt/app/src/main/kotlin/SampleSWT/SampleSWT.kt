
package SampleSWT

import com.teamdev.jxbrowser.engine.RenderingMode.HARDWARE_ACCELERATED

import com.teamdev.jxbrowser.browser.Browser
import com.teamdev.jxbrowser.engine.Engine
import com.teamdev.jxbrowser.view.swt.BrowserView
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Shell

private const val URL = "https://docs.equo.dev/main/getting-started/introduction.html"
private const val WINDOW_HEIGHT = 500
private const val WINDOW_WIDTH = 900
private const val SAMPLE_NAME = "SampleSWT JX"
class SampleSWT {}

@kotlin.Throws(ClassNotFoundException::class)
fun main(args: Array<String?>) {
    val engine = Engine.newInstance(HARDWARE_ACCELERATED)
    val browser = engine.newBrowser()

    val display = Display.getDefault()
    val shell = Shell(display)
    shell.setText(SAMPLE_NAME)
    shell.setLayout(GridLayout())

    val view = BrowserView.newInstance(shell, browser)
    view.setSize(WINDOW_WIDTH, WINDOW_HEIGHT)
    browser.navigation().loadUrl(URL)

    shell.pack()
    shell.open()

    while (!shell.isDisposed()) {
        if (!display.readAndDispatch()) {
            display.sleep()
        }
    }
}
