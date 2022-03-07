package com.matias.mysoothe.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matias.mysoothe.R
import com.matias.mysoothe.ui.theme.MySootheTheme

@Composable
private fun MyButton(
    titleRes: Int,
    enabled: Boolean,
    buttonColor: Color = MaterialTheme.colors.primary,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp),
        enabled = enabled,
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor)
    ) {
        Text(text = stringResource(id = titleRes).uppercase())
    }
}

@Composable
fun PrimaryButton(titleRes: Int, enabled: Boolean = true, onClick: () -> Unit) {
    MyButton(titleRes = titleRes, enabled = enabled, buttonColor = MaterialTheme.colors.primary, onClick = onClick)
}

@Composable
fun SecondaryButton(titleRes: Int, enabled: Boolean = true, onClick: () -> Unit) {
    MyButton(titleRes = titleRes, enabled = enabled, buttonColor = MaterialTheme.colors.secondary, onClick = onClick)
}

@Preview(
    name = "Night Mode - Primary",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode - Primary",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun Preview() {
    MySootheTheme() {
        PrimaryButton(titleRes = R.string.log_in, onClick = {})
    }
}

@Suppress("UnusedPrivateMember")
@Preview(
    name = "Night Mode - Secondary",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode - Secondary",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun PreviewSecondary() {
    MySootheTheme {
        SecondaryButton(titleRes = R.string.log_in, onClick = {})
    }
}
