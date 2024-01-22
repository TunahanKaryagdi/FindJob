package com.tunahankaryagdi.findjob.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.utils.ImageType

@Composable
fun CustomAsyncImage(
    modifier: Modifier = Modifier,
    model: Any?,
    type: ImageType,
    contentScale: ContentScale = ContentScale.Crop
) {

    AsyncImage(
        modifier = modifier,
        model = model ,
        contentDescription = type.name,
        contentScale = contentScale,
        placeholder = if (type == ImageType.User) painterResource(id = R.drawable.ic_default_user) else painterResource(
            id = R.drawable.ic_default_company
        ),
        error = if (type == ImageType.User) painterResource(id = R.drawable.ic_default_user) else painterResource(
            id = R.drawable.ic_default_company
        )
    )


}