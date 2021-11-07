package com.github.ferumbot.specmarket.bots.core

import org.telegram.telegrambots.meta.api.objects.Update

fun Update.isCommand(): Boolean {
    if (hasCallbackQuery()) {
        return true
    }
    if (hasMessage()) {
        return true
    }
    return false
}

fun Update.getCommandAlias(): String {
    if (hasCallbackQuery()) {
        return callbackQuery.data
    }
    if (hasMessage()) {
        return message.text
    }
    return ""
}

fun Update.isText(): Boolean {
    if (!hasMessage()) {
        return false
    }
    return true
}

fun Update.getUserId(): Long {
    if (hasMessage()) {
        return message.from.id
    }
    if (hasShippingQuery()) {
        return shippingQuery.from.id
    }
    if (hasPreCheckoutQuery()) {
        return preCheckoutQuery.from.id
    }
    if (hasInlineQuery()) {
        return inlineQuery.from.id
    }
    if (hasChosenInlineQuery()) {
        return chosenInlineQuery.from.id
    }
    if (hasMyChatMember()) {
        return myChatMember.from.id
    }
    if (hasChannelPost()) {
        return channelPost.from.id
    }
    if (hasChatMember()) {
        return chatMember.from.id
    }
    if (hasCallbackQuery()) {
        return callbackQuery.from.id
    }
    if (hasEditedChannelPost()) {
        return editedChannelPost.from.id
    }
    if (hasEditedMessage()) {
        return editedMessage.from.id
    }
    if (hasPollAnswer()) {
        return pollAnswer.user.id
    }
    return 0L
}

fun Update.getChatId(): Long {
    if (hasMessage()) {
        return message.chatId
    }
    if (hasMyChatMember()) {
        return myChatMember.chat.id
    }
    if (hasChannelPost()) {
        return channelPost.chatId
    }
    if (hasChatMember()) {
        return chatMember.chat.id
    }
    if (hasCallbackQuery()) {
        return callbackQuery.message.chatId
    }
    if (hasEditedChannelPost()) {
        return editedChannelPost.chatId
    }
    if (hasEditedMessage()) {
        return editedMessage.chatId
    }
    return 0L
}