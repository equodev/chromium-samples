
package SampleSWT

import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Shell
import com.equo.chromium.ChromiumBrowser
import com.equo.chromium.swt.Browser

private const val URL = "https://docs.equo.dev/main/getting-started/introduction.html"
class SampleSWT {}

@kotlin.Throws(ClassNotFoundException::class)
fun main(args: Array<String?>) {
    if (args.size > 0 && "windowless".equals(args[0])) {
        System.setProperty("chromium.debug", "true")
    }
    ChromiumBrowser.earlyInit()
    val display: Display = Display.getDefault()
    val shell = Shell(display)
    shell.setLayout(GridLayout(1, false))
    if (args.size > 0 && "windowless".equals(args[0])) {
        shell.setText("Windowless")
        val browser: ChromiumBrowser = ChromiumBrowser.windowless(URL)
        val button = Button(shell, SWT.PUSH)
        button.setText("Print page paragraph")
        button.addSelectionListener(object : SelectionAdapter() {
            override fun widgetSelected(e: SelectionEvent?) {
                browser.executeJavacript(
                    "console.log(document.getElementsByClassName(\"paragraph\")[0].children[0].innerHTML)"
                )
            }
        })
        shell.setSize(300, 100)
    } else {
        shell.setText("SampleSWT")
        val browser = Browser(shell, SWT.NONE)
        browser.setLayoutData(GridData(SWT.FILL, SWT.FILL, true, true))
        browser.setUrl(URL)
    }
    shell.open()
    while (!shell.isDisposed()) {
        if (!display.readAndDispatch()) display.sleep()
    }
}
