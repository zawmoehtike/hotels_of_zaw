package com.zawmoehtike.hotelcodetest.common.exception

interface UnidirectionalMap<F, T> {
    fun map(item: F): T
}