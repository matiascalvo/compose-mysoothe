package com.matias.mysoothe.ui.screens.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.matias.mysoothe.R
import com.matias.mysoothe.domain.model.Record
import com.matias.mysoothe.ui.component.CircleItemsCarrouselWithTitle
import com.matias.mysoothe.ui.component.MyTextField
import com.matias.mysoothe.ui.component.SingleRectangularCarousel
import com.matias.mysoothe.ui.compose.rememberStateWithLifecycle
import com.matias.mysoothe.ui.screens.home.HomeContract.ElementType.AlignTourMind
import com.matias.mysoothe.ui.screens.home.HomeContract.ElementType.AlignYourBody
import com.matias.mysoothe.ui.screens.home.HomeContract.ElementType.Favorite
import com.matias.mysoothe.ui.theme.MySootheTheme

@Composable
fun HomeScreen() {
    HomeScreen(viewModel = hiltViewModel())
}

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val viewState by rememberStateWithLifecycle(viewModel.state)

    Scaffold(
        bottomBar = {
            BottomNavigation(
                onHomeClicked = {},
                onProfileClicked = {}
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = null,
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
    ) { padding ->
        HomeScreenContent(
            state = viewState,
            modifier = Modifier.padding(padding),
            onSearchChanged = { viewModel.onFilterChange(it) }
        )
    }
}

@Composable
private fun BottomNavigation(
    onHomeClicked: () -> Unit = {},
    onProfileClicked: () -> Unit = {},
) {
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.background,
    ) {
        BottomNavigationItem(
            selected = true,
            onClick = onHomeClicked,
            icon = {
                Icon(
                    Icons.Default.Spa,
                    contentDescription = null,
                )
            },
            label = {
                Text(stringResource(id = R.string.home).uppercase())
            }
        )

        BottomNavigationItem(
            selected = false,
            onClick = onProfileClicked,
            icon = {
                Icon(
                    Icons.Default.AccountCircle,
                    contentDescription = null,
                )
            },
            label = {
                Text(stringResource(id = R.string.profile).uppercase())
            }
        )
    }
}

@Composable
fun HomeScreenContent(
    state: HomeContract.State,
    modifier: Modifier = Modifier,
    onSearchChanged: (String) -> Unit = {}
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val focusManager = LocalFocusManager.current

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            MyTextField(
                modifier = Modifier.padding(horizontal = 16.dp),
                labelResource = R.string.search,
                value = state.filter,
                leadingIcon = Icons.Default.Search,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                onValueChange = onSearchChanged
            )

            if (state.showEmptyState) {
                EmptyState()
            } else {
                ElementsList(state)
            }
        }
    }
}

@Composable
private fun ElementsList(state: HomeContract.State) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        state.elements.forEach { element ->
            when (element) {
                is Favorite -> FavoriteCollectionsSection(recordLists = element.list)
                is AlignYourBody -> AlignYourBodySection(records = element.list)
                is AlignTourMind -> AlignYourMindSection(records = element.list)
            }
        }
    }
}

@Composable
private fun EmptyState() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(id = R.string.empty_search),
            modifier = Modifier
                .padding(48.dp)
                .align(Alignment.Center),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun AlignYourBodySection(records: List<Record>) {
    CircleItemsCarrouselWithTitle(title = R.string.align_your_body, records = records)
}

@Composable
private fun AlignYourMindSection(records: List<Record>) {
    CircleItemsCarrouselWithTitle(title = R.string.aling_your_mind, records = records)
}

@Composable
private fun FavoriteCollectionsSection(recordLists: List<List<Record>>) {
    Text(
        text = stringResource(id = R.string.favorite_collections).uppercase(),
        style = MaterialTheme.typography.h2,
        modifier = Modifier
            .paddingFromBaseline(40.dp)
            .padding(horizontal = 16.dp)
    )
    recordLists.forEach {
        SingleRectangularCarousel(records = it)
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
        HomeScreenContent(state = HomeContract.State())
    }
}
