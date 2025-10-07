package kz.dehaliboch.decomposesample.person.list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.statekeeper.ExperimentalStateKeeperApi
import com.arkivanov.essenty.statekeeper.saveable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.Serializable
import kz.dehaliboch.decomposesample.Person

class PersonsComponent(
    componentContext: ComponentContext,
    private val onAddNewPersonClick: () -> Unit,
    private val onPersonClick: (Person) -> Unit,
) : ComponentContext by componentContext {

//    @OptIn(ExperimentalStateKeeperApi::class)
//    private val _state: MutableStateFlow<State> by stateKeeper.saveable(
//        serializer = State.serializer(),
//        state = { state.value },
//        init = { MutableStateFlow(it ?: State()) }
//    )

    @OptIn(ExperimentalStateKeeperApi::class)
    private val _state: MutableStateFlow<State> by saveable(
        serializer = State.serializer(),
        state = { state.value },
        init = { MutableStateFlow(it ?: State()) }
    )

    val state: StateFlow<State> = _state.asStateFlow()

    fun clickOnPerson(person: Person) {
        onPersonClick(person)
    }

    fun updatePerson(person: Person) {
        _state.update {
            it.copy(it.items + person)
        }
    }

    fun addNewPersonClick() {
        onAddNewPersonClick()
    }

    fun deletePerson(person: Person) {
        _state.update {
            it.copy(it.items - person)
        }
    }

    @Serializable
    data class State(
        val items: List<Person> = emptyList()
    )

    companion object {
        const val KEY = "PersonsComponent"
    }
}