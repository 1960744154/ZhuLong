package com.lhr.frame;


import com.lhr.data.BaseInfo;
import com.lhr.data.EssBean;
import com.lhr.data.InformationBean;
import com.lhr.data.LiveListBean;
import com.lhr.data.LoginInfo;
import com.lhr.data.MainAdEntity;
import com.lhr.data.NewbestBean;
import com.lhr.data.PersonHeader;
import com.lhr.data.SpecialtyChooseEntity;
import com.lhr.data.TestInfo;
import com.lhr.data.TrainningBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


public interface IService {
    @GET(".")
    Observable<TestInfo> getTestData(@QueryMap Map<String, Object> params, @Query("page_id") int pageId);


    @GET("ad/getAd")
    Observable<BaseInfo<MainAdEntity>> getAdvert(@QueryMap Map<String, Object> pMap);

    @GET("lesson/getAllspecialty")
    Observable<BaseInfo<List<SpecialtyChooseEntity>>> getSubjectList();

    @GET("loginByMobileCode")
    Observable<BaseInfo<String>> getLoginVerify(@Query("mobile") String mobile);

    @GET("loginByMobileCode")
    Observable<BaseInfo<LoginInfo>> loginByVerify(@QueryMap Map<String, Object> params);

    @POST("getUserHeaderForMobile")
    @FormUrlEncoded
    Observable<BaseInfo<PersonHeader>> getHeaderInfo(@FieldMap Map<String, Object> params);


    //训练营
    //https://edu.zhulong.com/openapi/lesson/getLessonListForApi
    // ?page=1&course_type=3&limit=15&specialty_id=6&uid=15063681&time=1591366329&devices=oppoR11&system=android,
    // 5.1.1&version=2.1.4&unique_id=355757265852349&client_id=205
    @GET("lesson/getLessonListForApi")
    Observable<TrainningBean>getTrainInfo(@QueryMap Map<String, Object> params);

    //资料
//    https://bbs.zhulong.com/openapi/group/getGroupList
//            ?page=1&type=1&fid=29&uid=15063681&time=1591368576&devices=oppoR11&
//    system=android,5.1.1&version=2.1.4&unique_id=355757265852349&client_id=205
    @GET("openapi/group/getGroupList")
    Observable<InformationBean>getInformation(@QueryMap Map<String, Object> params);

    //最新精华
   // https://bbs.zhulong.com/openapi/group/getThreadEssence
    // ?page=1&fid=29&uid=15063681&time=1591368576
    // &devices=oppoR11&system=android,5.1.1&version=2.1.4
    // &unique_id=355757265852349&client_id=205
    @GET("openapi/group/getThreadEssence")
    Observable<NewbestBean>getnewbest(@QueryMap Map<String, Object> params);



    @POST("lesson/getIndexCommend")
    @FormUrlEncoded
    Observable<BaseInfo<List<LiveListBean>>> getMainPageList(@FieldMap Map<String,Object> params);

    @POST("group/getThreadEssence")
    @FormUrlEncoded
    Observable<BaseInfo<List<EssBean>>> getEss(@FieldMap Map<String,Object> params);

}
