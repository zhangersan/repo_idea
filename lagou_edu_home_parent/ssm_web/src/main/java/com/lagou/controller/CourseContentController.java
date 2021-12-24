package com.lagou.controller;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ersan
 * @date 2021/12/20
 */
@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    /**
     * 根据课程id查询课程内容
     * @param courseId
     * @return
     */
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLesson(Integer courseId){
        List<CourseSection> courseSectionList = courseContentService.findSectionAndLessonByCourseId(courseId);
        return new ResponseResult(true,200,"响应成功",courseSectionList);
    }

    /**
     * 根据课程id查询课程名称
     * @param courseId
     * @return
     */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);
        return new ResponseResult(true,200,"响应成功",course);
    }

    /**
     * 添加或修改章节信息
     * @param section
     * @return
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection section){

        if (section.getId() == null){
            courseContentService.saveSection(section);
            return new ResponseResult(true,200,"添加成功",null);
        } else {
            courseContentService.updateSection(section);
            return new ResponseResult(true,200,"修改成功",null);
        }
    }

    /**
     * 修改课程状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(Integer id,int status){
        courseContentService.updateSectionStatus(id,status);

        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("status",status);
        return new ResponseResult(true,200,"响应成功",map);
    }


}
