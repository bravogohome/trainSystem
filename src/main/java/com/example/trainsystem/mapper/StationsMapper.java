package com.example.trainsystem.mapper;

import com.example.trainsystem.entity.Stations;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
public interface StationsMapper extends BaseMapper<Stations> {
    @Select("SELECT * FROM stations WHERE stationid = #{stationid} ")
    List<Stations> selectStationByStationid(@Param("stationid")int stationid);

    @Select("SELECT stationid FROM stations WHERE stationname=#{stationname}")
    List<Integer> selectStationidByStationname(@Param("stationname")String stationname);

    @Select("select * from stations")
    List<Stations> selectAllStations();

    @Select("SELECT * FROM stations where stationname=#{stationname}")
    List<Stations> selectStationByStationname(@Param("stationname")String stationname);

    @Update("UPDATE stations SET traincode1=#{traincode1},traincode2=#{traincode2} where stationid=#{stationid}")
    int updateTraincode(@Param("traincode1")int traincode1,@Param("traincode2")int traincode2,@Param("stationid")int stationid);
}
