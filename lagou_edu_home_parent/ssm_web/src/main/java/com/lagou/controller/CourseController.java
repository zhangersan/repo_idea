package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import com.lagou.service.impl.CourseServiceImpl;
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
import java.util.List;
import java.util.Map;

/**
 * @author ersan
 * @date 2021/12/18
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/findByConditions")
    public ResponseResult findByConditions(@RequestBody CourseVO courseVO) {
        List<Course> courseList = courseService.findByConditions(courseVO);
        return new ResponseResult(true, 0, "响应成功", courseList);
    }

    /**
     * 图片上传且回显
     *
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

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
     * 根据id查询课程和讲师信息
     * @param id
     * @return
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){

        CourseVO courseVO = courseService.findCourseById(id);

        return new ResponseResult(true,200,"根据id查找成功",courseVO);
    }


    /**
     * 新增或修改操作
     * @param courseVO
     * @return
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO){

        if (courseVO.getId() == null){
            courseService.saveCourseAndTeacher(courseVO);
            return new ResponseResult(true,200,"添加成功",null);
        } else {
            courseService.updateCourseAndTeacher(courseVO);
            return new ResponseResult(true,200,"修改成功",null);
        }
    }

    /**
     * 修改课程状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(int id,int status){
        courseService.updateCourseStatus(id,status);
        Map<String,Integer> map = new HashMap<>();
        map.put("status",status);
        return new ResponseResult(true,200,"课程状态修改成功",map);
    }
}
