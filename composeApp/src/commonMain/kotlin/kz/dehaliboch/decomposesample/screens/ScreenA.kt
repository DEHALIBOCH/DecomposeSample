package kz.dehaliboch.decomposesample.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.essenty.lifecycle.Lifecycle
import kz.dehaliboch.decomposesample.navigation.ScreenAComponent
import kz.dehaliboch.decomposesample.navigation.ScreenAEvent
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ScreenA(component: ScreenAComponent) {
    val text by component.text.subscribeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Screen A")
        OutlinedTextField(
            value = text,
            onValueChange = {
                component.onEvent(ScreenAEvent.UpdateText(it))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Button(
            onClick = {
                component.onEvent(ScreenAEvent.ClickButtonA)
            }
        ) {
            Text("Go To Screen B")
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun ScreenAPreview() {
    ScreenA(
        component = ScreenAComponent(
            componentContext = DefaultComponentContext(
                lifecycle = object : Lifecycle {
                    override fun subscribe(callbacks: Lifecycle.Callbacks) {}

                    override fun unsubscribe(callbacks: Lifecycle.Callbacks) {}

                    override val state: Lifecycle.State
                        get() = Lifecycle.State.RESUMED
                }
            ),
            onNavigateToScreenB = {}
        )
    )
}