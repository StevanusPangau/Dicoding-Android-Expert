package com.nexlink.mysimplecleanarchitecture.di

import com.nexlink.mysimplecleanarchitecture.data.IMessageDataSource
import com.nexlink.mysimplecleanarchitecture.data.MessageDataSource
import com.nexlink.mysimplecleanarchitecture.data.MessageRepository
import com.nexlink.mysimplecleanarchitecture.domain.IMessageRepository
import com.nexlink.mysimplecleanarchitecture.domain.MessageInteractor
import com.nexlink.mysimplecleanarchitecture.domain.MessageUseCase

object Injection {
    fun provideUseCase(): MessageUseCase {
        val messageRepository = provideRepository()
        return MessageInteractor(messageRepository)
    }

    private fun provideRepository(): IMessageRepository {
        val messageDataSource = provideDataSource()
        return MessageRepository(messageDataSource)
    }

    private fun provideDataSource(): IMessageDataSource {
        return MessageDataSource()
    }
}