package com.hangeulbot.dao;

import com.hangeulbot.vo.HangeulbotUser;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by jyson on 2016. 7. 6..
 */

public interface HangeulbotUserRepository extends JpaRepository<HangeulbotUser, Integer> {
    HangeulbotUser findByUserId(String memberId);
}
