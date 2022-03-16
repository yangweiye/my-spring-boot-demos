package com.yangweiye.springbootdemos.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangweiye.springbootdemos.entity.Link;
import com.yangweiye.springbootdemos.mapper.LinkMapper;
import com.yangweiye.springbootdemos.service.ILinkService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yangweiye
 * @since 2022-01-13
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements ILinkService {

}
