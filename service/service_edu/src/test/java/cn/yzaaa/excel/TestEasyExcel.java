package cn.yzaaa.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Honglixi
 * @create 2021-04-28-18:50
 */
public class TestEasyExcel {
    public static void main(String[] args) {
        //实现excel写的操作
        //1.设置写入文件夹地址和excel文件1名称
//        String filename = "E:\\write.xlsx";
//        //2.调用easyexcel里面的方法实现写操作
//        EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(getData());
        //实现excel读操作
        String filename = "E:\\write.xlsx";
        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();

    }
    //创建方法返回list集合
    private static List<DemoData> getData(){
        ArrayList<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("lucy" + i);
            list.add(data);
        }
        return list;
    }
}
