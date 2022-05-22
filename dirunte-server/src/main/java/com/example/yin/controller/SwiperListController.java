package com.example.yin.controller;

import com.example.yin.common.FatalMessage;
import com.example.yin.common.ErrorMessage;
import com.example.yin.common.SuccessMessage;
import com.example.yin.constant.Constants;
import com.example.yin.domain.SwiperList;
import com.example.yin.service.impl.SwiperListServiceImpl;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class SwiperListController {

    @Autowired
    private SwiperListServiceImpl swiperListService;

    @Configuration
    public static class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/swiperListPic/**")
                    .addResourceLocations(Constants.SWIPERLIST_PIC_PATH);
        }
    }

    // 添加歌单
    @ResponseBody
    @RequestMapping(value = "/swiperList/add", method = RequestMethod.POST)
    public Object addSwiperList(HttpServletRequest req) {
        String title = req.getParameter("title").trim();
        String introduction = req.getParameter("introduction").trim();
        String pic = "/img/swiperListPic/123.jpg";

        SwiperList swiperList = new SwiperList();
        swiperList.setTitle(title);
        swiperList.setIntroduction(introduction);
        swiperList.setPic(pic);

        boolean res = swiperListService.addSwiperList(swiperList);
        if (res) {
            return new SuccessMessage<Null>("添加成功").getMessage();
        } else {
            return new ErrorMessage("添加失败").getMessage();
        }
    }

    // 删除歌单
    @RequestMapping(value = "/swiperList/delete", method = RequestMethod.GET)
    public Object deleteSwiperList(HttpServletRequest req) {
        String id = req.getParameter("id");

        boolean res = swiperListService.deleteSwiperList(Integer.parseInt(id));
        if (res) {
            return new SuccessMessage<Null>("删除成功").getMessage();
        } else {
            return new ErrorMessage("删除失败").getMessage();
        }
    }

    // 返回所有歌单
    @RequestMapping(value = "/swiperList", method = RequestMethod.GET)
    public Object allSwiperList() {
        return new SuccessMessage<List<SwiperList>>(null, swiperListService.allSwiperList()).getMessage();
    }

    // 返回标题包含文字的歌单
    @RequestMapping(value = "/swiperList/likeTitle/detail", method = RequestMethod.GET)
    public Object swiperListOfLikeTitle(HttpServletRequest req) {
        String title = req.getParameter("title").trim();

        return new SuccessMessage<List<SwiperList>>(null, swiperListService.likeTitle('%' + title + '%')).getMessage();
    }

    // 更新歌单信息
    @ResponseBody
    @RequestMapping(value = "/swiperList/update", method = RequestMethod.POST)
    public Object updateSwiperListMsg(HttpServletRequest req) {
        String id = req.getParameter("id").trim();
        String title = req.getParameter("title").trim();
        String introduction = req.getParameter("introduction").trim();

        SwiperList swiperList = new SwiperList();
        swiperList.setId(Integer.parseInt(id));
        swiperList.setTitle(title);
        swiperList.setIntroduction(introduction);

        boolean res = swiperListService.updateSwiperListMsg(swiperList);
        if (res) {
            return new SuccessMessage<Null>("修改成功").getMessage();
        } else {
            return new ErrorMessage("修改失败").getMessage();
        }
    }

    // 更新歌单图片
    @ResponseBody
    @RequestMapping(value = "/swiperList/img/update", method = RequestMethod.POST)
    public Object updateSwiperListPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "swiperListPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String imgPath = "/img/swiperListPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            SwiperList swiperList = new SwiperList();
            swiperList.setId(id);
            swiperList.setPic(imgPath);

            boolean res = swiperListService.updateSwiperListImg(swiperList);
            if (res) {
                return new SuccessMessage<String>("上传成功", imgPath).getMessage();
            } else {
                return new ErrorMessage("上传失败").getMessage();
            }
        } catch (IOException e) {
            return new FatalMessage("上传失败" + e.getMessage()).getMessage();
        }
    }
}
