package com.matias.mysoothe.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matias.mysoothe.R
import com.matias.mysoothe.ui.theme.MySootheTheme

@Suppress("LongParameterList")
@Composable
fun MyTextField(
    modifier: Modifier = Modifier,
    labelResource: Int,
    value: String,
    leadingIcon: ImageVector? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        label = {
            Text(
                text = stringResource(id = labelResource),
                modifier = Modifier.wrapContentHeight(Alignment.CenterVertically)
            )
        },
        textStyle = MaterialTheme.typography.body1,
        onValueChange = onValueChange,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        isError = isError,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        leadingIcon = {
            leadingIcon?.let {
                Icon(
                    modifier = Modifier.size(18.dp),
                    imageVector = leadingIcon,
                    contentDescription = null
                )
            }
        }
    )
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
        MyTextField(
            labelResource = R.string.app_name,
            value = "",
            leadingIcon = Icons.Default.Search,
            onValueChange = { }
        )
    }
}
