package com.example.ps_codeexercise.data.source

import com.example.ps_codeexercise.R
import com.example.ps_codeexercise.data.dto.RoutesDataDTO
import com.example.ps_codeexercise.data.utils.JsonRawResource
import com.example.ps_codeexercise.data.utils.fromJson
import com.google.gson.Gson

class DriversFileDataSource(private val jsonRawResource: JsonRawResource, private val gson: Gson) :
    DriversDataSource {

    override fun getDriversData(): List<String>? = jsonRawResource.decodeToString(
        R.raw.routes
    )?.let { gson.fromJson<RoutesDataDTO?>(it)?.drivers }
}