package com.tunahankaryagdi.findjob.presentation.application.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.domain.model.user.UserDetail
import com.tunahankaryagdi.findjob.presentation.application.ApplicationEvent
import com.tunahankaryagdi.findjob.presentation.components.CustomAsyncImage
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.presentation.components.SpacerWidth
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.ui.theme.secondaryTextLight
import com.tunahankaryagdi.findjob.utils.Constants
import com.tunahankaryagdi.findjob.utils.ImageType

@Composable
fun UserDetailDialog(
    modifier: Modifier = Modifier,
    onTrigger: (ApplicationEvent) -> Unit,
    userDetail: UserDetail,
    message: String
) {
    AlertDialog(
        onDismissRequest = {
            onTrigger(ApplicationEvent.OnDismissDialog)
        },
        confirmButton = {
            Icon(
                modifier = Modifier
                    .clickable {
                        onTrigger(ApplicationEvent.OnConfirmDialog)
                    },
                tint = CustomTheme.colors.primary,
                imageVector = Icons.Default.Done ,
                contentDescription = stringResource(id = R.string.done)
            )
        },
        dismissButton = {
            Icon(
                modifier = Modifier
                    .clickable {
                        onTrigger(ApplicationEvent.OnDismissDialog)
                    },
                tint = CustomTheme.colors.secondary,
                imageVector = Icons.Default.Close ,
                contentDescription = stringResource(id = R.string.close)
            )
        },
        containerColor = CustomTheme.colors.primaryBackground,
        titleContentColor = CustomTheme.colors.primary,
        textContentColor = CustomTheme.colors.secondaryText,
        title = {
            Text(text = stringResource(id = R.string.user_profile))
        },
        text = {
            LazyColumn() {
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        CustomAsyncImage(
                            modifier = Modifier
                                .weight(2f)
                                .clip(CircleShape),
                            model = "${Constants.BASE_IMAGE_URL}/${userDetail.image}",
                            type = ImageType.User,
                            contentScale = ContentScale.Inside
                        )

                        SpacerWidth(size = CustomTheme.spaces.medium)

                        Column(
                            modifier = Modifier
                                .weight(7f),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = userDetail.nameSurname,
                                style = CustomTheme.typography.bodyLarge
                            )
                            Text(text = userDetail.email)
                        }
                    }
                }
                item{
                    SpacerHeight(size = CustomTheme.spaces.medium)
                    Text(
                        text = stringResource(id = R.string.message),
                        style = CustomTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold, color = secondaryTextLight)
                    )
                    Text(text = message)
                    SpacerHeight(size = CustomTheme.spaces.medium)
                }
                item {
                    Text(
                        text = stringResource(id = R.string.skills),
                        style = CustomTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold, color = secondaryTextLight)
                    )
                }
                if (userDetail.skills.isEmpty()){
                    item {
                        Text(text = stringResource(id = R.string.no_skills))
                    }
                }
                else{
                    items(userDetail.skills.size){
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "• ${userDetail.skills[it].experience} year experience in ${userDetail.skills[it].name}",
                            style = CustomTheme.typography.labelLarge
                        )
                    }
                }



            }
        }
    )
}