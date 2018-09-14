package com.alibaba.message.binding;

import com.alibaba.message.model.AbstractMessage;
import com.alibaba.message.model.AsyncHttpMessage;

/**
 * @author sier.pys 9/14/18
 */
public class AsyncHttpMessageBinding<R extends String> extends AbstractMessageBinding<R> {

    @Override
    public AbstractMessage toMessage(Object input) {
        String wholeUrl = serializer.serialize(input);
        return new AsyncHttpMessage(wholeUrl);
    }
}
