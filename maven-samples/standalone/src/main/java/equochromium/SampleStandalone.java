package equochromium;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.equo.chromium.ChromiumBrowser;
import com.equo.chromium.ChromiumBrowserStandalone;

/**
 * Standalone browser sample.
 *
 * <p>Default mode opens the Equo documentation page:
 * <pre>mvn verify</pre>
 *
 * <p>Pass {@code -Dcsd} to enable Client-Side Decorations mode, where the
 * native OS window decorations are replaced by HTML/CSS/JS elements rendered
 * inside the browser.  The {@code window.equo} JavaScript API is injected
 * automatically and exposes:
 *
 * <ul>
 *   <li>{@code window.equo.beginMove(screenX, screenY)} – start a native window drag</li>
 *   <li>{@code window.equo.beginResize(screenX, screenY, edge)} – start a native resize</li>
 *   <li>{@code window.equo.maximize()} – maximize the window</li>
 *   <li>{@code window.equo.restore()} – restore from maximized state</li>
 *   <li>{@code window.equo.minimize()} – minimize the window</li>
 *   <li>{@code window.equo.close()} – close the window</li>
 * </ul>
 *
 * <pre>mvn verify -Dcsd</pre>
 */
public class SampleStandalone {

    private static final String HTML =
        "<!doctype html><html><head><meta charset='utf-8'><style>" +
        "  *, *::before, *::after { box-sizing: border-box; margin: 0; padding: 0; }" +
        "  body { height: 100vh; overflow: hidden; display: flex; flex-direction: column;" +
        "    font-family: system-ui, sans-serif; background: #0a0a0f; color: #fff; }" +

        /* ── Title bar ── */
        "  #titlebar { height: 38px; flex-shrink: 0; display: flex; align-items: center;" +
        "    justify-content: space-between; padding: 0 8px 0 16px;" +
        "    background: rgba(255,255,255,0.04); border-bottom: 1px solid rgba(255,255,255,0.08);" +
        "    user-select: none; -webkit-user-select: none; }" +
        "  #titlebar .title { font-size: 12px; opacity: .5; }" +
        "  #wc { display: flex; gap: 6px; }" +
        "  .wc { width: 26px; height: 20px; border-radius: 5px; border: none; cursor: pointer;" +
        "    background: rgba(255,255,255,0.07); color: #fff; font-size: 11px; }" +
        "  .wc:hover { background: rgba(255,255,255,0.16); }" +
        "  .wc.close:hover { background: #c0392b; }" +

        /* ── Animated gradient background ── */
        "  @keyframes drift { 0%,100%{background-position:0% 50%} 50%{background-position:100% 50%} }" +
        "  #content { flex: 1; display: flex; flex-direction: column; align-items: center;" +
        "    justify-content: center; gap: 20px;" +
        "    background: linear-gradient(135deg,#0f0c29,#302b63,#24243e,#1a1a2e,#16213e,#0f3460);" +
        "    background-size: 400% 400%; animation: drift 10s ease infinite; }" +

        /* ── Hero text ── */
        "  h1 { font-size: 42px; font-weight: 800; letter-spacing: -1px;" +
        "    background: linear-gradient(90deg,#a78bfa,#38bdf8,#34d399);" +
        "    -webkit-background-clip: text; -webkit-text-fill-color: transparent; }" +
        "  p { font-size: 14px; opacity: .5; letter-spacing: .05em; text-transform: uppercase; }" +

        /* ── Resize handles ── */
        "  .rs { position: fixed; z-index: 9999; }" +
        "  .rs.n  { top:0; left:6px; right:6px; height:6px; cursor:n-resize; }" +
        "  .rs.s  { bottom:0; left:6px; right:6px; height:6px; cursor:s-resize; }" +
        "  .rs.e  { top:6px; bottom:6px; right:0; width:6px; cursor:e-resize; }" +
        "  .rs.w  { top:6px; bottom:6px; left:0; width:6px; cursor:w-resize; }" +
        "  .rs.nw { top:0; left:0; width:6px; height:6px; cursor:nw-resize; }" +
        "  .rs.ne { top:0; right:0; width:6px; height:6px; cursor:ne-resize; }" +
        "  .rs.sw { bottom:0; left:0; width:6px; height:6px; cursor:sw-resize; }" +
        "  .rs.se { bottom:0; right:0; width:6px; height:6px; cursor:se-resize; }" +
        "</style></head><body>" +

        "<div id='titlebar'>" +
        "  <span class='title'>Equo Chromium</span>" +
        "  <div id='wc'>" +
        "    <button class='wc' id='btn-min'>&#x2014;</button>" +
        "    <button class='wc' id='btn-max'>&#x25A1;</button>" +
        "    <button class='wc close' id='btn-close'>&#x2715;</button>" +
        "  </div>" +
        "</div>" +

        "<div class='rs n' data-edge='TOP'></div><div class='rs s' data-edge='BOTTOM'></div>" +
        "<div class='rs e' data-edge='RIGHT'></div><div class='rs w' data-edge='LEFT'></div>" +
        "<div class='rs nw' data-edge='TOP_LEFT'></div><div class='rs ne' data-edge='TOP_RIGHT'></div>" +
        "<div class='rs sw' data-edge='BOTTOM_LEFT'></div><div class='rs se' data-edge='BOTTOM_RIGHT'></div>" +

        "<div id='content'>" +
        "  <h1>Equo Chromium</h1>" +
        "  <p>Client-Side Decorations</p>" +
        "</div>" +

        "<script>" +
        "  function eq(fn) { if(window.equo) fn(); else { var t=setInterval(function(){if(window.equo){clearInterval(t);fn();}},50); } }" +
        "  var max=false;" +
        "  function toggleMax(){ max=!max; eq(function(){ max?window.equo.maximize():window.equo.restore(); }); }" +
        "  document.getElementById('titlebar').addEventListener('mousedown',function(e){" +
        "    if(e.button!==0||e.target.closest('#wc'))return;" +
        "    eq(function(){window.equo.beginMove(e.screenX,e.screenY);});" +
        "  });" +
        "  document.getElementById('titlebar').addEventListener('dblclick',function(e){if(!e.target.closest('#wc'))toggleMax();});" +
        "  document.getElementById('btn-min').addEventListener('click',function(){eq(function(){window.equo.minimize();});});" +
        "  document.getElementById('btn-max').addEventListener('click',function(){toggleMax();});" +
        "  document.getElementById('btn-close').addEventListener('click',function(){eq(function(){window.equo.close();});});" +
        "  document.querySelectorAll('.rs').forEach(function(el){" +
        "    el.addEventListener('mousedown',function(e){" +
        "      if(e.button!==0)return; e.stopPropagation();" +
        "      eq(function(){window.equo.beginResize(e.screenX,e.screenY,el.dataset.edge);});" +
        "    });" +
        "  });" +
        "</script></body></html>";

    public static void main(String[] args) {
        if (Boolean.getBoolean("csd")) {
            String url = "data:text/html;charset=utf-8,"
                    + URLEncoder.encode(HTML, StandardCharsets.UTF_8).replace("+", "%20");
            ChromiumBrowserStandalone browser = ChromiumBrowser.standalone(
                    url,
                    "Equo Chromium Client-Side Decorations Sample",
                    200, 200, 600, 420,
                    true	// Enable Client-Side Decorations
            );
        } else {
            ChromiumBrowser.standalone("https://docs.equo.dev/main/getting-started/introduction.html", 200, 200, 600, 420);
        }
        ChromiumBrowser.startBrowsers();
    }
}
