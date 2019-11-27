package com.aikeeper.speed.kill.system.component.impl;

import com.aikeeper.speed.kill.system.component.SpeedKillOrderInfoComponent;
import com.aikeeper.speed.kill.system.dal.SpeedKillOrderInfoMapper;
import com.aikeeper.speed.kill.system.domain.info.SpeedKillOrderInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 */
@Component
public class SpeedKillOrderInfoComponentImpl implements SpeedKillOrderInfoComponent {

    @Resource
    private SpeedKillOrderInfoMapper speedKillOrderInfoMapper;

    @Override
    public Boolean deleteByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public Boolean insert(SpeedKillOrderInfo record) {
        return null;
    }

    @Override
    public SpeedKillOrderInfo selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<SpeedKillOrderInfo> selectAll() {
        return null;
    }

    @Override
    public Boolean updateByPrimaryKey(SpeedKillOrderInfo record) {
        return null;
    }
}