package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 * @author ersan
 * @date 2021/12/18
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findByConditions(CourseVO courseVO) {
        return courseMapper.findByConditions(courseVO);
    }

    @Override
    public void saveCourseAndTeacher(CourseVO courseVO) {

        try {

            /*新增课程*/
            Course course = new Course();

            //将courseVO对应的值封装到course中
            BeanUtils.copyProperties(course, courseVO);

            //设置时间
            Date date = new Date();
            course.setCreateTime(date);
            course.setUpdateTime(date);

            //新增
            courseMapper.saveCourse(course);
            /*新增课程*/

            /*添加讲师*/
            Teacher teacher = new Teacher();

            BeanUtils.copyProperties(teacher, courseVO);

            teacher.setCreateTime(date);
            teacher.setUpdateTime(date);
            teacher.setIsDel(0);
            //获取course_id(这个id就是根据dao.xml中的sql语句来的)
            teacher.setCourseId(course.getId());

            courseMapper.saveTeacher(teacher);
            /*添加讲师*/

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    public CourseVO findCourseById(Integer id) {
        return courseMapper.findCourseById(id);
    }

    @Override
    public void updateCourseAndTeacher(CourseVO courseVO) {

        try {
            /*课程修改*/
            Date date = new Date();
            //封装Course类
            Course course = new Course();
            BeanUtils.copyProperties(course, courseVO);

            //补全时间属性
            course.setUpdateTime(date);
            //修改课程方法
            courseMapper.updateCourse(course);
            /*课程修改*/

            /*讲师修改*/
            //封装Teacher类
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacher, courseVO);

            //补全时间属性
            teacher.setCourseId(course.getId());
            teacher.setUpdateTime(date);
            //修改讲师方法
            courseMapper.updateTeacher(teacher);
            /*讲师修改*/

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateCourseStatus(int id, int status) {

        //封装数据
        Course course = new Course();
        course.setId(id);
        course.setStatus(status);
        course.setUpdateTime(new Date());

        //调用方法
        courseMapper.updateCourseStatus(course);
    }

}
