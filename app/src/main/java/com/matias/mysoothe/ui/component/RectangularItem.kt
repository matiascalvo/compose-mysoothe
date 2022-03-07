package com.matias.mysoothe.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matias.mysoothe.R
import com.matias.mysoothe.domain.model.Record
import com.matias.mysoothe.domain.model.RecordType
import com.matias.mysoothe.ui.theme.MySootheTheme

@Composable
fun RectangularItem(record: Record) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .height(56.dp)
            .width(192.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.size(56.dp),
                painter = painterResource(id = record.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                text = record.name,
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
            )
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
    MySootheTheme {
        Surface() {
            RectangularItem(Record("Short mantras test test", R.drawable.self_massage, RecordType.FAVORITE))
        }
    }
}
