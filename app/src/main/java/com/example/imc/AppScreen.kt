package com.example.imc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen( modifier: Modifier, viewModel: ImcScreenViewModel = ImcScreenViewModel() ) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Calculate BMI")
                }
            )
        }
    ) { paddingValues ->
        AppScreenContent(modifier.padding(paddingValues) , viewModel)
    }
}

@Composable
fun AppScreenContent(modifier: Modifier, viewModel: ImcScreenViewModel) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "%.2f".format(viewModel.bmi.value),
                style = MaterialTheme.typography.headlineSmall,
            )
            Divider(modifier = Modifier.fillMaxWidth(.7f), thickness = 2.5.dp)
            Text(
                text = viewModel.message.value,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ModeSelector(viewModel.selectedMode.value, updateMode = viewModel::updateMode)
            CustomTextField(
                viewModel.heightState.value,
                ImeAction.Next,
                viewModel::updateHeight
            )
            CustomTextField(
                viewModel.weightState.value,
                ImeAction.Done,
                viewModel::updateWeight
            )
        }
        Spacer(modifier = Modifier)

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(16.dp, 12.dp)
                .fillMaxWidth(),
        ) {
            ActionButton(text = "Clear", viewModel::clear)
            ActionButton(text = "Calculate", viewModel::calculate)
        }
    }
}
