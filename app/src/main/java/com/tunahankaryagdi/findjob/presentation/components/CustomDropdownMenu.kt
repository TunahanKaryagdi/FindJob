package com.tunahankaryagdi.findjob.presentation.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.utils.DropdownItem


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomDropdownMenu(
    modifier: Modifier = Modifier,
    selectedDropdownValue: String,
    items: List<DropdownItem>,
    onExpandedChange: (Boolean) ->Unit,
    onDismiss: ()-> Unit,
    onClickItem: (DropdownItem) -> Unit,
    isExpanded: Boolean
) {



    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = isExpanded ,
        onExpandedChange = onExpandedChange
    ) {

            CustomOutlinedTextField(
                value = selectedDropdownValue,
                readOnly = true,
                placeholder = stringResource(id = R.string.select_a_skill),
                onValueChange = {},
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = stringResource(id = R.string.dropdown_icon)
                    )
                }
            )


        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = onDismiss
        ) {

            items.forEach {item->
                DropdownMenuItem(
                    text = { Text(text = item.name) },
                    onClick = { onClickItem(item) })
            }
        }
    }



}