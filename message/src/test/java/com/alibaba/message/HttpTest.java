package com.alibaba.message;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;

import java.io.IOException;

/**
 * @author sier.pys 9/14/18
 */
public class HttpTest {
    MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
    HttpClient client = new HttpClient(connectionManager);


    @Test
    public void getTest() {
        GetMethod httpget = new GetMethod(
                "http://www.google.com/search?hl=en&q=httpclient&btnG=Google+Search&aq=f&oq=");


        try {
            client.executeMethod(httpget);

            byte[] responseBody = httpget.getResponseBody();

            System.out.println(new String(responseBody));
        } catch (IOException e) {

        }
    }
}
