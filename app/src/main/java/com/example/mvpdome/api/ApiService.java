package com.example.mvpdome.api;

import com.example.mvpdome.bean.WallPaperResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;


/**
 * ApiService接口 统一管理应用所有的接口
 * @author llw
 */
public interface ApiService {

    /**
     * 获取热门壁纸列表
     * http://service.picasso.adesk.com/v1/vertical/vertical?limit=30&skip=180&adult=false&first=0&order=hot
     */
    @GET("/v1/vertical/vertical?limit=30&skip=180&adult=false&first=0&order=hot")
    Observable<WallPaperResponse> getWallPaper();
    //Observable<BaseMsg<WallPaperResponse>> getWallPaper();
}
