package com.aikeeper.speed.kill.system.service;

import com.aikeeper.speed.kill.system.api.SpeedKillOrderInfoService;
import com.aikeeper.speed.kill.system.component.SpeedKillOrderInfoComponent;
import com.aikeeper.speed.kill.system.domain.info.SpeedKillOrderInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 */
@Service("speedKillOrderInfoService")
public class SpeedKillOrderInfoServiceImpl implements SpeedKillOrderInfoService {

    @Resource
    private SpeedKillOrderInfoComponent speedKillOrderInfoComponent;

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