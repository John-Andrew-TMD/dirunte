package com.example.yin.dao;

import com.example.yin.domain.SwiperList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface SwiperListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SwiperList record);

    int insertSelective(SwiperList record);

    SwiperList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SwiperList record);

    int updateByPrimaryKeyWithBLOBs(SwiperList record);

    int updateByPrimaryKey(SwiperList record);

    int updateSwiperListMsg(SwiperList record);

    int updateSwiperListImg(SwiperList record);

    int deleteSwiperList(Integer id);

    List<SwiperList> allSwiperList();

    List<SwiperList> likeTitle(String title);

    List<SwiperList> likeStyle(String style);
}
