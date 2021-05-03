package cn.yzaaa.vod.eduservice.controller;


import cn.yzaaa.vod.commonutils.R;
import cn.yzaaa.vod.eduservice.entity.EduTeacher;
import cn.yzaaa.vod.eduservice.entity.vo.TeacherQuery;
import cn.yzaaa.vod.eduservice.service.EduTeacherService;
import cn.yzaaa.vod.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-04-22
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {
    //把service注入
    @Autowired
    private EduTeacherService teacherService;

    //查询讲师所有数据
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher(){
        //调用service方法实现查询所有的操作
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }
    //逻辑删除讲师的方法
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        boolean flag = teacherService.removeById(id);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //分页查询
    //current 当前页,limit 每页记录数
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit){
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);

        try {
            int i = 10/0;
        } catch (Exception e) {
            //执行自定义异常
            throw new GuliException(20001,"执行了自定义异常处理");
        }

        /*
        调用方法实现分页，调用方法时，底层封装，把分页所有数据封装到pageTeacher对象里面
         */
        teacherService.page(pageTeacher,null);
        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords();//数据list集合
        return R.ok().data("total",total).data("rows",records);
    }
    //条件查询带分页的方法
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit,
                                  @RequestBody(required = false)TeacherQuery teacherQuery){
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);

        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String end = teacherQuery.getEnd();
        String begin = teacherQuery.getBegin();
        //判断条件是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(name)){
            //模糊查询
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            //等于
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            //大于等于 >=
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            //小于等于 <=
            wrapper.le("gmt_create",end);
        }
        //排序
        wrapper.orderByDesc("gmt_create");
        //调用方法实现条件查询分页
        teacherService.page(pageTeacher,wrapper);
        long total = pageTeacher.getTotal(); //总记录数
        List<EduTeacher> records = pageTeacher.getRecords(); // 数据list集合
        return R.ok().data("total",total).data("rows",records);
    }
    //添加讲师接口的方法
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }
    //根据讲师id进行查询
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id) {
        EduTeacher eduTeacher = teacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }
    //讲师修改功能
    @PostMapping("updateTeacher")
    public R updataTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = teacherService.updateById(eduTeacher);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }
    
}

