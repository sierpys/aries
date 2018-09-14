package com.alibaba.message.model;

/**
 * @author sier.pys 9/14/18
 */
public class AsyncHttpMessage extends AbstractMessage {

    private final String wholeUrl;

    public AsyncHttpMessage(String wholeUrl) {
        this.wholeUrl = wholeUrl;
    }

    public String getWholeUrl() {
        return this.wholeUrl;
    }
}
