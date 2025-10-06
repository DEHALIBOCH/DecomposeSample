package kz.dehaliboch.decomposesample.person.list

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kz.dehaliboch.decomposesample.Person

class PersonsComponent(
    componentContext: ComponentContext,
    private val onAddNewPersonClick: () -> Unit,
    private val onPersonClick: (Person) -> Unit,
) : ComponentContext by componentContext {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun clickOnPerson(person: Person) {
        onPersonClick(person)
    }

    fun updatePerson(person: Person) {

    }

    fun addNewPersonClick() {
        onAddNewPersonClick()
    }

    fun deletePerson(person: Person) {

    }

    data class State(
        val items: List<Person> = emptyList()
    )

    companion object {
        const val KEY = "PersonsComponent"
    }
}