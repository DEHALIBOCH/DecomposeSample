package kz.dehaliboch.decomposesample.person.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kz.dehaliboch.decomposesample.Person
import kz.dehaliboch.decomposesample.util.preview.defaultPreviewComponentContext
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PersonsScreen(
    component: PersonsComponent,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val state by component.state.collectAsState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            items(state.items) { person ->
                PersonItem(person, component::clickOnPerson)
            }
        }

        Button(
            onClick = component::addNewPersonClick
        ) {
            Text("Add new person")
        }
    }
}

@Composable
private fun PersonItem(person: Person, onPersonClick: (Person) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(
                onClick = {
                    onPersonClick.invoke(person)
                }
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 1.dp,
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Name: ${person.name}")
            Spacer(Modifier.height(8.dp))
            Text("Age: ${person.age}")
        }
    }
}

@Preview()
@Composable
private fun PersonItemPreview() {
    PersonItem(
        Person(age = 11, name = "Zxc"),
        {}
    )
}

@Preview(showBackground = true)
@Composable
private fun PersonDetailsScreenPreview() {

    PersonsScreen(
        component = PersonsComponent(
            componentContext = defaultPreviewComponentContext(),
            onAddNewPersonClick = { },
            onPersonClick = { }
        )
    )
}