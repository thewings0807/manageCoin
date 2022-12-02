package com.demo.coin.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "coin")
@Getter
@Setter
public class Coin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code",columnDefinition = "VARCHAR(50) COMMENT '幣別代號'")
    private String code;

    @Column(name = "name",columnDefinition = "VARCHAR(50) COMMENT '幣別中文名稱'")
    private String name;

    @Column(name = "symbol",columnDefinition = "VARCHAR(50) COMMENT '符號'")
    private String symbol;

    @Column(name = "rate",columnDefinition = "VARCHAR(50) DEFAULT '0.00' COMMENT '匯率'")
    private String rate;

    @Column(name = "description",columnDefinition = "VARCHAR(255) COMMENT '說明'")
    private String description;

    @Column(name = "rate_float",columnDefinition = "DOUBLE DEFAULT 0.00 COMMENT '匯率浮點數'")
    private double rateFloat;

    @Column(name = "update_time",columnDefinition = "DATETIME COMMENT '更新時間'")
    private LocalDateTime updateTime;
}
