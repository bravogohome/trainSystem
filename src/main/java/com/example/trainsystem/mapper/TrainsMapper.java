package com.example.trainsystem.mapper;

import com.example.trainsystem.entity.Trains;
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
public interface TrainsMapper extends BaseMapper<Trains> {
    @Select("SELECT * FROM trains WHERE trainid=#{trainid}")
    List<Trains> findTrainByTrainid(@Param("trainid")int trainid);
    @Select("SELECT * FROM trains WHERE trainnum=#{trainnum}")
    List<Trains> findTrainByTrainnum(@Param("trainnum")String trainnum);
}
