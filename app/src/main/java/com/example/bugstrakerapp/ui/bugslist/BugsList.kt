package com.example.bugstrakerapp.ui.bugslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bugstrakerapp.R
import com.example.bugstrakerapp.ui.theme.BugsTrakerAppTheme
import com.example.bugstrakerapp.ui.theme.padding


@Preview
@Composable
fun BugsList() {
    BugsTrakerAppTheme {
        val search = remember { mutableStateOf("") }
        Box(modifier = Modifier.fillMaxSize()) {
            Column {
                TextField(
                    value = search.value,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { search.value = it },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "") }
                )
                LazyColumn {
                    items(11) {
                        ItemList(
                            title = "Заголовок",
                            text = "Долбоебы вы",
                            date = "Ноябрь 13.2025",
                            type = "Android * bug",
                        )
                    }
                }
            }
            FloatingActionButton(
                onClick = {},
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(padding.padding24)
                    .size(56.dp)
            ) {
                Icon(Icons.Default.Add, modifier = Modifier, contentDescription = null)
            }
        }
    }


}

@Composable
private fun ItemList(
    title: String,
    text: String,
    date: String,
    type: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = padding.padding24)
    ) {
        Row {
            Image(
                painter = painterResource(R.drawable.openeye),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Column(modifier = Modifier.padding(horizontal = padding.padding24)) {
                Text(text = title)
                Text(text = text)
                Text(text = date + " * " + type)
            }
        }
    }
}