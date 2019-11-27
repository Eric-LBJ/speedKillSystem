package com.aikeeper.speed.kill.system.component.impl;

import com.aikeeper.speed.kill.system.component.GoodsInfoComponent;
import com.aikeeper.speed.kill.system.dal.GoodsInfoMapper;
import com.aikeeper.speed.kill.system.domain.info.GoodsInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 */
@Component
public class GoodsInfoComponentImpl implements GoodsInfoComponent {

    @Resource
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    public Boolean deleteByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public Boolean insert(GoodsInfo record) {
        return null;
    }

    @Override
    public GoodsInfo selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<GoodsInfo> selectAll() {
        return null;
    }

    @Override
    public Boolean updateByPrimaryKey(GoodsInfo record) {
        return null;
    }
}