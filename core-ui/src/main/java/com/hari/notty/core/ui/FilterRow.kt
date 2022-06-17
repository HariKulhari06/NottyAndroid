package com.hari.notty.core.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.har.notty.core.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterRow(
    modifier: Modifier = Modifier,
    lable:String,
) {
    Surface() {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(45.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = lable, style = MaterialTheme.typography.bodyMedium
            )

            Surface(
                onClick = { },
                shape = MaterialTheme.shapes.extraLarge
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "New", style = MaterialTheme.typography.bodySmall
                    )
                    Icon(
                        modifier = Modifier
                            .size(20.dp)
                            .padding(start = 4.dp),
                        painter = painterResource(id = R.drawable.ic_filter),
                        contentDescription = null
                    )

                }
            }

        }
    }
}


@Preview
@Composable
fun CategoryFilterItemPreview() {
    FilterRow(lable = "Categories")
}