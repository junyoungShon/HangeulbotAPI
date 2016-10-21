package com.hangeulbot.repository;

import com.hangeulbot.vo.HangeulbotUser;
import org.springframework.data.jpa.repository.JpaRepository;

import static javafx.scene.input.KeyCode.T;


/**
 * Created by jyson on 2016. 7. 6..
 */

public interface HangeulbotUserRepository extends JpaRepository<HangeulbotUser, String> {
    HangeulbotUser findByUserId(String userId);


}
