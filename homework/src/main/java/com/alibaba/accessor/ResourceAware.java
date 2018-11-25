package com.alibaba.accessor;

import com.alibaba.accessor.support.Resource;

/**
 * @author sier.pys 11/19/18
 */
public interface ResourceAware {
    default void setResource(Resource resource){

    };
}
