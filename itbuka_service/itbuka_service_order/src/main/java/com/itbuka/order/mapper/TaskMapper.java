package com.itbuka.order.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itbuka.order.domain.Task;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 王庚润
* @description 针对表【tb_task】的数据库操作Mapper
* @createDate 2024-08-14 10:29:20
* @Entity com.itbuka.order.domain.Task
*/
@Mapper
public interface TaskMapper extends BaseMapper<Task> {

}




