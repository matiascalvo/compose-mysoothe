package com.matias.mysoothe.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matias.mysoothe.domain.model.Record
import com.matias.mysoothe.domain.model.RecordType
import com.matias.mysoothe.domain.usecase.GetRecordsUseCase
import com.matias.mysoothe.ui.screens.home.HomeContract.ElementType.AlignYourBody
import com.matias.mysoothe.ui.screens.home.HomeContract.ElementType.Favorite
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRecordsUseCase: GetRecordsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeContract.State.Empty)
    val state = _state.asStateFlow()

    init {
        updateList(_state.value.filter)
    }

    private fun updateList(filter: String) {
        _state.update { it.copy(filter = filter) }
        viewModelScope.launch {
            val records = getRecordsUseCase.invoke(filter).getOrElse { emptyList() }
            _state.update { currentState ->
                val list = buildElementList(records)
                currentState.copy(
                    elements = list,
                    showEmptyState = list.isEmpty()
                )
            }
        }
    }

    private fun buildElementList(records: List<Record>): MutableList<HomeContract.ElementType> {
        val favouriteItems = records.filter { it.type == RecordType.FAVORITE }.chunked(FAVORITES_CHUNK_SIZE)
        val bodyItems = records.filter { it.type == RecordType.BODY }
        val mindItems = records.filter { it.type == RecordType.MIND }

        val list = mutableListOf<HomeContract.ElementType>()
        if (favouriteItems.isNotEmpty()) {
            list.add(Favorite(favouriteItems))
        }
        if (bodyItems.isNotEmpty()) {
            list.add(AlignYourBody(bodyItems))
        }
        if (mindItems.isNotEmpty()) {
            list.add(AlignYourBody(mindItems))
        }
        return list
    }

    fun onFilterChange(value: String) {
        updateList(value)
    }

    companion object {
        private const val FAVORITES_CHUNK_SIZE = 3
    }
}
