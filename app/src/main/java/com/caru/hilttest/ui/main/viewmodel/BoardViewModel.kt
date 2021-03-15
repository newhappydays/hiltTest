package com.caru.hilttest.ui.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.caru.hilttest.model.User
import com.caru.hilttest.repository.UserRepository
import kotlinx.coroutines.launch

class BoardViewModel @ViewModelInject constructor(
        private val repository: UserRepository
): ViewModel() {

    var itemLiveData = MutableLiveData<List<User>>()

    var userList : LiveData<List<User>> = repository.userList.asLiveData()

    var scrollLiveData = MutableLiveData<Int>()

    fun itemList(){
        var list = ArrayList<User>()
        repeat(30){ count ->
            list.add(User(id = count, name = "흠$count", age = count))
        }
        itemLiveData.postValue(list)
    }

    fun userInsert(user : User) = viewModelScope.launch {
        repository.insert(user)
    }
}
