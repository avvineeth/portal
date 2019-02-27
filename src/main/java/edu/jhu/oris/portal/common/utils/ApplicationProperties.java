package edu.jhu.oris.portal.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class get details from property files and load data to class 
 *
 */
public class ApplicationProperties{
	
	private static Properties props = null;
	private static final String APPLICATION_PROP_FILE="/application.properties";
	
	/**
	 * Constructor Creates a new instance of IRBProperties
	 */
	private ApplicationProperties() {}
	
	/**
	 * Loads properties from the property file
	 */
	private static Properties loadProperties() throws IOException {

		InputStream stream = null;
		try {
			props = new Properties();
			stream = new ApplicationProperties().getClass().getResourceAsStream(APPLICATION_PROP_FILE);
			props.load( stream );
		} finally {
			try {
				stream.close();
			} catch (Exception ex) {
			}
		}
		return props;
	}
	
	/**
	 * retrieves single property value from property file -application.properties
	 */
	public static String getProperty(String key,String defaultValue) throws IOException {
		if (props == null) {
			synchronized (ApplicationProperties.class) {
				if (props == null) {
					props = loadProperties();
				}
			}
		}
		return props.getProperty(key,defaultValue);
	}
	/**
	 * Get a property value from the application.properties file.
	 */
	public static String getProperty(String key) throws IOException {
		return getProperty(key,null);
	}
}