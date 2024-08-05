package org.hariswei.cmpshowcaseproject.ui.app

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppbarViewModel : ViewModel() {

    private val _backNavState = MutableStateFlow(false)
    val backNavState : StateFlow<Boolean> = _backNavState

    private val _sortDropDownState = MutableStateFlow(false)
    val sortDropDownState : StateFlow<Boolean> = _sortDropDownState

    fun onEvent(event: AppbarScreenEvent) {
        when(event) {
            is AppbarScreenEvent.IsShowBackButton -> {
                _backNavState.value = event.isShow
            }

            is AppbarScreenEvent.IsShowSortDropDown -> {
                _sortDropDownState.value = !sortDropDownState.value
            }
        }
    }
}

sealed class AppbarScreenEvent {
    data class IsShowBackButton(
        val isShow: Boolean = false
    ): AppbarScreenEvent()

    data object IsShowSortDropDown: AppbarScreenEvent()

}