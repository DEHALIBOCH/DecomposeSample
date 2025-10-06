package kz.dehaliboch.decomposesample.root

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import kz.dehaliboch.decomposesample.person.create.CreatePersonScreen
import kz.dehaliboch.decomposesample.person.detail.PersonDetailsScreen
import kz.dehaliboch.decomposesample.person.list.PersonsScreen

@Composable
fun RootScreen(component: RootComponent) {

    val stack by component.stack.subscribeAsState()

    Children(
        stack = stack,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.systemBars)
            .padding(16.dp),
    ) { child ->
        when (val instance = child.instance) {
            is RootComponent.Child.CreatePerson -> {
                CreatePersonScreen(instance.component)
            }

            is RootComponent.Child.PersonDetails -> {
                PersonDetailsScreen(instance.component)
            }

            is RootComponent.Child.Persons -> {
                PersonsScreen(instance.component)
            }
        }
    }
}