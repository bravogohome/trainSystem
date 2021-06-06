package com.example.trainsystem.entity;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 
    * </p>
*
* @author gohome
* @since 2021-06-06
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class Trains implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "trainid", type = IdType.AUTO)
    private Integer trainid;

    private String trainnum;


}
