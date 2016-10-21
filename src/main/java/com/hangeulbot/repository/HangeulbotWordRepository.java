package com.hangeulbot.repository;

import com.hangeulbot.vo.HangeulbotWord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jyson on 2016. 9. 30..
 */
public interface HangeulbotWordRepository extends JpaRepository<HangeulbotWord,Integer> {
}
