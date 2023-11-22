package com.tunahankaryagdi.findjob.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.components.CustomOutlinedTextField
import com.tunahankaryagdi.findjob.presentation.components.CustomTinyButton
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.presentation.components.SpacerWidth
import com.tunahankaryagdi.findjob.presentation.home.components.PopularJobCard
import com.tunahankaryagdi.findjob.presentation.home.components.RecentPostCard
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {

    var text by remember {
        mutableStateOf("")
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(CustomTheme.spaces.medium)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            
            CustomTinyButton(
                icon = Icons.Filled.List
            )
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = stringResource(id = R.string.job_image)
            )
        }

        SpacerHeight(size = CustomTheme.spaces.medium)
        
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomOutlinedTextField(
                modifier = Modifier
                    .weight(1f),
                value = text,
                placeholder =  stringResource(id = R.string.search_here),
            ) { text = it }

            SpacerWidth(size = CustomTheme.spaces.small)
            
            CustomTinyButton(icon = Icons.Filled.Search)
        }

        SpacerHeight(size = CustomTheme.spaces.medium)

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.popular_job),
                style = CustomTheme.typography.titleNormal
            )
            Text(
                text = stringResource(id = R.string.show_all),
                style = CustomTheme.typography.bodySmall
            )
        }

        SpacerHeight(size = CustomTheme.spaces.small)



        LazyRow() {
            items(5) {

                PopularJobCard(
                    companyName = "Google",
                    jobName = "Product Manager",
                    salary = 3000,
                    location = "Canada Toronto")
                SpacerWidth(size = CustomTheme.spaces.small)
            }

        }
        
        SpacerHeight(size = CustomTheme.spaces.medium)

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.recent_post),
                style = CustomTheme.typography.titleNormal
            )
            Text(
                text = stringResource(id = R.string.show_all),
                style = CustomTheme.typography.bodySmall
            )
        }

        SpacerHeight(size = CustomTheme.spaces.small)

        LazyColumn(){
            items(5){
                Column {
                    RecentPostCard(
                        jobName = "UI/UX Designer",
                        jobType = "Full time",
                        salary = 4500
                    )
                    SpacerHeight(size = CustomTheme.spaces.small)
                }
                
            }
        }
    }


}