package cn.yzaaa.eduservice.entity.chapterVo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Honglixi
 * @create 2021-05-01-9:26
 */
@Data
public class ChapterVo {
    private String id;

    private String title;

    //表示小节
    private List<VideoVo> children = new ArrayList<>();
}
