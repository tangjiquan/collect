/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.beans.factory.xml;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.util.xml.DomUtils;

/**
 * Default implementation of the {@link BeanDefinitionDocumentReader} interface.
 * Reads bean definitions according to the "spring-beans" DTD and XSD format
 * (Spring's default XML bean definition format).
 *
 * <p>The structure, elements and attribute names of the required XML document
 * are hard-coded in this class. (Of course a transform could be run if necessary
 * to produce this format). <code>&lt;beans&gt;</code> doesn't need to be the root
 * element of the XML document: This class will parse all bean definition elements
 * in the XML file, not regarding the actual root element.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @author Erik Wiersma
 * @since 18.12.2003
 */
public class DefaultBeanDefinitionDocumentReader implements BeanDefinitionDocumentReader {

	public static final String BEAN_ELEMENT = BeanDefinitionParserDelegate.BEAN_ELEMENT;

	public static final String ALIAS_ELEMENT = "alias";

	public static final String NAME_ATTRIBUTE = "name";

	public static final String ALIAS_ATTRIBUTE = "alias";

	public static final String IMPORT_ELEMENT = "import";

	public static final String RESOURCE_ATTRIBUTE = "resource";


	protected final Log logger = LogFactory.getLog(getClass());

	private XmlReaderContext readerContext;


	/**
	 * Parses bean definitions according to the "spring-beans" DTD.
	 * <p>Opens a DOM Document; then initializes the default settings
	 * specified at <code>&lt;beans&gt;</code> level; then parses
	 * the contained bean definitions.
	 */
	/**
	 * ���XmlReaderContext��һ���Ự�࣬
	 * ������ע����̵ĻỰ�࣬�����г��б��ν����Ự��ʵ��������
    Resrouce��������ProblemReporter��FailFastProblemReporter��
           �¼�����ReaderEventListener��EmptyReaderEventListenerʹ�õ��ǿ�ʵ�֣���
         Դ��ȡ��SourceExtractor��NullSourceExtractor��ʵ�֣�
    XmlBeanDefinitionReader�����汣����BeanFactoryRegiester��ʵ��DefaultListableBeanFactory,Ҳ����ICO�������ڲ�beanFactory,ע���BeanDefenition�ͷ������棩
    NamespaceHandlerResolver��XML�����ļ��еĸ������ֿռ䣨�磺context������Ľڵ㣨��: context:property-placeholder���Ķ�Ӧ�������ķֽ�����ʵ��ͨ��Namespace SystemId�ҵ���Ӧ�Ľ���������·������Ҫͨ����ȡ����JAR�ļ���META-INF/spring.handlers�ļ�ʵ�֡�
	 */
	public void registerBeanDefinitions(Document doc, XmlReaderContext readerContext) {
		this.readerContext = readerContext;
		logger.debug("Loading bean definitions");
		//�õ�bean�����Ԫ�صĸ��ڵ�
		Element root = doc.getDocumentElement();
		//���BeanDefinitionParserDelegate��һ����Ҫ�ĸ����࣬��ʵ���˶Ծ���BeanԪ����
		//Bean������Ϣ�Ľ��� , ����������ί�д�������  
		BeanDefinitionParserDelegate delegate = createHelper(readerContext, root);
		 // ����ǰ�ô��������ǿ�ʵ��  
		preProcessXml(root);
		//��Ҫ�Ľ������� // ���������ĵ�����ѵ�����ӽڵ�ֱ�����������д������ 
		parseBeanDefinitions(root, delegate);
		//�������ô���Ҳ�ǿ�ʵ��  
		postProcessXml(root);
	}

	protected BeanDefinitionParserDelegate createHelper(XmlReaderContext readerContext, Element root) {
		BeanDefinitionParserDelegate delegate = new BeanDefinitionParserDelegate(readerContext);
		delegate.initDefaults(root);
		return delegate;
	}

	/**
	 * Return the descriptor for the XML resource that this parser works on.
	 */
	protected final XmlReaderContext getReaderContext() {
		return this.readerContext;
	}

