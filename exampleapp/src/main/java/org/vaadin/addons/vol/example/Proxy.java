package org.vaadin.addons.vol.example;

public class Proxy extends org.eclipse.jetty.servlets.ProxyServlet.Transparent {
    public Proxy() {
        _DontProxyHeaders.add("Host".toLowerCase());
    }

}
