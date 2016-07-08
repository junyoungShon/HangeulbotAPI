package com.hangeulbot.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;


/**
 * Created by jyson on 2016. 7. 6..
 */
@Entity
@ToString(callSuper=true, includeFieldNames=true)
public class HangeulbotDevice {


    @Column(name="idx")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private int idx;

    @Id
    @Column(name="device_id")
    @Setter
    @Getter
    private String deviceId;

    @Column(name="device_name")
    @Setter
    @Getter
    private String deviceName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    @Setter
    @Getter
    private HangeulbotUser hangeulbotUser;

    public HangeulbotDevice() {}



}
