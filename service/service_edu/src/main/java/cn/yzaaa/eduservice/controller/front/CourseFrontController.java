package cn.yzaaa.eduservice.controller.front;

import cn.yzaaa.eduservice.entity.EduCourse;
import cn.yzaaa.eduservice.entity.EduTeacher;
import cn.yzaaa.eduservice.entity.frontvo.CourseFrontVo;
import cn.yzaaa.eduservice.service.EduCourseService;
import cn.yzaaa.eduservice.service.EduTeacherService;
import cn.yzaaa.vod.commonutils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    //1 条件查询带分页查询课程
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(pageCourse,courseFrontVo);
        //返回分页所有数据
        return R.ok().data(map);
    }
}
