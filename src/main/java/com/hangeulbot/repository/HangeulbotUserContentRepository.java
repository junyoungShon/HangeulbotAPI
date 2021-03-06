package com.hangeulbot.repository;

import com.hangeulbot.vo.HangeulbotChild;
import com.hangeulbot.vo.HangeulbotUserContentLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jyson on 2016. 9. 30..
 */
public interface HangeulbotUserContentRepository extends JpaRepository<HangeulbotUserContentLog,Integer>{
    List<HangeulbotUserContentLog> findByHangeulbotChildOrderByStartDateAsc(HangeulbotChild hangeulbotChild);
    List<HangeulbotUserContentLog> findByHangeulbotChild(HangeulbotChild hangeulbotChild, Pageable pageable);

}
