package com.hangeulbot.repository;

import com.hangeulbot.vo.HangeulbotChild;
import com.hangeulbot.vo.HangeulbotChoPhoenix;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jyson on 2016. 7. 15..
 */
public interface HangeulbotChoPhoenixRepository extends JpaRepository<HangeulbotChoPhoenix,Integer> {
    HangeulbotChoPhoenix findByChoSungAndHangeulbotChild(char cho, HangeulbotChild hangeulbotchild);
}
