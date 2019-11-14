package com.alibaba.core.mapper;

import com.alibaba.core.model.ZDZBPosition;

public interface ZDZBPositionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ZDZBPosition record);

    int insertSelective(ZDZBPosition record);

    ZDZBPosition selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ZDZBPosition record);

    int updateByPrimaryKey(ZDZBPosition record);
}