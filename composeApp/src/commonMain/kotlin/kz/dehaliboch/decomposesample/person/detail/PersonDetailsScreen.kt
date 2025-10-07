package kz.dehaliboch.decomposesample.person.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import kz.dehaliboch.decomposesample.Person
import kz.dehaliboch.decomposesample.person.delete.DeletePersonDialogScreen
import kz.dehaliboch.decomposesample.util.preview.defaultPreviewComponentContext
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PersonDetailsScreen(
    component: PersonDetailsComponent,
    modifier: Modifier = Modifier.fillMaxSize(),
) {
    val state by component.state.collectAsState()
    val slots by component.slots.subscribeAsState()
    val counterState by component.counterState.collectAsState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = state.title,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = component::clickOnPerson),
            fontSize = 22.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = counterState.toString(),
            modifier = Modifier.fillMaxWidth(),
            fontSize = 32.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = component::deletePersonClick
        ) {
            Text("Delete Person")
        }
    }

    when (slots.child?.instance) {
        is PersonDetailsComponent.Child.Delete -> {
            DeletePersonDialogScreen(
                onDismiss = component::dismissDeletePersonDialog,
                onConfirm = component::deleteConfirm,
            )
        }

        else -> Unit
    }
}

@Preview(showBackground = true)
@Composable
private fun PersonDetailsScreenPreview() {

    PersonDetailsScreen(
        component = PersonDetailsComponent(
            componentContext = defaultPreviewComponentContext(),
            person = Person(0, "asd"),
        ),
    )
}