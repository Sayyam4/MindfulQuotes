package com.sayyam.mindfulquotes.presentation.home.components

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sayyam.mindfulquotes.R
import com.sayyam.mindfulquotes.data.local.QuoteEntity
import com.sayyam.mindfulquotes.presentation.home.QuotesEvent

@Composable
fun QuotesItem(
    quote: QuoteEntity,
    onEvent: (QuotesEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(
                horizontal = 12.dp,
                vertical = 6.dp
            )
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            ),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(20.dp),
    ) {
        Row {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .weight(1f)
            ) {
                Text(
                    text = quote.quote,
                    style = MaterialTheme.typography.displayMedium
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "~${quote.author}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = modifier
                        .align(Alignment.End)
                        .padding(2.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "share",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            val sendIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, "${quote.quote}\n~${quote.author}")
                                type = "text/plain"
                            }
                            try {
                                val shareIntent = Intent.createChooser(sendIntent, null)
                                context.startActivity(shareIntent)
                            } catch (e: ActivityNotFoundException) {
                                Toast
                                    .makeText(context, "Something went wrong!!", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                )
                Icon(
                    painter = if (quote.favorite) painterResource(id = R.drawable.ic_favorite)  else painterResource(
                        id = R.drawable.ic_favorite_border)  ,
                    contentDescription = if (quote.favorite) "like" else "unlike",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .size(30.dp)
                        .clickable {
                            onEvent(QuotesEvent.OnLikeChange(quote))
                        }
                )
            }
        }
    }
}