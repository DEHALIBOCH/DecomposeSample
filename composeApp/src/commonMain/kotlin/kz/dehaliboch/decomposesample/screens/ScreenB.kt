package kz.dehaliboch.decomposesample.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.Lifecycle
import kz.dehaliboch.decomposesample.navigation.ScreenBComponent
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ScreenB(component: ScreenBComponent) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Screen B")
        Button(
            onClick = {
                component.goBack()
            }
        ) {
            Text("Go Back")
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun ScreenAPreview() {
    ScreenB(
        component = ScreenBComponent(
            componentContext = DefaultComponentContext(
                lifecycle = object : Lifecycle {
                    override fun subscribe(callbacks: Lifecycle.Callbacks) {}

                    override fun unsubscribe(callbacks: Lifecycle.Callbacks) {}

                    override val state: Lifecycle.State
                        get() = Lifecycle.State.RESUMED
                }
            ),
            text = "qwe",
            onGoBack = {}
        )
    )
}