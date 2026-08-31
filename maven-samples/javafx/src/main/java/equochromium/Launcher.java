package equochromium;

import javafx.application.Application;

/**
 * Plain (non-{@code Application}) entry point. Launching a class that extends {@code Application}
 * directly from the classpath trips the JVM's "JavaFX runtime components are missing" check; going
 * through a non-Application launcher avoids it (no need for {@code --module-path}).
 */
public class Launcher {
	public static void main(String[] args) {
		Application.launch(SampleJavaFx.class, args);
	}
}
