package com.hangeulbot.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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


    @OneToOne
    @Setter
    @Getter
    @JoinColumn(name="user_id")
    private HangeulbotUser hangeulbotUser;

    public HangeulbotDevice() {}



}
