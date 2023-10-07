package com.xynos.talk.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xynos.talk.ui.viewmodel.ChatListViewModel
import com.xynos.talk.ui.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ChatListViewModel::class)
    abstract fun bindChatListViewModel(viewModel: ChatListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
