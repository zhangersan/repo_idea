package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;

import java.util.List;

/**
 * @author ersan
 * @date 2021/12/18
 */
public interface CourseService {

    /**
     * 条件查询（课程名称和状态）
     * @param courseVO
     * @return
     */
    public List<Course> findByConditions(CourseVO courseVO);

    /**
     * 新增课程和讲师
     * @param courseVO
     */
    public void saveCourseAndTeacher(CourseVO courseVO);

    /**
     * 根据id查询课程和讲师信息
     * @param id
     * @return
     */
    public CourseVO findCourseById(Integer id);

    /**
     * 课程和讲师修改
     * @param courseVO
     */
    public void updateCourseAndTeacher(CourseVO courseVO);

    /**
     * 修改课程状态
     * @param id
     * @param status
     */
    public void updateCourseStatus(int id, int status);
}
