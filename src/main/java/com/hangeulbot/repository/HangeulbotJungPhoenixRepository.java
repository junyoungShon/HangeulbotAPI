package com.hangeulbot.repository;

import com.hangeulbot.vo.HangeulbotChild;
import com.hangeulbot.vo.HangeulbotChoPhoenix;
import com.hangeulbot.vo.HangeulbotJungPhoenix;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jyson on 2016. 7. 15..
 */
public interface HangeulbotJungPhoenixRepository extends JpaRepository<HangeulbotJungPhoenix,Integer> {
    HangeulbotJungPhoenix findByJungSungAndHangeulbotChild(char jun, HangeulbotChild hangeulbotchild);
}
