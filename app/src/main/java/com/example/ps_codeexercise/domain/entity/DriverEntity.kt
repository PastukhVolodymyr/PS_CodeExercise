package com.example.ps_codeexercise.domain.entity


data class DriverEntity(val name: String) {

    companion object {
        fun undefinedDriver() = DriverEntity(":Undefined")
    }
}

