package com.ashita.belgaxplore.presentation.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.ashita.belgaxplore.R
import com.ashita.belgaxplore.presentation.locationdetails.LocationDetailViewModel
import com.ashita.belgaxplore.ui.theme.BelgaXploreTheme
import com.ashita.belgaxplore.ui.theme.TextDesignBold14SP
import com.ashita.belgaxplore.ui.theme.TextDesignBold18SP
import com.ashita.belgaxplore.ui.theme.TextDesignNormal14SP


@Composable
fun DetailScreen(viewModel: LocationDetailViewModel = hiltViewModel()) {
    val scrollState = rememberScrollState()
    val state = viewModel.locationDetailsState.value
    BelgaXploreTheme {
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
                        IconButton(onClick = { }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = null)
                        }
                    },
                    elevation = 5.dp

                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize()) {
                state.locationDetails?.apply {
                    Column(
                        Modifier
                            .scrollable(
                                state = scrollState,
                                enabled = true,
                                orientation = Orientation.Vertical
                            )
                            .padding(innerPadding)
                    ) {
                        Image(painter = rememberImagePainter(
                            data = poster,
                            onExecute = ImagePainter.ExecuteCallback { _, _ -> true },
                            builder = {
                                crossfade(true)
                                placeholder(R.drawable.durbuy)
                            }
                        ),
                            contentScale = ContentScale.FillBounds,
                            contentDescription = null,
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth()
                        )

                        Text(
                            text = name.orEmpty(),
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
                            style = TextDesignBold18SP.copy(color = Color.Blue)
                        )

                        Divider()

                        Text(
                            text = description!!,
                            style = TextDesignNormal14SP,
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)
                        )

                        Divider()

                        Row(
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Best time to visit : ",
                                style = TextDesignBold14SP
                            )

                            Text(
                                text = bestTimeToVisit!!,
                                style = TextDesignNormal14SP
                            )
                        }

                        Divider()

                        AnnotatedClickableText(link!!)
                    }
                }


                if (state.errorMessage.isNotBlank()) {
                    Text(
                        text = state.errorMessage,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                            .align(Alignment.Center)
                    )
                }

                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

        }
    }
}


@Composable
fun AnnotatedClickableText(url: String) {
    val context = LocalContext.current
    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        ) { append(stringResource(R.string.check_on_maps)) }

        // We attach this *URL* annotation to the following content
        // until `pop()` is called
        pushStringAnnotation(
            tag = "URL",
            annotation = url
        )
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                fontSize = 14.sp
            )
        ) {
            append(stringResource(R.string.click_here))
        }

        pop()
    }

    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            // We check if there is an *URL* annotation attached to the text
            // at the clicked position
            annotatedText.getStringAnnotations(
                tag = "URL", start = offset,
                end = offset
            )
                .firstOrNull()?.let { annotation ->
                    startActivity(
                        context, Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(annotation.item)
                        ), null
                    )

                }
        },
        modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)
    )
}

