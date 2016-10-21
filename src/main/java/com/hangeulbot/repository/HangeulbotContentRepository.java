package com.hangeulbot.repository;

import com.hangeulbot.vo.HangeulbotChild;
import com.hangeulbot.vo.HangeulbotContent;
import com.hangeulbot.vo.HangeulbotUserContentLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jyson on 2016. 9. 27..
 */
public interface HangeulbotContentRepository extends JpaRepository<HangeulbotContent,String> {

}
