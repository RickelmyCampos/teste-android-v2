package com.gilbersoncampos.testeaiko.features.busLines

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun BusLinesScreen(viewModel: BusLinesViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.collectAsState()
    when (uiState.value) {
        is BusLineUiState.Loading -> {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }

        is BusLineUiState.Error -> {
            Column {
                Text(text = "Erro, tente novamente")
                Button(onClick = { }) {
                    Text(text = "Tentar novamente")
                }
            }
        }

        is BusLineUiState.Success -> {
            BusLinesUi((uiState.value as BusLineUiState.Success).busLineList)
        }
    }

}

@Composable
fun BusLinesUi(listLines: List<BusLine>) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(listLines, key = { it.id }) {
                BusLineItemComponent(busLine = it)
            }
        }
    }
}

@Composable
fun BusLineItemComponent(busLine: BusLine) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "${busLine.codeBus} - ${busLine.name}")
        Text(text = busLine.direction)
    }
}

@Composable
@Preview
fun BusLineItemComponentPreview() {
    BusLineItemComponent(
        BusLine(
            "1",
            name = "NAksdnkasd",
            codeBus = "asdasdas",
            direction = "asdjkah"
        )
    )
}