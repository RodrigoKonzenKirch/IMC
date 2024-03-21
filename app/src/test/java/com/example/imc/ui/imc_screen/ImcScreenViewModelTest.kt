package com.example.imc.ui.imc_screen

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ImcScreenViewModelTest {

    private lateinit var viewModel: ImcScreenViewModel

    @Before
    fun setup(){
        viewModel = ImcScreenViewModel()

    }

    @Test
    fun `updateHeight with valid Height should update height state value`() {
        val expectedResult = "1.80"

        viewModel.updateHeight(expectedResult)

        assertThat(viewModel.heightState.value.value).isEqualTo(expectedResult)
    }

    @Test
    fun `updateWeight with valid weight should update weight state value   `() {
        val expectedResult = "74"

        viewModel.updateWeight(expectedResult)

        assertThat(viewModel.weightState.value.value).isEqualTo(expectedResult)

    }

    @Test
    fun `clear when called should update height and weight state value to empty string and error to null`(){
        val fakeValue = "1"
        viewModel.updateHeight(fakeValue)
        viewModel.updateWeight(fakeValue)

        assertThat(viewModel.heightState.value.value).isEqualTo(fakeValue)
        assertThat(viewModel.weightState.value.value).isEqualTo(fakeValue)

        viewModel.clear()

        assertThat(viewModel.heightState.value.value).isEqualTo("")
        assertThat(viewModel.weightState.value.value).isEqualTo("")
        assertThat(viewModel.bmi.value).isEqualTo(0.0)
        assertThat(viewModel.message.value).isEqualTo("")
        assertThat(viewModel.heightState.value.error).isEqualTo(null)
        assertThat(viewModel.weightState.value.error).isEqualTo(null)

    }
}