package com.wisebirds.sap.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class MySiteMeshFilter extends ConfigurableSiteMeshFilter  {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
    	builder.addDecoratorPath("/*", "/WEB-INF/view/sitemesh/decorator.jsp");
    }
}
