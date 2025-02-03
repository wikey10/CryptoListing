package com.app.cryptolisting.crypto.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.cryptolisting.crypto.presentation.coin_list.models.DisplayableNumber
import com.app.cryptolisting.ui.theme.CryptoListingTheme
import com.app.cryptolisting.ui.theme.greenBackground

@Composable
fun PriceChange(
    change:DisplayableNumber,
    modifier: Modifier = Modifier
){
   val color = if(change.value <0.0){
       MaterialTheme.colorScheme.onErrorContainer
   }
    else{
        Color.Green
    }
    val backgroundColor = if(change.value < 0.0){
        MaterialTheme.colorScheme.errorContainer
    }
    else{
        greenBackground
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clip(RoundedCornerShape(100f)).background(backgroundColor).padding(horizontal = 4.dp)) {
        Icon(
            imageVector = if(change.value <0.0){
                Icons.Default.KeyboardArrowDown
            }
            else{
                Icons.Default.KeyboardArrowUp
            },
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = color
        )
        Text(text = "${change.formatted} ",
            color = color,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium)
    }
}


@PreviewLightDark
@Composable
private fun PriceChangePreview(){
    CryptoListingTheme {
        PriceChange(
            change = DisplayableNumber(
                value = 2.43,
                formatted = "2.43 %"
            )
        )
    }
}