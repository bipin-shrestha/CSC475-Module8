package com.business.fitrack.ui.composables.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.business.fitrack.R
import com.business.fitrack.data.models.User
import com.business.fitrack.ui.composables.RegularButton

@Preview
@Composable
fun EmptyWorkoutPlanView(
    modifier: Modifier = Modifier,
    user: User? = null,
    onClick: () -> Unit = {}
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty_workout_anim))

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.Start
    ) {

        Heading(text =
            stringResource(R.string.welcome) +
                if (user?.userName == null) "!"
                else " ${user.userName.replaceFirstChar { it.uppercase() }}!"
        )


        SubHeading(text = stringResource(R.string.empty_plan_text))

        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
            ,
            iterations = Int.MAX_VALUE
        )


        RegularButton(text = stringResource(R.string.get_started),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = onClick)


    }
}