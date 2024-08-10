package com.itbuka.canal.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;

@CanalEventListener //声明当前的类是canal的监听类
public class BusinessListener {
    /**
     *
     * @param eventType 当前操作数据库的类型
     * @param rowData 当前操作数据库的数据
     */
    @ListenPoint(schema = "b0394",table = "ad")
    public void adUpdate(CanalEntry.EventType eventType, CanalEntry.RowData rowData){
        System.out.println("广告表数据发生改变");
        //获取改变之前的数据
        rowData.getBeforeColumnsList().forEach((c)-> System.out.println("改变前的数据:"+c.getName()+"::"+c.getValue()));

        //获取改变之后的数据
        rowData.getAfterColumnsList().forEach((c)-> System.out.println("改变之后的数据:"+c.getName()+"::"+c.getValue()));
    }
}

