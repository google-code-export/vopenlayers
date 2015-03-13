# Building #

  * install subversion (google it)
  * install maven (google it)
  * check out the source code. See http://code.google.com/p/vopenlayers/source/checkout
  * go to the project directory using command line (cd vopenlayers-read-only)
  * run maven install target ( mvn install)
  * use the addon jar file from target directory or straight from your local maven repository

Quick command line how-to for developers:
  * check out the project: svn co http://vopenlayers.googlecode.com/svn/trunk/ voltest
  * get to the project directory: cd voltest/
  * compile test widgetset and java sources: mvn gwt:compile package
  * Run test server: mvn exec:java -Dexec.mainClass="org.vaadin.vol.VolTestServer" -Dexec.classpathScope="test"


## A simple start to dive into ... ##
```bash

# checkout for non members
svn checkout http://vopenlayers.googlecode.com/svn/trunk/ vopenlayers-read-only

# complile all and start test server
mvn clean compile test-compile gwt:compile exec:java

# after that you can connect with your browser on localhost:9998
```


Using IDE for doing the same thing should work just fine too

Note that Eclipse m2e plugin for some reason adds "excluded: " for test source directory. Remove that to make eclipse autocompile tests. Starting TestServer is then supa easy with "debug as-> java app"

# Help for developers starting new features/fixing bugs #

## Coding conventions ##

We use the same coding conventions as core Vaadin.

http://dev.vaadin.com/wiki/CodingConventions

## GWT compiling/debugging ##

gwt maven plugin can be used to compile the test app widgetset to target/testwebapp:

mvn gwt:compile


The test app widget set can also be compiled via command line or e.g. eclipse launch configuration.  Settings for using GWT directly:

main class: com.google.gwt.dev.Compiler

program arguments: -gen generated -draftCompile -war target/testwebapp/VAADIN/widgetsets org.vaadin.vol.demo.VolExampleAppWidgetset -style PRETTY

vm args:  -Xmx1024M -Xms512M

For debugger the main class is com.google.gwt.dev.Compiler and program arguments:
-noserver org.vaadin.vol.demo.VolExampleAppWidgetset http://localhost:9998

Also note that it becomes much faster to compile the code (for debugging) if you only compile the permutation for the browsers you are using. E.g. remove comment around this code snippet (

&lt;set-property name="user.agent" value="safari"/&gt;

)  in VolExampleAppWidgetset.gwt.xml file.

## development server ##

The projects test package also contains VolTestServer class which contains a very fast jetty launching for development purposes. It automatically serves the widget set from target/testwebapp.

## Hardest parts (GWT,JSNI, wrappers) ##

GWT runs its code in an iframe to avoid collisions with other javascripts. OpenLayers does not. OpenLayers developers also hasn't taken this into consideration and most difficult issues when doing wrappers are when parameters are passed from GWT world to OpenLayers world.

E.g. passing arrays may be a really pain in the ass. OpenLayers detects js arrays with "typeof", which does not work between iframes. See http://trac.osgeo.org/openlayers/ticket/2959 . In the project this we use workaround that re creates the array in the scope of that main window. helpers.js file contains function toOlArray that may be used in GWT JSNI funcitons like this:

var linearRing = new $wnd.OpenLayers.Geometry.LinearRing($wnd.toOlArray(points));

The code above also demonstrates the fact that we must call OpenLayers api with "$wnd" prefix. Another option is to create a shorcut at the beginning of JSNI method:

var OpenLayers = $wnd.OpenLayers;
// do stuff with things

Scope issues may also rise on other global variables which OpenLayers code rely on same parts. E.g. if you see missing "console" errors on older browsers, OL is then missing the "firebug lite" it creates into the main windows scope. Console issue should have a global hack since [r20](https://code.google.com/p/vopenlayers/source/detail?r=20), but in case it doesn't work creating a shorthand should work like this: var console = $wnd.console;.

## Vaadin client side integration ##

Vaadin currently relies on the assumption that the client side component are always widgets. As this is not easily accomplished with OpenLayers integration, there is a hidden fake tree of Widget in addition to actual OpenLayers map components. The widget hierarchy is there basically just to keep GWT and Vaadin happy. Widgets then point to corresponding JSNI objects that wrap actual OpenLayers objects. Using this approach is essential to use Vaadin's built in paint mechanism that provide a decent client-server communication and e.g. "subtree caching".