package com.alibaba.message.publisher;

import com.alibaba.message.model.AbstractMessage;

/**
 * @author sier.pys 9/13/18
 */
public interface Publisher {
    boolean sendMessage(AbstractMessage message);
}
