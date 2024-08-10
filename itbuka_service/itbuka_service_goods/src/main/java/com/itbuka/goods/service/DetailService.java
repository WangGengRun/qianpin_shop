package com.itbuka.goods.service;

import com.itbuka.goods.domain.Detail;
import org.springframework.stereotype.Service;

@Service
public interface DetailService {
    Detail findDetail(Long id);
}
