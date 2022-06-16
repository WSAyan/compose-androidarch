package com.sslwireless.androidarch.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sslwireless.androidarch.ui.data.MenuItemData
import com.sslwireless.androidarch.ui.theme.GreyWhite3
import com.sslwireless.androidarch.ui.theme.SlateGrey
import com.sslwireless.androidarch.ui.theme.Typography

@Composable
fun CommonToolbar(
    title: String,
    backIcon: ImageVector = Icons.Filled.ArrowBack,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = Typography.body1,
                fontWeight = FontWeight.Bold
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(
                    backIcon,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onBackground
                )
            }
        },
    )
}

@Composable
fun ToolbarWithMenus(
    title: String = "",
    searchHint: String = "Search",
    backIcon: ImageVector = Icons.Filled.ArrowBack,
    menuList: List<MenuItemData>? = null,
    onBackPressed: () -> Unit,
    onActionPressed: (menuItemData: MenuItemData) -> Unit,
    onSearchValueChange: (value: String) -> Unit,
    onSearchKeyPressed: (search: String) -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    TopAppBar(
        title = {
            if (isExpanded) {
                Row(
                    modifier = Modifier
                        .background(
                            shape = RoundedCornerShape(10.dp),
                            color = GreyWhite3
                        )
                        .height(35.dp),
                ) {

                    Spacer(modifier = Modifier.width(12.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        BasicTextField(
                            value = searchText,
                            onValueChange = {
                                searchText = it

                                onSearchValueChange(searchText.text)
                            },
                            maxLines = 1,
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            decorationBox = {
                                if (searchText.text.isEmpty()) {
                                    Text(
                                        text = searchHint,
                                        textAlign = TextAlign.Start,
                                        style = Typography.caption,
                                        color = SlateGrey,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }

                                it()
                            },
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                            keyboardActions = KeyboardActions(
                                onSearch = {
                                    onSearchKeyPressed(searchText.text)
                                }
                            ),
                        )
                    }

                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "close",
                        modifier = Modifier
                            .size(12.dp)
                            .align(alignment = Alignment.CenterVertically)
                            .clickable {
                                isExpanded = !isExpanded

                                searchText = TextFieldValue("")
                            },
                        tint = SlateGrey,
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                }
            } else {
                Text(
                    text = title.uppercase(),
                    style = Typography.body1,
                    fontWeight = FontWeight.Bold,
                )
            }
        },
        backgroundColor = MaterialTheme.colors.background,
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(
                    backIcon,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onBackground
                )
            }
        },
        actions = {
            menuList?.forEach { menu ->

                // hide search menu action
                if (isExpanded && menu.isExpandableSearch) {
                    Spacer(modifier = Modifier.width(20.dp))
                    return@forEach
                }

                menu.ShowMenu {
                    if (it.isExpandableSearch) {
                        isExpanded = !isExpanded
                    }

                    onActionPressed(it)
                }
                Spacer(modifier = Modifier.width(20.dp))
            }
        }
    )
}

@Preview
@Composable
fun ToolbarWithMenusPreview() {
    ToolbarWithMenus(
        onBackPressed = {},
        onActionPressed = {},
        onSearchValueChange = {},
        onSearchKeyPressed = {})
}

@Composable
fun MenuItemData.ShowMenu(onActionPressed: (menuItemData: MenuItemData) -> Unit) {
    val drawable = this.drawable
    val icon = this.icon

    if (drawable != null) {
        Icon(
            painter = painterResource(id = drawable),
            contentDescription = "",
            Modifier.clickable {
                onActionPressed(this)
            }
        )
    } else if (icon != null) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            Modifier.clickable {
                onActionPressed(this)
            }
        )
    }

    val label = this.label
    if (label != null) {
        Text(
            text = LocalContext.current.getString(label),
            style = Typography.overline,
            color = MaterialTheme.colors.onBackground
        )
    }
}

