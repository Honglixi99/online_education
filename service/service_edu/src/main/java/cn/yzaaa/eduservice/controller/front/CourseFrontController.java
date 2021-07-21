package cn.yzaaa.eduservice.controller.front;

import cn.yzaaa.eduservice.client.OrdersClient;
import cn.yzaaa.eduservice.entity.EduCourse;
import cn.yzaaa.eduservice.entity.EduTeacher;
import cn.yzaaa.eduservice.entity.chapterVo.ChapterVo;
import cn.yzaaa.eduservice.entity.frontvo.CourseFrontVo;
import cn.yzaaa.eduservice.entity.frontvo.CourseWebVo;
import cn.yzaaa.eduservice.service.EduChapterService;
import cn.yzaaa.eduservice.service.EduCourseService;
import cn.yzaaa.eduservice.service.EduTeacherService;
import cn.yzaaa.vod.commonutils.JwtUtils;
import cn.yzaaa.vod.commonutils.R;
import cn.yzaaa.vod.commonutils.ordervo.CourseWebVoOrder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private OrdersClient ordersClient;

    //1 条件查询带分页查询课程
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(pageCourse,courseFrontVo);
        //返回分页所有数据
        return R.ok().data(map);
    }
    //2 课程详情的方法
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request){
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);

        //根据课程id查询章节和小节
        List<ChapterVo> chapterVoList = chapterService.getChapterVideoByCourseId(courseId);

        //根据课程id和用户id查询当前课程是否已经支付过了
        boolean buyCourse = ordersClient.isBuyCourse(courseId, JwtUtils.getMemberIdByJwtToken(request));

        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVoList).data("isBuy",buyCourse);
    }

    //根据课程id查询课程信息
    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id){
        CourseWebVo courseInfo = courseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseInfo,courseWebVoOrder);
        return courseWebVoOrder;
    }
}
