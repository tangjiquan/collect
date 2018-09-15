package org.panther;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: Kevin
 * @date: created in 下午10:35 2018-08-09
 * @version: V1.0
 */
public class ClassUtil {

	private String filePath = "/config/"; //配置文件路径

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public DynamicBean dynamicClass(Object object) throws Exception {
		/*HashMap returnMap = new HashMap();
		HashMap typeMap = new HashMap();
		//读取配置文件
		Properties prop = new Properties();
		String sourcepackage = object.getClass().getName();
		String classname = sourcepackage.substring(sourcepackage.lastIndexOf(".") + 1);
		InputStream in = ClassUtil.class.getResourceAsStream(filePath + classname + ".properties");
		prop.load(in);

		Set<String> keylist = prop.stringPropertyNames();

		Class type = object.getClass();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for(int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if(!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(object, new Object[0]);
				if(result != null) {
					returnMap.put(propertyName, result);
				} else {
					returnMap.put(propertyName, "");
				}
				typeMap.put(propertyName, descriptor.getPropertyType());
			}
		}*/

		HashMap returnMap = new HashMap();
		HashMap typeMap = new HashMap();

		typeMap.put("name", String.class);
		typeMap.put("address", String.class);

		returnMap.put("name", "zhangsan");
		returnMap.put("address", "中国苏州");

		//map转换成实体对象
		DynamicBean bean = new DynamicBean(typeMap);
		//赋值
		Set keys = typeMap.keySet();
		for(Iterator it = keys.iterator(); it.hasNext(); ) {
			String key = (String) it.next();
			bean.setValue(key, returnMap.get(key));
		}
		//Object obj = bean.getObject();
		//return obj;
		return bean;
	}

	public static void main(String[] args) throws Exception {
		//Object obj = new ClassUtil().dynamicClass(new ClassUtil()/*LeapRole是个普通类，未贴源码*/);
		//System.out.println(obj);


		DynamicBean bean  = new ClassUtil().dynamicClass(new ClassUtil()/*LeapRole是个普通类，未贴源码*/);
		System.out.println(bean.getValue("name"));
		System.out.println(bean.getValue("address"));
	}


}
