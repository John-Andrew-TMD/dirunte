package com.example.yin.service.impl;

import com.example.yin.dao.SwiperListMapper;
import com.example.yin.domain.SwiperList;
import com.example.yin.service.SwiperListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwiperListServiceImpl implements SwiperListService {

    @Autowired
    private SwiperListMapper swiperListMapper;

    @Override
    public boolean updateSwiperListMsg(SwiperList swiperList) {
        return swiperListMapper.updateSwiperListMsg(swiperList) >0 ?true:false;
    }

    @Override
    public boolean deleteSwiperList(Integer id) {
        return swiperListMapper.deleteSwiperList(id) >0 ?true:false;
    }

    @Override
    public List<SwiperList> allSwiperList()
    {
        return swiperListMapper.allSwiperList();
    }

    @Override
    public List<SwiperList> likeTitle(String title)
    {
        return swiperListMapper.likeTitle(title);
    }

    @Override
    public List<SwiperList> likeStyle(String style)
    {
        return swiperListMapper.likeStyle(style);
    }

    @Override
    public boolean addSwiperList(SwiperList swiperList)
    {
        return swiperListMapper.insertSelective(swiperList) > 0?true:false;
    }

    @Override
    public boolean updateSwiperListImg(SwiperList swiperList) {

        return swiperListMapper.updateSwiperListImg(swiperList) >0 ?true:false;
    }
}
