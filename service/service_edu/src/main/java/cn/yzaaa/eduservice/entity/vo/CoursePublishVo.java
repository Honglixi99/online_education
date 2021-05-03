package cn.yzaaa.eduservice.entity.vo;

import lombok.Data;

/**
 * @author Honglixi
 * @create 2021-05-02-13:18
 */
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
