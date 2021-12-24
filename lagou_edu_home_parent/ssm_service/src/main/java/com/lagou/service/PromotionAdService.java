package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionVO;

import java.util.List;

/**
 * @author ersan
 * @date 2021/12/20
 */
public interface PromotionAdService {

    /**
     * 查询所有广告
     * @return
     */
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionVO promotionVO);

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
     * @param id
     * @param status
     */
    public void updatePromotionAdStatus(int id, int status);
}
