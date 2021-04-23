package cn.yzaaa.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Honglixi
 * @create 2021-04-23-15:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException{

    private Integer code;//状态码
    private String msg;//异常信息
}
