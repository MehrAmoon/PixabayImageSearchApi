package com.amoon.apisearchimage.network

import com.amoon.apisearchimage.network.response.ImagesResponse
import com.amoon.apisearchimage.util.Consts.Companion.API_URL
import com.amoon.apisearchimage.util.Consts.Companion.KEY_TAG
import com.amoon.apisearchimage.util.Consts.Companion.PAGE_TAG
import com.amoon.apisearchimage.util.Consts.Companion.PER_PAGE_TAG
import com.amoon.apisearchimage.util.Consts.Companion.QUERY_TAG
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET(API_URL)
    suspend fun search(
        @Query(KEY_TAG) key: String,
        @Query(QUERY_TAG) query: String,
        @Query(PAGE_TAG) page: Int,
        @Query(PER_PAGE_TAG) perPage: Int
    ): ImagesResponse

}