	/**
	 * Invoke the {@link org.springframework.beans.factory.parsing.SourceExtractor} to pull the
	 * source metadata from the supplied {@link Element}.
	 */
	protected Object extractSource(Element ele) {
		return this.readerContext.extractSource(ele);
	}


	/**
	 * Parse the elements at the root level in the document:
	 * "import", "alias", "bean".
	 * @param root the DOM root element of the document
	 */
	/*
	 * ��Document�Ľ�����Ϊ���������һ����Ĭ�ϵ����ֿռ�beans��http://www.springframework.org/schema/beans�� 
	 * ��ǰ׺��������:bean�����������ֿռ�Ľڵ�Ľ�������ǰ׺���磺context:property-placeholder����
    	��ǰ׺��beansĬ�����ֿռ�ڵ㣺����BeanDefinitionParserDelegate�������Ĺ����ࣩ��ɽڵ�Ľ�����
    	��ǰ׺���������ֿռ�ڵ㣺ʹ�ý��������ɽ����������߼�Ϊ����ʹ��Namespace��SystemId������URLȫ·����
    	ͨ��NamespaceHandlerResolver�ҵ���ӦNamespaceHandler��Ȼ��ͨ�������NamespaceHandler��parse���������ڵ㡣
    	��NamespaceHandler�ڲ�ͨ�ڵ������ҵ���ӦBeanDefinitionParser��������ɽڵ�Ľ���������BeanDefinition��

	 */
	protected void parseBeanDefinitions(Element root, BeanDefinitionParserDelegate delegate) {
		if (delegate.isDefaultNamespace(root.getNamespaceURI())) {
			NodeList nl = root.getChildNodes();
			for (int i = 0; i < nl.getLength(); i++) {
				Node node = nl.item(i);
				if (node instanceof Element) {
					Element ele = (Element) node;
					String namespaceUri = ele.getNamespaceURI();
					  // �����Ĭ�����ֿռ�(beans),��ֱ��ʹ�ý���
					if (delegate.isDefaultNamespace(namespaceUri)) {
						//�����Spring�����Ĭ��Ԫ�ؽ��н���������Bean��Import�ȵ�
						parseDefaultElement(ele, delegate);
					}
					else {
						 // ����Ƿ�Ĭ�Ͽռ䣬��ʹ�ý��������ɡ�  
						delegate.parseCustomElement(ele);
					}
				}
			}
		}
		else {
			delegate.parseCustomElement(root);
		}
	}

	private void parseDefaultElement(Element ele, BeanDefinitionParserDelegate delegate) {
		//�����XML��Bean������Ϣ�е�ImportԪ�ؽ��н���
		if (DomUtils.nodeNameEquals(ele, IMPORT_ELEMENT)) {
			importBeanDefinitionResource(ele);
		}
		else if (DomUtils.nodeNameEquals(ele, ALIAS_ELEMENT)) {
			processAliasRegistration(ele);
		}
		//�����Ƕ�BeanԪ�ؽ��н����ĵط������ǿ��Կ�������Ľ��������ǽ���BeanDefinitionParserDelegate����ɵ�
		else if (DomUtils.nodeNameEquals(ele, BEAN_ELEMENT)) {
			processBeanDefinition(ele, delegate);
		}
	}

