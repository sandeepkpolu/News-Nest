package com.beweaver.newsnest.data

import com.beweaver.newsnest.datamodels.RssFeedResponse
import com.beweaver.newsnest.di.api.ApiService
import io.reactivex.Observable
import javax.inject.Inject

class NewsRepository(private val apiService: ApiService) : BaseNewsRepository {
    override fun getTopNews(): Observable<RssFeedResponse> {
        return apiService.getTopNews()
    }

    override fun getWorldNews(): Observable<RssFeedResponse> {
        return apiService.getWorldNews()
    }

    override fun getUSNews(): Observable<RssFeedResponse> {
        return apiService.getUSNews()
    }

    override fun getIndiaNews(): Observable<RssFeedResponse> {
        return apiService.getIndiaNews()
    }

    override fun getBusinessNews(): Observable<RssFeedResponse> {
        return apiService.getBusinessNews()
    }

    override fun getSportsNews(): Observable<RssFeedResponse> {
        return apiService.getSportsNews()
    }

    override fun getTechNews(): Observable<RssFeedResponse> {
        return apiService.getTechNews()
    }

    override fun getEducationNews(): Observable<RssFeedResponse> {
        return apiService.getEducationNews()
    }

    override fun getEntertainmentNews(): Observable<RssFeedResponse> {
        return apiService.getEntertainmentNews()
    }
}
