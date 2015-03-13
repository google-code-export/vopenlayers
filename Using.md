# Using vopenlayers #

  1. add maven dependency

> 2. update (or create and update) your widgetset to inherit volwidgetset: mvn vaadin:update-widgetset

> 3. Check your widgetset. Ensure it has only `<inherits name="org.vaadin.vol.VolWidgetsetWithHostedScript" />` or `<inherits name="org.vaadin.vol.VolWidgetset" />` depending how you wish to serve openlayers scripts.

Optional steps:
  * include openlayers scipts if you don't use the "hosted script" version of the widgetset. Why would you do this? This should be considered if you are using SSL/TLS to avoid "mixed content warnings". The hosted script (openlayers.org) is also sometimes under bit heavy load, so your application may perform better. Also one can build rather lot optimized version of openlayers script that only contains features that are actually used.
  * include other scripts like open street maps or google maps api if needed

vopenlayers is basically a Vaadin addon, containing both client and server side classes. The widgetset (GWT module) also contains some classes that may be used as is in pure GWT applications. The jar file can be downloaded from the downloads page.

check general instructions for Vaadin addons from the Directory:
http://vaadin.com/directory

In several places the documentation is non-existent. API should though be descriptive especially if openlayers itself is somewhat familiar for the developer. OpenLayers docs may thus be for a great help.

# Example project #

The demo application is packaged with maven and can be used as a reference when setting up the environment.

The source code may be checked out or browsed from the Source part.