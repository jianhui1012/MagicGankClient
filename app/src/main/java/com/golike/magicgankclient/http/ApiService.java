package com.golike.magicgankclient.http;


import com.golike.magicgankclient.model.SearchData;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 *  api接口
 * @author golike
 * @date 2018/2/2
 */

public interface ApiService  {

    /***
     * http://gank.io/api/search/query/listview/category/Android/count/10/page/1
     * @param type
     * @param count
     * @param page
     * @return
     */
    @GET("search/query/listview/category/{type}/count/{count}/page/{page}")
    Observable<SearchData> getSearchData(@Path("type") String type, @Path("count") int count, @Path("page") int page);

    /***
     * http://gank.io/api/data/数据类型/请求个数/第几页
     * @param type
     * @param count
     * @param page
     * @return
     */
    @GET("data/{type}/{count}/{page}")
    Observable<SearchData> getHomeData(@Path("type") String type, @Path("count") int count, @Path("page") int page);

    /***
     * http://gank.io/api/history/content/请求个数/第几页
     * 获取某几日干货网站数据
     * @param count
     * @param page
     * @return
     */
    @GET("history/content/{count}/{page}")
    Observable<SearchData> getHistoryData(@Path("count") int count, @Path("page") int page);

    /***
     * http://gank.io/api/history/content/day/2016/05/11
     * 获取特定日期网站数据
     * @param date
     * @return
     */
    @GET("history/content/day/{date}")
    Observable<SearchData> getHistoryDataByDate(@Path("date") String date);

    /***
     * https://gank.io/api/add2gank
     * 提交干货到审核区
     * todo 创建实体
     * @param url 想要提交的网页地址
     * @param desc 对干货内容的描述	单
     * @param who  提交者 ID
     * @param type  干货类型
     * @return
     */
    @FormUrlEncoded
    @POST("add2gank")
    Observable<SearchData> add2Gank(@Field("url") String url,@Field("desc") String desc,@Field("who") String who,@Field("type") String type);

    /***
     * http://gank.io/api/random/data/Android/20
     * @param type 数据类型：福利 | Android | iOS | 休息视频 | 拓展资源 | 前端
     * @param count 个数： 数字，大于0
     * @return
     */
    @GET("random/data/{type}/{count}")
    Observable<SearchData> getRandomData(@Path("type") String type,@Path("count") int count);
}
