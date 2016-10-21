package com.hangeulbot.repository;

import com.hangeulbot.vo.HangeulbotWord;
import com.hangeulbot.vo.HangeulbotWordSemanticGrade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jyson on 2016. 9. 30..
 */
public interface HangeulbotWordSemanticGradeRepository extends JpaRepository<HangeulbotWordSemanticGrade,Integer> {
    HangeulbotWordSemanticGrade findByHangeulbotWord(HangeulbotWord hangeulbotWord);
}
