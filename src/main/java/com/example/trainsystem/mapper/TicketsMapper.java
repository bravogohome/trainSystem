package com.example.trainsystem.mapper;

import com.example.trainsystem.entity.Tickets;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gohome
 * @since 2021-06-06
 */
@Repository
public interface TicketsMapper extends BaseMapper<Tickets> {
    @Select("SELECT * FROM tickets WHERE userid=#{userid}")
    List<Tickets> selectTicketByUserid(@Param("userid")Integer userid);
}
