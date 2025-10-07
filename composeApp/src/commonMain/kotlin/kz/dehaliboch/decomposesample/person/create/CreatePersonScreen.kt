package kz.dehaliboch.decomposesample.person.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kz.dehaliboch.decomposesample.util.preview.defaultPreviewComponentContext
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CreatePersonScreen(
    component: CreatePersonComponent,
    modifier: Modifier = Modifier.fillMaxSize()
) {

    val state by component.state.collectAsState()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = state.name,
            onValueChange = component::updateName,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text("name")
            }
        )

        TextField(
            value = state.age.toString(),
            onValueChange = component::updateAge,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text("age")
            }
        )

        Button(
            onClick = component::finish
        ) {
            Text(text = "Finish")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CreatePersonScreenPreview() {

    CreatePersonScreen(
        component = CreatePersonComponent(
            componentContext = defaultPreviewComponentContext(),
            onDismiss = {},
            onFinish = { },
        )
    )
}