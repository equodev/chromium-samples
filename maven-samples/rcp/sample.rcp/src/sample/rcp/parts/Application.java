package sample.rcp.parts;

import org.eclipse.e4.ui.internal.workbench.swt.E4Application;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class Application extends E4Application implements IApplication {

	@Override
	public Object start(IApplicationContext applicationContext) throws Exception {
		com.equo.chromium.ChromiumBrowser.earlyInit();
		return super.start(applicationContext);
	}
}