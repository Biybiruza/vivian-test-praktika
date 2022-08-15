package com.example.viviantest.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viviantest.core.Resource
import com.example.viviantest.data.ApiInterface
import com.example.viviantest.data.GenericResponse
import com.example.viviantest.data.PostUser
import com.example.viviantest.data.login.User
import com.example.viviantest.settings.Settings
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class SignInViewModel(private val api: ApiInterface, private val settings: Settings) : ViewModel() {
    private var mutableUser: MutableLiveData<Resource<GenericResponse<User>>> = MutableLiveData()
    val user: LiveData<Resource<GenericResponse<User>>>
        get() = mutableUser

    private val compositeDisposable = CompositeDisposable()

    fun login(postUser: PostUser) {
        mutableUser.value = Resource.loading()
        compositeDisposable.add(
            api.signIn(postUser)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    {
                        if (it.success) {
                            settings.role = it.payload.role
                            settings.token = it.payload.token
                            settings.postName = it.payload.name
                            settings.userId = it.payload.employeeId
                            settings.branchId = it.payload.branchInfoSignIn.branchId
                            settings.status = it.payload.branchInfoSignIn.warehouse
                            mutableUser.value = Resource.success(it)
                        } else {
                            mutableUser.value = Resource.error(it.message)
                        }
                    },
                    {
                        mutableUser.value = Resource.error(it.localizedMessage)
                    }
                )
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
