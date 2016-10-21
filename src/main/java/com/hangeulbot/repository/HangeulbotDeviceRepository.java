package com.hangeulbot.repository;

import com.hangeulbot.vo.HangeulbotDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by jyson on 2016. 7. 8..
 */

public interface HangeulbotDeviceRepository extends JpaRepository<HangeulbotDevice,String>{
    HangeulbotDevice findByDeviceAddress(String deviceId);
}
