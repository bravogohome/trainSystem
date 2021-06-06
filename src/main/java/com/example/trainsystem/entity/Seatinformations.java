package com.example.trainsystem.entity;

    import com.baomidou.mybatisplus.annotation.IdType;
    import java.time.LocalDate;
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
    public class Seatinformations implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer trainid;

    private LocalDate traindate;

    private Integer hardseatnumber;

    private Double hardseatprice;

    private Integer hardseatremain;

    private Integer hardbednumber;

    private Double hardbedprice;

    private Integer hardbedremain;

    private Integer softbednumber;

    private Double softbedprice;

    private Integer softbedremain;

    private Integer secondseatnumber;

    private Double secondseatprice;

    private Integer secondseatremain;

    private Integer firstseatnumber;

    private Double firstseatprice;

    private Integer firstseatremain;

    private Integer businessseatnumber;

    private Double businessseatprice;

    private Integer businessseatremain;


}
