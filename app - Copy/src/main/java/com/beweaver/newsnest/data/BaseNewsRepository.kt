package com.beweaver.newsnest.data

import com.beweaver.newsnest.datamodels.RssFeedResponse
import io.reactivex.Observable

interface BaseNewsRepository {

    fun getTopNews(): Observable<RssFeedResponse>

    fun getWorldNews(): Observable<RssFeedResponse>

    fun getUSNews(): Observable<RssFeedResponse>

    fun getIndiaNews(): Observable<RssFeedResponse>

    fun getBusinessNews(): Observable<RssFeedResponse>

    fun getSportsNews(): Observable<RssFeedResponse>

    fun getTechNews(): Observable<RssFeedResponse>

    fun getEducationNews(): Observable<RssFeedResponse>

    fun getEntertainmentNews(): Observable<RssFeedResponse>
}