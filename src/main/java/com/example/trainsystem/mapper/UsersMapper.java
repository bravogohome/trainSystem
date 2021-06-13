package com.example.trainsystem.mapper;

import com.example.trainsystem.entity.Users;
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
public interface UsersMapper extends BaseMapper<Users> {
    @Select("SELECT * FROM users WHERE username=#{username}")
    List<Users> selectUserByUsername(@Param("username")String username);
    @Select("SELECT * FROM users WHERE userphone=#{userphone}")
    List<Users> selectUserByUserphone(@Param("userphone")String userphone);
    @Select("SELECT * FROM users WHERE useridcard=#{useridcard}")
    List<Users> selectUserByUseridcard(@Param("useridcard")String useridcard);
}
