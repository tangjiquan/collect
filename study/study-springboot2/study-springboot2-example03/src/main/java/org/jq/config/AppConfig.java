package org.jq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Kevin
 * @Date: Created in 下午8:27 18-5-1
 * @Version:
 * @Description:
 */

@Component//这是一个组件， 写上这个注解，springboot会启动的时候加载他
@ConfigurationProperties(prefix = "app")// 将所有app前缀的属性自动赋值给对应的Bean属性
public class AppConfig {
	private String name;
	private String desc;
	private String version;
	private Integer pageSize;
	private boolean showAdvert;
	private String website;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isShowAdvert() {
		return showAdvert;
	}

	public void setShowAdvert(boolean showAdvert) {
		this.showAdvert = showAdvert;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
}
