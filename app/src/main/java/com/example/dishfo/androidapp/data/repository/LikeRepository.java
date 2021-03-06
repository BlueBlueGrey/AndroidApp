package com.example.dishfo.androidapp.data.repository;

import com.example.dishfo.androidapp.data.DataAcess.LikeAcess;
import com.example.dishfo.androidapp.application.MyApplication;
import com.example.dishfo.androidapp.data.message.DataBaseDao;
import com.example.dishfo.androidapp.bean.sqlBean.Like;
import com.example.dishfo.androidapp.bean.sqlBean.Note;
import com.example.dishfo.androidapp.bean.sqlBean.User;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by dishfo on 18-3-20.
 */
public class LikeRepository {
    @Inject
    DataBaseDao dataBaseDao;
    @Inject
    LikeAcess likeAcess;

    public LikeRepository(){
        MyApplication.getComponent().inject(this);
    }

    public Like getLike(User user, Note note) throws IOException {
        Like like=dataBaseDao.getLike(user.getEmail(),note.getId(),note.getAreaId());
        if(like==null){
            like=likeAcess.getLike(user.getEmail(),note.getId(),note.getAreaId());
            if(like!=null){

                return like;
            }
        }
        return like;
    }

    public List<Like> getLikeByUser(String email) throws IOException {
        List<Like> likes=dataBaseDao.getLikeByUser(email);
        if(likes==null){
            likes=likeAcess.getLikeByUser(email);
        }
        return likes;
    }

    public Like saveLike(Like like) throws IOException {
        Like res=likeAcess.saveLike(like);
        dataBaseDao.insertLike(res);
        return res;
    }

    public void deleteLike(Like like) throws IOException {
        dataBaseDao.deleteLike(like);
        likeAcess.deleteLike(like);
    }

    public void refreshData(){

    }
}
