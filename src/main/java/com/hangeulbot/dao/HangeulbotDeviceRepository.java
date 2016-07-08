package com.hangeulbot.dao;

import com.hangeulbot.vo.HangeulbotDevice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jyson on 2016. 7. 8..
 */
public interface HangeulbotDeviceRepository extends JpaRepository<HangeulbotDevice,String>{
}
