package com.hangeulbot.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jyson on 2016. 7. 6..
 */
@Entity
@ToString(callSuper=true, includeFieldNames=true)
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

    @Column
    @Setter
    @Getter
    private String childPhoto;

    @Setter
    @Getter
    @Column(name="user_id")
    private String userId;

    public HangeulbotChild() {}


}
