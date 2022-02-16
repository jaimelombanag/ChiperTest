package com.chiper.test



object ImageUrlBuilder {
    fun getUrl(path: String?, size: String = "w500"): String {
        if(path==null || path.isEmpty()) return ""

        return "${Constants.IMAGE_URL}$size$path"
    }
}