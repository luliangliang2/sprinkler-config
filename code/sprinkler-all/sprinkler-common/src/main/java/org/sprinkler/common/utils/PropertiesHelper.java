package org.sprinkler.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;


public class PropertiesHelper {

	public static Properties getProperties(String fileFullPath) throws IOException {
		Properties ps = new Properties();
		ps.load(resourceLoader(fileFullPath));
		return ps;
	}
	
	
	public static InputStream resourceLoader(String fileFullPath) throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        return resourceLoader.getResource(fileFullPath).getInputStream();
    }
}
