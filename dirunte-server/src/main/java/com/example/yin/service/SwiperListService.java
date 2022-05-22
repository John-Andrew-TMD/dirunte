package com.example.yin.service;

import com.example.yin.domain.SwiperList;

import java.util.List;

public interface SwiperListService {

    boolean addSwiperList (SwiperList swiperList);

    boolean updateSwiperListMsg(SwiperList swiperList);

    boolean updateSwiperListImg(SwiperList swiperList);

    boolean deleteSwiperList(Integer id);

    List<SwiperList> allSwiperList();

    List<SwiperList> likeTitle(String title);

    List<SwiperList> likeStyle(String style);
}
