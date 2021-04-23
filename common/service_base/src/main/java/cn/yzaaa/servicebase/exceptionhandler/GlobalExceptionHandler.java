package cn.yzaaa.servicebase.exceptionhandler;


import cn.yzaaa.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/** 异常处理
 * @author Honglixi
 * @create 2021-04-23-10:45
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了异常处理...");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了返回数据
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理...");
    }
    //自定义异常处理
    @ExceptionHandler(GuliException.class)
    @ResponseBody //为了返回数据
    public R error(GuliException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
