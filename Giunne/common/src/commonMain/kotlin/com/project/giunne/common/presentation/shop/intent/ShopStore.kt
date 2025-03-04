package com.project.giunne.common.presentation.shop.intent

import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.remote.request.PutInventoryItemRequest
import com.project.giunne.common.data.remote.response.Item
import com.project.giunne.common.data.util.DefineUrl.IMAGE_BASE_URL
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.avatar.GetUserAvatarListUseCase
import com.project.giunne.common.domain.usecase.mypage.GetInventoryItemListUseCase
import com.project.giunne.common.domain.usecase.mypage.PutInventoryItemUseCase
import com.project.giunne.common.domain.usecase.shop.GetCategoryItemListUseCase
import com.project.giunne.common.domain.usecase.shop.GetCategoryMapUseCase
import com.project.giunne.common.presentation.shop.state.CharacterState
import com.project.giunne.common.presentation.shop.state.ShopEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

class ShopStore(
    private val getCategoryMapUseCase: GetCategoryMapUseCase = KoinJavaComponent.get(GetCategoryMapUseCase::class.java),
    private val getCategoryItemListUseCase: GetCategoryItemListUseCase = KoinJavaComponent.get(GetCategoryItemListUseCase::class.java),
    private val getAvatarListUseCase: GetUserAvatarListUseCase = KoinJavaComponent.get(GetUserAvatarListUseCase::class.java),
    private val getInventoryItemListUseCase: GetInventoryItemListUseCase = KoinJavaComponent.get(GetInventoryItemListUseCase::class.java),
    private val putInventoryItemUseCase: PutInventoryItemUseCase = KoinJavaComponent.get(PutInventoryItemUseCase::class.java),
): BaseComponent<CharacterState, ShopEvent>(
    scope = CoroutineScope(Dispatchers.IO),
    initialState = CharacterState()
) {
    fun getCategoryMap() {
        scope.launch {
            runCatching {
                getCategoryMapUseCase()
            }.onSuccess { response ->
                setState {
                    copy(
                        categoryMap = response
                    )
                }
            }.onFailure {
                setState { copy(error = it.asDataThrowable()) }
            }
        }
    }

    fun onChangeItem(item: Item) {
        setState {
            copy(
                selectedCharacter = if (item.categoryId == 6) {
                    (IMAGE_BASE_URL + item.itemImages.find { it.level == currentLevel }?.fileUrl)
                } else this.selectedCharacter,
                selectedItems = if (selectedItems.find { it.id == item.id } != null) {
                    selectedItems.filter { item.id != it.id }
//                    selectedItems.toMutableList().apply {
//                        remove(item)
//                    }
                } else {
                    selectedItems.toMutableList().apply {
                        removeIf { it.categoryId == item.categoryId }
                        add(item)
                    }
                }
            )
        }
    }

    fun saveEquipmentState(
        items: List<Item>
    ) {
        scope.launch {
            val request = PutInventoryItemRequest(
                itemidList = items.map { it.id }
            )

            runCatching {
                putInventoryItemUseCase.invoke(request)
            }.onSuccess { response ->
                onModifyWearingItems()
            }.onFailure {
                setState { copy(error = it.asDataThrowable()) }
            }
        }
    }

    fun onChangeType(categoryId: Long) {
        scope.launch {
            runCatching {
                getInventoryItemListUseCase(categoryId, 1)
            }.onSuccess { response ->
                setState {
                    copy(
                        selectedType = categoryId,
                        categoryItem = response.data,
                        paginationInfo = response.paginationInfo
                    )
                }
            }.onFailure {
                setState { copy(error = it.asDataThrowable()) }
            }
        }
    }

    fun loadNextPage(categoryId: Long, pageIndex: Int) {
        scope.launch {
            runCatching {
                getInventoryItemListUseCase(categoryId, pageIndex)
            }.onSuccess { response ->
                setState {
                    copy(
                        categoryItem = (categoryItem + response.data).distinctBy { it.id },
                        paginationInfo = response.paginationInfo
                    )
                }
            }
        }
    }

    fun onUndo() {
        setState {
            copy(
                selectedCharacter = character,
                selectedItems = wearingItems
            )
        }
    }

    fun onModifyWearingItems() {
        setState {
            copy(
                character = selectedCharacter,
                wearingItems = selectedItems
            )
        }
    }

    fun dismissErrorDialog() {
        setState { copy(error = null) }
    }

    fun setCurrentWearingItems(
        playerId: Long,
        pageIndex: Int
    ) {
        setState { copy(isLoading = true) }
        scope.launch {
            runCatching {
                getAvatarListUseCase(pageIndex)
            }.onSuccess { response ->
                val userInfo = response.data.find { it.id.toLong() == playerId }
                val wearingItems = userInfo?.wearingItems ?: listOf()
                val characterItem = wearingItems.find { it.categoryId == 6 } ?: wearingItems.find { it.categoryId == 1 }
                val characterUrl = characterItem?.itemImage?.fileUrl.orEmpty()

                val url = IMAGE_BASE_URL + characterUrl
                val items = wearingItems.map { item -> item.asShopItem() }

                setState {
                    copy(
                        isLoading = false,
                        currentLevel = userInfo?.level ?: 1,
                        character = url,
                        selectedCharacter = url,
                        wearingItems = items,
                        selectedItems = items,
                    )
                }
            }.onFailure {
                setState { copy(isLoading = false) }
            }
        }
    }
}