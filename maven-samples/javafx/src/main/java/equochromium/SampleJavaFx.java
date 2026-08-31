package equochromium;

import com.equo.chromium.ChromiumBrowser;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX browser sample — embeds an Equo Chromium browser (offscreen-rendered) as a real
 * {@code javafx.scene.Node} inside a JavaFX scene graph, alongside native JavaFX controls.
 *
 * <pre>mvn verify</pre>
 * launches it (override the page with {@code -Durl=...}). The browser lives in the CENTER of a
 * {@link BorderPane}, under a JavaFX toolbar — proving it respects JavaFX layout/clipping.
 */
public class SampleJavaFx extends Application {

	@Override
	public void start(Stage stage) {
		String url = System.getProperty("url", "https://docs.equo.dev/main/getting-started/introduction.html");

		// The browser is embedded as a JavaFX Node inside this host pane.
		StackPane browserHost = new StackPane();
		ChromiumBrowser browser = ChromiumBrowser.javafx(browserHost, url);

		// A real JavaFX toolbar around the browser, to show scene-graph integration.
		Label brand = new Label("Equo Chromium — JavaFX");
		TextField address = new TextField(url);
		HBox.setHgrow(address, Priority.ALWAYS);
		address.setOnAction(e -> browser.setUrl(address.getText()));

		Button devBtn = new Button("DevTools");
		devBtn.setOnAction(e -> browser.showDevTools());

		HBox toolbar = new HBox(10, brand, address, devBtn);
		toolbar.setAlignment(Pos.CENTER_LEFT);
		toolbar.setPadding(new Insets(8));

		BorderPane root = new BorderPane();
		root.setTop(toolbar);
		root.setCenter(browserHost);

		stage.setScene(new Scene(root, 1100, 760));
		stage.setTitle("Equo Chromium — JavaFX sample");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
