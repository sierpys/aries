package com.alibaba.message.publisher;

import com.alibaba.message.model.AbstractMessage;
import com.alibaba.message.model.AsyncHttpMessage;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author sier.pys 9/14/18
 */
public class AsyncHttpPublisher implements Publisher {
    final Logger logger = LoggerFactory.getLogger(getClass());


    private MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
    private HttpClient client = new HttpClient(connectionManager);
    private int retryTimes = 2;

    public AsyncHttpPublisher() {
        PublisherManger.registerPublisher(AsyncHttpMessage.class, this);
    }

    @Override
    public boolean sendMessage(AbstractMessage message) {
        if (!(message instanceof AsyncHttpMessage)) {
            throw new IllegalArgumentException("message is not instance of Http Message");
        }
        AsyncHttpMessage asyncHttpMessage = (AsyncHttpMessage) message;
        GetMethod get = new GetMethod(asyncHttpMessage.getWholeUrl());
        try {
            for (int i = 0; i < retryTimes; ++i) {
                try {
                    client.executeMethod(get);
                    if (get.getStatusCode() == HttpStatus.SC_OK) {
                        return true;
                    }
                } catch (IOException e) {
                    logger.error(Thread.currentThread().getName() + " - failed to send async http message ["
                            + get.getQueryString() + "] retry " + i + " : " + e.getMessage(), e);
                }
            }
        } finally {
            get.releaseConnection();
        }
        return false;
    }

    public int getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }
}
