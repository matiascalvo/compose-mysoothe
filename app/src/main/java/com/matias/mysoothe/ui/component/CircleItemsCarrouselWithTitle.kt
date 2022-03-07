package com.matias.mysoothe.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.matias.mysoothe.domain.model.Record

@Composable
fun CircleItemsCarrouselWithTitle(title: Int, records: List<Record>) {
    Text(
        text = stringResource(id = title).uppercase(),
        style = MaterialTheme.typography.h2,
        modifier = Modifier
            .paddingFromBaseline(40.dp)
            .padding(horizontal = 16.dp)
    )
    CarouselRow(records = records)
}
