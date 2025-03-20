package com.project.giunne.common.data.remote.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PutInventoryItemRequest(
    @SerialName("itemidList")
    val itemidList: List<Long>
)