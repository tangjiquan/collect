/**
 * 
 */
package org.tio.http.common.view.freemarker;

import freemarker.template.Configuration;
import org.tio.http.common.HttpConfig;

import java.io.IOException;

/**
 * @author tanyaowu
 *
 */
public interface ConfigurationCreater {
	/**
	 * 
	 * @param httpConfig
	 * @param root
	 * @return
	 * @throws IOException
	 */
	public Configuration createConfiguration(HttpConfig httpConfig, String root) throws IOException;

}
