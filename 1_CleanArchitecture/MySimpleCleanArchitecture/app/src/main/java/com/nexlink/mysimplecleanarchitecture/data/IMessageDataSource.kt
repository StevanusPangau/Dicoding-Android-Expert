package com.nexlink.mysimplecleanarchitecture.data

import com.nexlink.mysimplecleanarchitecture.domain.MessageEntity

interface IMessageDataSource {
    fun getMessageFromSource(name: String): MessageEntity
}