package kz.dehaliboch.decomposesample.person.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kz.dehaliboch.decomposesample.util.preview.defaultPreviewComponentContext
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PersonsScreen(
    component: PersonsComponent,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text("PersonsScreen")
    }
}

@Preview(showBackground = true)
@Composable
private fun PersonDetailsScreenPreview() {

    PersonsScreen(
        component = PersonsComponent(defaultPreviewComponentContext())
    )
}