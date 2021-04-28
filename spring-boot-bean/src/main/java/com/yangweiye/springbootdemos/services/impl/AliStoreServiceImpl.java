package com.yangweiye.springbootdemos.services.impl;

import com.yangweiye.springbootdemos.services.StoreService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class AliStoreServiceImpl implements StoreService {
    @Override
    public void Save(String s) {
        System.out.println("使用阿里云存储 " + s);
    }
}
