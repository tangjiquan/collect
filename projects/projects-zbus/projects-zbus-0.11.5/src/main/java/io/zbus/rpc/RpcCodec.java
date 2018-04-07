/**
 * The MIT License (MIT)
 * Copyright (c) 2009-2015 HONG LEIMING
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.zbus.rpc;

import io.zbus.transport.http.Message;

public interface RpcCodec {
	Message  encodeRequest(Request request, String encoding); 
	Message  encodeResponse(Object response, String encoding); 
	Request  decodeRequest(Message msg); 
	Object decodeResponse(Message msg);  
	
	<T> T convert(Object value, Class<T> clazz); 
	void setRequestTypeInfo(boolean requestTypeInfo); //encoding info includes Type info, e.g. Class info
	void setResponseTypeInfo(boolean responseTypeInfo);   //encoding info includes Type info, e.g. Class info
	
	public static final int STATUS_OK = 200;
	public static final int STATUS_APP_ERROR = 600; //extension of HTTP status
}
