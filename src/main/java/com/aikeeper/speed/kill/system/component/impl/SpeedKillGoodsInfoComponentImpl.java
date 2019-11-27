package com.aikeeper.speed.kill.system.component.impl;

import com.aikeeper.speed.kill.system.component.SpeedKillGoodsInfoComponent;
import com.aikeeper.speed.kill.system.dal.SpeedKillGoodsInfoMapper;
import com.aikeeper.speed.kill.system.domain.info.SpeedKillGoodsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 */
@Component
public class SpeedKillGoodsInfoComponentImpl implements SpeedKillGoodsInfoComponent {

    @Resource
    private SpeedKillGoodsInfoMapper speedKillGoodsInfoMapper;

    @Override
    public Boolean deleteByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public Boolean insert(SpeedKillGoodsInfo record) {
        return null;
    }

    @Override
    public SpeedKillGoodsInfo selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<SpeedKillGoodsInfo> selectAll() {
        return null;
    }

    @Override
    public Boolean updateByPrimaryKey(SpeedKillGoodsInfo record) {
        return null;
    }
}