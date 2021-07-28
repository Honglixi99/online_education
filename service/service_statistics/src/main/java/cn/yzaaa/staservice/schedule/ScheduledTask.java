package cn.yzaaa.staservice.schedule;

import cn.yzaaa.staservice.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import utils.DateUtil;

import java.util.Date;

/**
 * @author Honglixi
 * @create 2021-07-25 16:44
 */
@Component
public class ScheduledTask {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @Scheduled(cron = "0/5 * * * * ?") //每隔5秒执行一次这个方法
    public void task1() {
//        System.out.println("task1执行了。。。");
    }

    /**
     * 每天凌晨1点把前一天数据进行查询添加
     */
    @Scheduled(cron = "0 0 1 * * ? ")
    public void task2() {
//        System.out.println("task1执行了。。。");
        statisticsDailyService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}
