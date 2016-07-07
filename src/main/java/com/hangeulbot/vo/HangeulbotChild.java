package com.hangeulbot.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jyson on 2016. 7. 6..
 */
@Entity
public class HangeulbotChild {



    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private int idx;

    @Column(name="child_name")
    @Setter
    @Getter
    private String childName;

    @Column(name="child_birth")
    @Setter
    @Getter
    private Date childBirth;

    @Column(name="child_num")
    @Setter
    @Getter
    private int childNum;

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id")
    @Setter
    @Getter
    private HangeulbotUser hangeulbotUser;

    public HangeulbotChild() {}


}
