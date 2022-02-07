package com.ashita.belgaxplore.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.ashita.belgaxplore.R
import com.ashita.belgaxplore.domain.data.Locations
import com.ashita.belgaxplore.presentation.locationlist.LocationsListViewModel
import com.ashita.belgaxplore.ui.theme.TextDesignBold18SP

@ExperimentalCoilApi
@Composable
fun MainScreen(navController: NavController) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        LocationsList(
            navController
        )
    }
}

@ExperimentalCoilApi
@Composable
fun LocationsList(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "BelgaXplore",
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                elevation = 5.dp

            )
        }
    ) { innerPadding ->
        FetchLocationsList(navController = navController, Modifier.padding(innerPadding))
    }
}

@ExperimentalCoilApi
@Composable
fun FetchLocationsList(
    navController: NavController,
    modifier: Modifier,
    viewModel: LocationsListViewModel = hiltViewModel()
) {
    val scrollState = rememberLazyListState()
    val state = viewModel.locationListState.value

    // We save the coroutine scope where our animated scroll will be executed
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = scrollState,
            modifier = modifier.padding(vertical = 8.dp, horizontal = 8.dp)
        ) {
            items(items = state.locationsList) { location ->
                Location(navController, modifier, location = location)
            }
        }

        if (state.isError.isNotBlank()) {
            Text(
                text = state.isError,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .align(Alignment.Center)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
        }
    }


}

@ExperimentalCoilApi
@Composable
fun Location(navController: NavController, modifier: Modifier, location: Locations) {
    Card(
        shape = RoundedCornerShape(22.dp),
        elevation = 5.dp,
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .fillMaxWidth()
            .clickable { onItemClick(location, navController = navController) }
    ) {
        println("Name of location Main Screen : ${location.id}")
        CardContent(modifier, location)
    }
}

fun onItemClick(location: Locations, navController: NavController) {
    navController.navigate(route = Screen.DetailScreen.route + "/${location.id}")
}

@ExperimentalCoilApi
@Composable
private fun CardContent(modifier: Modifier, location: Locations) {
    Row(
        modifier = modifier
            .padding(12.dp)
            .padding(horizontal = 10.dp)
    ) {
        val painter = rememberImagePainter(
            data = location.poster,
            builder = {
                crossfade(true)
                placeholder(R.drawable.placeholder)
                fallback(R.drawable.durbuy)
                error(R.drawable.durbuy)
                transformations(CircleCropTransformation())
            }
        )

        Image(
            painter = painter,
            contentDescription = stringResource(R.string.image_content_desc),
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .size(60.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = modifier.width(15.dp))

        Text(
            text = location.name,
            style = TextDesignBold18SP,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = modifier.align(Alignment.CenterVertically)
        )
    }
}