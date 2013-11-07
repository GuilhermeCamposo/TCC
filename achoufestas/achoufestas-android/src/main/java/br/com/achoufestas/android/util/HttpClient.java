package br.com.achoufestas.android.util;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class HttpClient extends DefaultHttpClient {
	
	public HttpClient(){
		
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is established.
		// The default value is zero, that means the timeout is not used. 
		int timeoutConnection = 320000;
		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
		
		int timeoutSocket = 320000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		
		setParams(httpParameters);
	
	}

}
