package kz.dehaliboch.decomposesample.person.detail

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kz.dehaliboch.decomposesample.Person

class PersonDetailsComponent(
    componentContext: ComponentContext,
    val person: Person,
) : ComponentContext by componentContext {

    val state = MutableStateFlow(State(title = person.toString()))

    fun deletePersonClick() {
        // TODO 40:14 https://www.youtube.com/watch?v=4CJJJDH4PKM&t=7s
    }


    data class State(val title: String)
}