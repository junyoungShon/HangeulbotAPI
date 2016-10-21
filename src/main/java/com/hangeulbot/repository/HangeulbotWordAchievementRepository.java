package com.hangeulbot.repository;

import com.hangeulbot.vo.HangeulbotChild;
import com.hangeulbot.vo.HangeulbotWord;
import com.hangeulbot.vo.HangeulbotWordAchievement;
import com.hangeulbot.vo.HangeulbotWordGrade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jyson on 2016. 9. 30..
 */
public interface HangeulbotWordAchievementRepository extends JpaRepository<HangeulbotWordAchievement,Integer> {
    HangeulbotWordAchievement findByHangeulbotChildAndHangeulbotWord(HangeulbotChild hangeulbotChild, HangeulbotWord hangeulbotWord);

    List<HangeulbotWordAchievement> getByHangeulbotChild(HangeulbotChild hangeulbotChild);
}
