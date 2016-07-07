package com.hangeulbot.service;

import org.springframework.stereotype.Service;

/**
 * Created by jyson on 2016. 7. 6..
 */
@Service
public interface APIService {
    public boolean isDuplicatedByEmailId(String memberId);
}
