package com.hangeulbot.repository;

import com.hangeulbot.vo.HangeulbotChild;
import com.hangeulbot.vo.HangeulbotJongPhoenix;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jyson on 2016. 7. 15..
 */
public interface HangeulbotJongPhoenixRepository extends JpaRepository<HangeulbotJongPhoenix,Integer> {
    HangeulbotJongPhoenix findByJongSungAndHangeulbotChild(char jon, HangeulbotChild hangeulbotchild);
}
