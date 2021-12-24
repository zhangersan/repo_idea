package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

/**
 * @author ersan
 * @date 2021/12/20
 */
public interface PromotionAdMapper {

    /**
     * 查询所有广告
     * @return
     */
    public List<PromotionAd> findAllPromotionAdByPage();

    /**
     * 添加新的广告
     * @param promotionAd
     */
    public void savePromotionAd(PromotionAd promotionAd);

    /**
     * 根据id查询广告信息(回显)
     * @param id
     * @return
     */
    public PromotionAd findPromotionAdById(Integer id);

    /**
     * 修改广告
     * @param promotionAd
     */
    public void updatePromotionAd(PromotionAd promotionAd);

    /**
     * 修改广告状态
     * @param promotionAd
     */
    public void updatePromotionAdStatus(PromotionAd promotionAd);


}
