package com.aikeeper.speed.kill.system.service;

import com.aikeeper.speed.kill.system.api.GoodsInfoService;
import com.aikeeper.speed.kill.system.component.GoodsInfoComponent;
import com.aikeeper.speed.kill.system.domain.info.GoodsInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 */
@Service("goodsInfoService")
public class GoodsInfoServiceImpl implements GoodsInfoService {

    @Resource
    private GoodsInfoComponent goodsInfoComponent;

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