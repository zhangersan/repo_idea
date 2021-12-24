package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;

import java.util.List;

/**
 * @author ersan
 * @date 2021/12/18
 */
public interface CourseMapper {

    /**
     * 条件查询（课程名称和状态）
     * @param courseVO
     * @return
     */
    public List<Course> findByConditions(CourseVO courseVO);

    /**
     * 课程新增
     * @param course
     */
    public void saveCourse(Course course);

    /**
     * 讲师添加
     * @param teacher
     */
    public void saveTeacher(Teacher teacher);

    /**
     * 根据id查询课程和讲师信息
     * @param id
     * @return
     */
    public CourseVO findCourseById(Integer id);

    /**
     * 课程修改
     * @param course
     */
    public void updateCourse(Course course);

    /**
     * 讲师修改
     * @param teacher
     */
    public void updateTeacher(Teacher teacher);

    /**
     * 修改课程状态
     * @param course
     */
    public void updateCourseStatus(Course course);
}
