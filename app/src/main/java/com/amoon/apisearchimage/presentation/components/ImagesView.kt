package com.amoon.apisearchimage.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Comment
import androidx.compose.material.icons.rounded.Download
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.amoon.apisearchimage.domain.model.ImageDb
import com.amoon.apisearchimage.presentation.theme.RedErrorLight
import com.amoon.apisearchimage.util.DEFAULT_IMAGE
import com.amoon.apisearchimage.util.loadPicture
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun ImagesView(
    images: ImageDb
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        item {
            val image = images.largeImageURL?.let {
                loadPicture(
                    url = it,
                    defaultImage = DEFAULT_IMAGE
                ).value
            }
            image?.let { img ->
                Image(
                    bitmap = img.asImageBitmap(),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IMAGE_HEIGHT.dp),
                    contentScale = ContentScale.Crop,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {

               var tags = images.tags.toString().split(",").forEach { tagItem ->
                    tagItem
                }
                images.tags?.let {
                    Text(
                        text = "Image Tags :  $tags",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        style = MaterialTheme.typography.caption
                    )
                }

                Text(
                    text = images.user.toString(),
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentWidth(Alignment.Start),
                    style = MaterialTheme.typography.h2
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                ) {


                    Icon(
                        imageVector = Icons.Rounded.Comment,
                        contentDescription = "Icon",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .wrapContentHeight(Alignment.CenterVertically)
                    )
                    Text(
                        text = images.comments.toString(),
                        modifier = Modifier
                            .padding(start = 2.dp)
                            .wrapContentHeight(Alignment.CenterVertically),
                        style = MaterialTheme.typography.caption
                    )

                    Icon(
                        imageVector = Icons.Rounded.Download,
                        contentDescription = "Icon",
                        modifier = Modifier
                            .padding(start = 50.dp)
                            .align(Alignment.CenterVertically)
                            .wrapContentHeight(Alignment.CenterVertically)
                    )

                    Text(
                        text = images.downloads.toString(),
                        modifier = Modifier
                            .wrapContentHeight(Alignment.CenterVertically),
                        style = MaterialTheme.typography.caption
                    )

                    Icon(
                        imageVector = Icons.Rounded.Favorite,
                        contentDescription = "Icon",
                        tint = RedErrorLight,
                        modifier = Modifier
                            .padding(start = 50.dp)
                            .align(Alignment.CenterVertically)
                            .wrapContentHeight(Alignment.CenterVertically)
                    )
                    val likes = images.likes.toString()
                    Text(
                        text = likes,
                        modifier = Modifier
                            .padding(start = 2.dp)
                            .wrapContentHeight(Alignment.CenterVertically),
                        style = MaterialTheme.typography.caption
                    )
                }

            }
        }
    }
}