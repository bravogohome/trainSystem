package com.example.trainsystem.mapper;

import com.example.trainsystem.entity.Seatinformations;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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
public interface SeatinformationsMapper extends BaseMapper<Seatinformations> {
    @Select("SELECT * FROM seatinformations WHERE trainid=#{trainid} AND tranidate=#{traindate}")
    List<Seatinformations> selectSeatinformationByTrainidAndTraindate(@Param("trainid")Integer trainid,@Param("traindate") String traindate);
}
