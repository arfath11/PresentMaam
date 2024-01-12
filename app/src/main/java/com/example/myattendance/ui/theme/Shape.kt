package com.example.myattendance.ui.theme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(50.dp),
    medium = RoundedCornerShape(bottomStart = 16.dp, topEnd = 16.dp)
)
/*
use case  =  Image(
       modifier = modifier
           .size(dimensionResource(id = R.dimen.image_size))
           .padding(dimensionResource(id = R.dimen.padding_small))
           .clip(MaterialTheme.shapes.small),
 */