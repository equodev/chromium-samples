package SampleJavaFx

import com.equo.chromium.ChromiumBrowser
import javafx.application.Application
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.StackPane
import javafx.stage.Stage

/**
 * JavaFX browser sample — embeds an Equo Chromium browser (offscreen-rendered) as a real
 * javafx.scene.Node inside a JavaFX scene graph, next to native JavaFX controls.
 *
 * Run: ./gradlew :app:run  (override the page with -Durl=...)
 */
class SampleJavaFx : Application() {
    override fun start(stage: Stage) {
        val url = System.getProperty("url", "https://docs.equo.dev/main/getting-started/introduction.html")

        // The browser is embedded as a JavaFX Node inside this host pane.
        val browserHost = StackPane()
        val browser = ChromiumBrowser.javafx(browserHost, url)

        // A real JavaFX toolbar around the browser, to show scene-graph integration.
        val address = TextField(url)
        HBox.setHgrow(address, Priority.ALWAYS)
        address.setOnAction { browser.setUrl(address.text) }
        val toolbar = HBox(10.0, Label("Equo Chromium — JavaFX"), address)
        toolbar.alignment = Pos.CENTER_LEFT
        toolbar.padding = Insets(8.0)

        val root = BorderPane()
        root.top = toolbar
        root.center = browserHost

        stage.scene = Scene(root, 1100.0, 760.0)
        stage.title = "Equo Chromium — JavaFX sample"
        stage.show()
    }
}

// Non-Application entry point: compiled to class SampleJavaFx.SampleJavaFxKt so the JVM main
// class is NOT an Application subclass (avoids the "JavaFX runtime components are missing" error).
fun main() {
    Application.launch(SampleJavaFx::class.java)
}
