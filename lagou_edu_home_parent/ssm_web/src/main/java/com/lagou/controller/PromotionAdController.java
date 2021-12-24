package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ersan
 * @date 2021/12/21
 */
@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    /**
     * 分页查询
     * @param promotionVO
     * @return
     */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionVO promotionVO){

        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionAdByPage(promotionVO);
        return new ResponseResult(true,200,"分页查询响应成功!",pageInfo);
    }

    /**
     * 图片上传且回显
     *
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult promotionAdUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        //1.判断文件是否为空
        if (file.isEmpty()) {
            throw new RuntimeException();
        }

        //2.获取项目路径
        //D:\apache-tomcat-8.5.56\webapps\ssm_web\
        String realPath = request.getServletContext().getRealPath("/");
        //按照要求截取路径:D:\apache-tomcat-8.5.56\webapps\
        String urlStr = realPath.substring(0, realPath.indexOf("ssm_web"));

        //3.获取文件名并重命名
        //123.jsp
        String originalFilename = file.getOriginalFilename();
        //.jsp
        String newFilename = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        /*
        4.设置文件上传路径
        "D:\apache-tomcat-8.5.56\webapps\ upload  \"
        */
        String uploadPath = urlStr + "upload\\";
        //5.把文件路径和文件名称放在文件流中
        File filePath = new File(uploadPath, newFilename);

        //6.判断是否存在目录，不存在则创建
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录成功！" + filePath);
        }

        //7.最后上传文件
        file.transferTo(filePath);

        //8.响应数据
        Map<String, String> map = new HashMap<>();
        map.put("fileName", newFilename);
        map.put("filePath", "http://localhost:8080/upload/" + newFilename);

        return new ResponseResult(true, 200, "响应成功", map);
    }

    /**
     * 根据id查询广告信息
     * @param id
     * @return
     */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(int id){
        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);
        return new ResponseResult(true,200,"查找成功",promotionAd);
    }

    /**
     * 添加或修改广告
     * @param promotionVO
     * @return
     */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotion(@RequestBody PromotionAd promotionVO){

        if (promotionVO.getId() == null){
            promotionAdService.savePromotionAd(promotionVO);
            return new ResponseResult(true, 200, "添加成功",null);
        } else{
            promotionAdService.updatePromotionAd(promotionVO);
            return new ResponseResult(true, 200, "修改成功",null);

        }
    }

    /**
     * 修改广告状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(int id,int status){
        promotionAdService.updatePromotionAdStatus(id,status);

        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("status",status);

        return new ResponseResult(true, 200, "状态修改成功!",map);
    }
}
