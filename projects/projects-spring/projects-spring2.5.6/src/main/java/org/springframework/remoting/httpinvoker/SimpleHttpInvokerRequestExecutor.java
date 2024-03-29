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

package org.springframework.remoting.httpinvoker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.remoting.support.RemoteInvocationResult;
import org.springframework.util.StringUtils;

/**
 * HttpInvokerRequestExecutor implementation that uses standard J2SE facilities
 * to execute POST requests, without support for HTTP authentication or
 * advanced configuration options.
 *
 * <p>Designed for easy subclassing, customizing specific template methods.
 * However, consider CommonsHttpInvokerRequestExecutor for more sophisticated
 * needs: The J2SE HttpURLConnection is rather limited in its capabilities.
 *
 * @author Juergen Hoeller
 * @since 1.1
 * @see CommonsHttpInvokerRequestExecutor
 * @see HttpURLConnection
 */
public class SimpleHttpInvokerRequestExecutor extends AbstractHttpInvokerRequestExecutor {

	/**
	 * Execute the given request through a standard J2SE HttpURLConnection.
	 * <p>This method implements the basic processing workflow:
	 * The actual work happens in this class's template methods.
	 * @see #openConnection
	 * @see #prepareConnection
	 * @see #writeRequestBody
	 * @see #validateResponse
	 * @see #readResponseBody
	 */
	protected RemoteInvocationResult doExecuteRequest(
			HttpInvokerClientConfiguration config, ByteArrayOutputStream baos)
			throws IOException, ClassNotFoundException {

		HttpURLConnection con = openConnection(config);
		prepareConnection(con, baos.size());
		writeRequestBody(config, con, baos);
		validateResponse(config, con);
		InputStream responseBody = readResponseBody(config, con);

		return readRemoteInvocationResult(responseBody, config.getCodebaseUrl());
	}

	/**
	 * Open an HttpURLConnection for the given remote invocation request.
	 * @param config the HTTP invoker configuration that specifies the
	 * target service
	 * @return the HttpURLConnection for the given request
	 * @throws IOException if thrown by I/O methods
	 * @see URL#openConnection()
	 */
	protected HttpURLConnection openConnection(HttpInvokerClientConfiguration config) throws IOException {
		URLConnection con = new URL(config.getServiceUrl()).openConnection();
		if (!(con instanceof HttpURLConnection)) {
			throw new IOException("Service URL [" + config.getServiceUrl() + "] is not an HTTP URL");
		}
		return (HttpURLConnection) con;
	}

	/**
	 * Prepare the given HTTP connection.
	 * <p>The default implementation specifies POST as method,
	 * "application/x-java-serialized-object" as "Content-Type" header,
	 * and the given content length as "Content-Length" header.
	 * @param con the HTTP connection to prepare
	 * @param contentLength the length of the content to send
	 * @throws IOException if thrown by HttpURLConnection methods
	 * @see HttpURLConnection#setRequestMethod
	 * @see HttpURLConnection#setRequestProperty
	 */
	protected void prepareConnection(HttpURLConnection con, int contentLength) throws IOException {
		con.setDoOutput(true);
		con.setRequestMethod(HTTP_METHOD_POST);
		con.setRequestProperty(HTTP_HEADER_CONTENT_TYPE, getContentType());
		con.setRequestProperty(HTTP_HEADER_CONTENT_LENGTH, Integer.toString(contentLength));
		LocaleContext locale = LocaleContextHolder.getLocaleContext();
		if (locale != null) {
			con.setRequestProperty(HTTP_HEADER_ACCEPT_LANGUAGE, StringUtils.toLanguageTag(locale.getLocale()));
		}
		if (isAcceptGzipEncoding()) {
			con.setRequestProperty(HTTP_HEADER_ACCEPT_ENCODING, ENCODING_GZIP);
		}
	}

	/**
	 * Set the given serialized remote invocation as request body.
	 * <p>The default implementation simply write the serialized invocation to the
	 * HttpURLConnection's OutputStream. This can be overridden, for example, to write
	 * a specific encoding and potentially set appropriate HTTP request headers.
	 * @param config the HTTP invoker configuration that specifies the target service
	 * @param con the HttpURLConnection to write the request body to
	 * @param baos the ByteArrayOutputStream that contains the serialized
	 * RemoteInvocation object
	 * @throws IOException if thrown by I/O methods
	 * @see HttpURLConnection#getOutputStream()
	 * @see HttpURLConnection#setRequestProperty
	 */
	protected void writeRequestBody(
			HttpInvokerClientConfiguration config, HttpURLConnection con, ByteArrayOutputStream baos)
			throws IOException {

		baos.writeTo(con.getOutputStream());
	}

	/**
	 * Validate the given response as contained in the HttpURLConnection object,
	 * throwing an exception if it does not correspond to a successful HTTP response.
	 * <p>Default implementation rejects any HTTP status code beyond 2xx, to avoid
	 * parsing the response body and trying to deserialize from a corrupted stream.
	 * @param config the HTTP invoker configuration that specifies the target service
	 * @param con the HttpURLConnection to validate
	 * @throws IOException if validation failed
	 * @see HttpURLConnection#getResponseCode()
	 */
	protected void validateResponse(HttpInvokerClientConfiguration config, HttpURLConnection con)
			throws IOException {

		if (con.getResponseCode() >= 300) {
			throw new IOException(
					"Did not receive successful HTTP response: status code = " + con.getResponseCode() +
					", status message = [" + con.getResponseMessage() + "]");
		}
	}

	/**
	 * Extract the response body from the given executed remote invocation
	 * request.
	 * <p>The default implementation simply reads the serialized invocation
	 * from the HttpURLConnection's InputStream. If the response is recognized
	 * as GZIP response, the InputStream will get wrapped in a GZIPInputStream.
	 * @param config the HTTP invoker configuration that specifies the target service
	 * @param con the HttpURLConnection to read the response body from
	 * @return an InputStream for the response body
	 * @throws IOException if thrown by I/O methods
	 * @see #isGzipResponse
	 * @see GZIPInputStream
	 * @see HttpURLConnection#getInputStream()
	 * @see HttpURLConnection#getHeaderField(int)
	 * @see HttpURLConnection#getHeaderFieldKey(int)
	 */
	protected InputStream readResponseBody(HttpInvokerClientConfiguration config, HttpURLConnection con)
			throws IOException {

		if (isGzipResponse(con)) {
			// GZIP response found - need to unzip.
			return new GZIPInputStream(con.getInputStream());
		}
		else {
			// Plain response found.
			return con.getInputStream();
		}
	}

	/**
	 * Determine whether the given response is a GZIP response.
	 * <p>Default implementation checks whether the HTTP "Content-Encoding"
	 * header contains "gzip" (in any casing).
	 * @param con the HttpURLConnection to check
	 */
	protected boolean isGzipResponse(HttpURLConnection con) {
		String encodingHeader = con.getHeaderField(HTTP_HEADER_CONTENT_ENCODING);
		return (encodingHeader != null && encodingHeader.toLowerCase().indexOf(ENCODING_GZIP) != -1);
	}

}
