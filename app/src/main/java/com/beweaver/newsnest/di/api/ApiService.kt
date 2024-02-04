package com.beweaver.newsnest.di.api

import com.beweaver.newsnest.datamodels.RssFeedResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {
    @GET("rssfeedstopstories.cms")
    fun getTopNews(): Observable<RssFeedResponse>

    @GET("rssfeeds/296589292.cms")
    fun getWorldNews(): Observable<RssFeedResponse>

    @GET("rssfeeds/72258322.cms")
    fun getUSNews(): Observable<RssFeedResponse>

    @GET("rssfeeds/-2128936835.cms")
    fun getIndiaNews(): Observable<RssFeedResponse>

    @GET("rssfeeds/1898055.cms")
    fun getBusinessNews(): Observable<RssFeedResponse>

    @GET("rssfeeds/4719148.cms")
    fun getSportsNews(): Observable<RssFeedResponse>

    @GET("rssfeeds/66949542.cms")
    fun getTechNews(): Observable<RssFeedResponse>

    @GET("rssfeeds/913168846.cms")
    fun getEducationNews(): Observable<RssFeedResponse>

    @GET("rssfeeds/1081479906.cms")
    fun getEntertainmentNews(): Observable<RssFeedResponse>
}
