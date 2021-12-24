package com.lagou.service;

import com.lagou.domain.PromotionSpace;

import java.util.List;

/**
 * @author ersan
 * @date 2021/12/20
 */
public interface PromotionSpaceService {

    /**
     * 查询所有广告位
     * @return
     */
    public List<PromotionSpace> findAllPromotionSpace();

    /**
     * 根据id查询广告位(回显)
     * @param id
     * @return
     */
    public PromotionSpace findPromotionSpaceById(Integer id);

    /**
     * 新增广告位
     * @param promotionSpace
     */
    public void savePromotionSpace(PromotionSpace promotionSpace);

    /**
     * 修改广告位
     * @param promotionSpace
     */
    public void updatePromotionSpace(PromotionSpace promotionSpace);
}
