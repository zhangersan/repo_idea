package com.lagou.service;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;

import java.util.List;

/**
 * @author ersan
 * @date 2021/12/20
 */
public interface CourseContentService {

    /**
     * 根据课程id查询章节和课时信息
     * @param courseId
     * @return
     */
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /**
     * 根据课程id查询课程名称
     * @param courseId
     * @return
     */
    public Course findCourseByCourseId(Integer courseId);

    /**
     * 新增章节信息
     * @param courseSection
     */
    public void saveSection(CourseSection courseSection);

    /**
     * 修改章节信息
     * @param courseSection
     */
    public void updateSection(CourseSection courseSection);

    /**
     * 修改章节状态
     * @param id,status
     */
    public void updateSectionStatus(Integer id,int status);

}
