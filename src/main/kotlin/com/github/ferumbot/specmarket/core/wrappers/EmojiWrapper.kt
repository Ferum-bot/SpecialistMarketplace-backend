package com.github.ferumbot.specmarket.core.wrappers

import com.vdurmont.emoji.EmojiManager

object EmojiWrapper {

    fun getRandomEmoji(): String {
        return EmojiManager.getAll().random().unicode
    }

    fun getRandomEmojies(count: Int): List<String> {
        val allEmojies = EmojiManager.getAll()
        if (count >= allEmojies.size) {
            return allEmojies.map { emoji -> emoji.unicode }
        }

        val resultSet: MutableSet<String> = mutableSetOf()
        while (resultSet.size != count) {
            val randomEmoji = allEmojies.random().unicode
            resultSet.add(randomEmoji)
        }

        return resultSet.toList()
    }
}