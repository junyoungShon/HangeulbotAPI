package com.hangeulbot.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by jyson on 2016. 7. 6..
 */
@Entity
public class HangeulbotUser {


    @Column(name="idx")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private int idx;

    @Id
    @Column(name="user_id")
    @Setter
    @Getter
    private String userId;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "hangeulbotUser")
    @Setter
    @Getter
    private List<HangeulbotDevice> hangeulbotDevices;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "hangeulbotUser")
    @Setter
    @Getter
    private List<HangeulbotChild> hangeulbotChildren;

    public HangeulbotUser() {}



}
