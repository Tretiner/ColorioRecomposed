package com.fuf.colorio.ui._shared.components.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun ListDialog(
    modifier: Modifier = Modifier,
    show: Boolean,
    title: String,
    items: List<String>,
    initialIndex: Int,
    onSelectIndex: (selectedIndex: Int) -> Unit,
    onDismiss: () -> Unit
) {
    val itemIndex by remember(initialIndex) { mutableIntStateOf(initialIndex) }

    if (show) {
        Dialog(onDismissRequest = onDismiss) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(100.dp, 300.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                ) {
                    Text(modifier = Modifier.align(Alignment.CenterHorizontally), text = title, fontSize = 22.sp, fontWeight = FontWeight.Medium)

                    Spacer(modifier = Modifier.height(10.dp))

                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        itemsIndexed(items = items) { i: Int, item ->
                            TextButton(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = { onSelectIndex(i) }
                            ) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = item,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SelectableItem(
    modifier: Modifier = Modifier,
    text: String
) {
}

@Preview(name = "ListDialog", showSystemUi = true)
@Composable
private fun PreviewListDialog() {
    ListDialog(
        title = "Color models",
        items = (1..20).map { i -> "model $i" },
        onSelectIndex = {},
        onDismiss = {},
        show = true,
        initialIndex = 0
    )
}