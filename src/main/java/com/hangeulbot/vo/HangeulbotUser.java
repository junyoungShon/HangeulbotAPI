package com.hangeulbot.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    @Column(name="user_id",length = 30)
    @Setter
    @Getter
    private String userId;

    @Column(name="password",nullable = false)
    @Setter
    @Getter
    private String password;


    @Column(name="phone_number",length = 20,nullable = false)
    @Setter
    @Getter
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @Setter
    @Getter
    @JoinColumn(name = "device_id")
    private HangeulbotDevice hangeulbotDevice;

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @Setter
    @Getter
    @JoinColumn(name="user_id")
    private List<HangeulbotChild> hangeulbotChildren;


    public HangeulbotUser() {}



}
