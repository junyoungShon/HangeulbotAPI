package com.hangeulbot.repository;

import com.hangeulbot.vo.HangeulbotChild;
import com.hangeulbot.vo.HangeulbotUserWordLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jyson on 2016. 9. 28..
 */
public interface HangeulbotUserWordLogRepository extends JpaRepository<HangeulbotUserWordLog, String> {
    List<HangeulbotUserWordLog> findByHangeulbotChildOrderByStartDateAsc(HangeulbotChild hangeulbotChild);
    List<HangeulbotUserWordLog> findByHangeulbotChild(HangeulbotChild hangeulbotChild, Pageable pageable);
}
