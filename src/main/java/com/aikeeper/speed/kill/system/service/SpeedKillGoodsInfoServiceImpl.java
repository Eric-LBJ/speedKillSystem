package com.aikeeper.speed.kill.system.service;

import com.aikeeper.speed.kill.system.api.SpeedKillGoodsInfoService;
import com.aikeeper.speed.kill.system.component.SpeedKillGoodsInfoComponent;
import com.aikeeper.speed.kill.system.domain.info.SpeedKillGoodsInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 */
@Service("speedKillGoodsInfoService")
public class SpeedKillGoodsInfoServiceImpl implements SpeedKillGoodsInfoService {

    @Resource
    private SpeedKillGoodsInfoComponent speedKillGoodsInfoComponent;

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