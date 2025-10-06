package kz.dehaliboch.decomposesample.person.create

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kz.dehaliboch.decomposesample.Person

class CreatePersonComponent(
    componentContext: ComponentContext,
    private val onFinish: (Person) -> Unit
) : ComponentContext by componentContext {

    private val _state = MutableStateFlow(State.DEFAULT)
    val state = _state.asStateFlow()

    fun updateName(name: String) {
        _state.update {
            it.copy(name = name)
        }
    }

    fun updateAge(age: String) {
        _state.update {
            it.copy(age = age.toIntOrNull() ?: Int.MAX_VALUE)
        }
    }

    fun finish() {
        onFinish(Person(state.value.age, state.value.name))
    }

    data class State(
        val name: String,
        val age: Int
    ) {
        companion object {
            val DEFAULT = State("", 0)
        }
    }
}