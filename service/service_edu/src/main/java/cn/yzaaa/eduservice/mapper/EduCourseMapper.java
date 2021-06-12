package cn.yzaaa.eduservice.mapper;

import cn.yzaaa.eduservice.entity.EduCourse;
import cn.yzaaa.eduservice.entity.frontvo.CourseWebVo;
import cn.yzaaa.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-04-29
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public CoursePublishVo getPublishCourseInfo(String courseId);

    CourseWebVo getBaseCourseInfo(String courseId);
}
