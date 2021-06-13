package com.example.trainsystem.mapper;

import com.example.trainsystem.entity.Routes;
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
public interface RoutesMapper extends BaseMapper<Routes> {
    @Select("SELECT routenum FROM routes WHERE trainid=#{trainid} AND staystationid=#{stationid}")
    List<Integer> selectRoutenumByTrainidAndStationid(@Param("trainid")Integer trainid,@Param("stationid")int stationid);

    @Select("SELECT * FROM routes where trainid=#{trainid}")
    List<Routes> selectRouteByTrainid(@Param("trainid")int trainid);

    @Select("SELECT routenum FROM routes where trainid=#{trainid} ORDER BY routenum DESC LIMIT 1")
    List<Integer> findlast(@Param("trainid")int trainid);
}
