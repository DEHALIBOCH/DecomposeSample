package kz.dehaliboch.decomposesample.person.list

import com.arkivanov.decompose.ComponentContext

class PersonsComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {


    companion object {
        const val KEY = "PersonsComponent"
    }
}