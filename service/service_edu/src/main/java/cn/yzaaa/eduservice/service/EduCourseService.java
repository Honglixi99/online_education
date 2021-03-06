package cn.yzaaa.eduservice.service;

import cn.yzaaa.eduservice.entity.EduCourse;
import cn.yzaaa.eduservice.entity.frontvo.CourseFrontVo;
import cn.yzaaa.eduservice.entity.frontvo.CourseWebVo;
import cn.yzaaa.eduservice.entity.vo.CourseInfoVo;
import cn.yzaaa.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-04-29
 */
public interface EduCourseService extends IService<EduCourse> {
    //添加课程基本信息的方法
    String saveCourseInfo(CourseInfoVo courseInfoVo);
    //根据课程id查询基本信息
    CourseInfoVo getCourseInfo(String courseId);
    //修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void removeCourse(String courseId);

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