	/**
	 * Parse an "import" element and load the bean definitions
	 * from the given resource into the bean factory.
	 */
	protected void importBeanDefinitionResource(Element ele) {
		String location = ele.getAttribute(RESOURCE_ATTRIBUTE);
		if (!StringUtils.hasText(location)) {
			getReaderContext().error("Resource location must not be empty", ele);
			return;
		}

		// Resolve system properties: e.g. "${user.dir}"
		location = SystemPropertyUtils.resolvePlaceholders(location);

		if (ResourcePatternUtils.isUrl(location)) {
			try {
				Set actualResources = new LinkedHashSet(4);
				int importCount = getReaderContext().getReader().loadBeanDefinitions(location, actualResources);
				if (logger.isDebugEnabled()) {
					logger.debug("Imported " + importCount + " bean definitions from URL location [" + location + "]");
				}
				Resource[] actResArray = (Resource[]) actualResources.toArray(new Resource[actualResources.size()]);
				getReaderContext().fireImportProcessed(location, actResArray, extractSource(ele));
			}
			catch (BeanDefinitionStoreException ex) {
				getReaderContext().error(
						"Failed to import bean definitions from URL location [" + location + "]", ele, ex);
			}
		}
		else {
			// No URL -> considering resource location as relative to the current file.
			try {
				Resource relativeResource = getReaderContext().getResource().createRelative(location);
				int importCount = getReaderContext().getReader().loadBeanDefinitions(relativeResource);
				if (logger.isDebugEnabled()) {
					logger.debug("Imported " + importCount + " bean definitions from relative location [" + location + "]");
				}
				getReaderContext().fireImportProcessed(location, new Resource[] {relativeResource}, extractSource(ele));
			}
			catch (IOException ex) {
				getReaderContext().error(
						"Invalid relative resource location [" + location + "] to import bean definitions from", ele, ex);
			}
			catch (BeanDefinitionStoreException ex) {
				getReaderContext().error(
						"Failed to import bean definitions from relative location [" + location + "]", ele, ex);
			}
		}
	}

	/**
	 * Process the given alias element, registering the alias with the registry.
	 */
	protected void processAliasRegistration(Element ele) {
		String name = ele.getAttribute(NAME_ATTRIBUTE);
		String alias = ele.getAttribute(ALIAS_ATTRIBUTE);
		boolean valid = true;
		if (!StringUtils.hasText(name)) {
			getReaderContext().error("Name must not be empty", ele);
			valid = false;
		}
		if (!StringUtils.hasText(alias)) {
			getReaderContext().error("Alias must not be empty", ele);
			valid = false;
		}
		if (valid) {
			try {
				getReaderContext().getRegistry().registerAlias(name, alias);
			}
			catch (Exception ex) {
				getReaderContext().error("Failed to register alias '" + alias +
						"' for bean with name '" + name + "'", ele, ex);
			}
			getReaderContext().fireAliasRegistered(name, alias, extractSource(ele));
		}
	}

	/**
	 * Process the given bean element, parsing the bean definition
	 * and registering it with the registry.
	 */
	protected void processBeanDefinition(Element ele, BeanDefinitionParserDelegate delegate) {
		BeanDefinitionHolder bdHolder = delegate.parseBeanDefinitionElement(ele);
		if (bdHolder != null) {
			bdHolder = delegate.decorateBeanDefinitionIfRequired(ele, bdHolder);
			try {
				// Register the final decorated instance.
				BeanDefinitionReaderUtils.registerBeanDefinition(bdHolder, getReaderContext().getRegistry());
			}
			catch (BeanDefinitionStoreException ex) {
				getReaderContext().error("Failed to register bean definition with name '" +
						bdHolder.getBeanName() + "'", ele, ex);
			}
			// Send registration event.
			getReaderContext().fireComponentRegistered(new BeanComponentDefinition(bdHolder));
		}
	}


	/**
	 * Allow the XML to be extensible by processing any custom element types first,
	 * before we start to process the bean definitions. This method is a natural
	 * extension point for any other custom pre-processing of the XML.
	 * <p>The default implementation is empty. Subclasses can override this method to
	 * convert custom elements into standard Spring bean definitions, for example.
	 * Implementors have access to the parser's bean definition reader and the
	 * underlying XML resource, through the corresponding accessors.
	 * @see #getReaderContext()
	 */
	protected void preProcessXml(Element root) {
	}

	/**
	 * Allow the XML to be extensible by processing any custom element types last,
	 * after we finished processing the bean definitions. This method is a natural
	 * extension point for any other custom post-processing of the XML.
	 * <p>The default implementation is empty. Subclasses can override this method to
	 * convert custom elements into standard Spring bean definitions, for example.
	 * Implementors have access to the parser's bean definition reader and the
	 * underlying XML resource, through the corresponding accessors.
	 * @see #getReaderContext()
	 */
	protected void postProcessXml(Element root) {
	}

}
