package com.yangweiye.springbootdemos.services.impl;

import com.yangweiye.springbootdemos.services.StoreService;
import org.springframework.stereotype.Service;

@Service
public class TencentStoreServiceImpl implements StoreService {
    @Override
    public void Save(String s) {
        System.out.println("使用腾讯云存储 " + s);
    }
}
