package com.example.trainsystem.entity;

    import com.baomidou.mybatisplus.annotation.IdType;
    import java.time.LocalDate;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.time.LocalDateTime;
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
    public class Tickets implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "ticketid", type = IdType.AUTO)
    private Integer ticketid;

    private Integer userid;

    private String tickettype;

    private String trainnum;

    private String seattype;

    private String seatcode;

    private Double ticketprice;

    private Integer startstationid;

    private Integer endstationid;

    private LocalDate ticketdate;

    private LocalDateTime purchasetime;

    private String purchasemode;


}
