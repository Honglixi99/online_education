package cn.yzaaa.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Honglixi
 * @create 2021-04-29-14:02
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    //一个一级分类有多个二级分类
    private List<TwoSubject> children = new ArrayList<>();
}
