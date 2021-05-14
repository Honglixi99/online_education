package cn.yzaaa.eduservice.controller.front;

import cn.yzaaa.eduservice.entity.EduCourse;
import cn.yzaaa.eduservice.entity.EduTeacher;
import cn.yzaaa.eduservice.service.EduCourseService;
import cn.yzaaa.eduservice.service.EduTeacherService;
import cn.yzaaa.vod.commonutils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Honglixi
 * @create 2021-05-14-15:46
 */
@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;
    //查询前八条热门课程，查询前4条名师
    public R index(){
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<EduCourse> edulist = courseService.list(wrapper);

        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapperTeacher);

        return R.ok().data("eduList",edulist).data("teacherList",teacherList);
    }
}
