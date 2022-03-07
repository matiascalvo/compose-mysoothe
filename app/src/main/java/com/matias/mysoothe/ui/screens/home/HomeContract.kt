package com.matias.mysoothe.ui.screens.home

import com.matias.mysoothe.domain.model.Record

class HomeContract {

    data class State(
        val filter: String = "",
        val elements: List<ElementType> = emptyList(),
        val showEmptyState: Boolean = true,
    ) {
        companion object {
            val Empty = State()
        }
    }

    sealed class ElementType {
        class Favorite(val list: List<List<Record>>) : ElementType()
        class AlignYourBody(val list: List<Record>) : ElementType()
        class AlignTourMind(val list: List<Record>) : ElementType()
    }
}
