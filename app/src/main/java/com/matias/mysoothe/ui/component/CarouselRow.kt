package com.matias.mysoothe.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matias.mysoothe.data.repositories.alignYourBodyRecords
import com.matias.mysoothe.domain.model.Record
import com.matias.mysoothe.ui.theme.MySootheTheme

@Composable
fun CarouselRow(records: List<Record>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(start = 16.dp, end = 8.dp)
    ) {
        items(records) { item ->
            CircleItem(record = item)
        }
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun Preview() {
    MySootheTheme() {
        androidx.compose.material.Surface() {
            CarouselRow(records = alignYourBodyRecords)
        }
    }
}
