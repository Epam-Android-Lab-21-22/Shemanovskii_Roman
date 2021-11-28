package com.beleavemebe.solevarnya

object PhraseRepository {
    val firstWord: String
        get() = firstWordList.random()

    val secondWord: String
        get() = secondWordList.random()

    private val firstWordList = listOf(
        "Советую вам",
        "Это будет страшной ошибкой -",
        "Нужно немедленно",
        "Это очень рискованно -",
        "Невозможно",
        "Лучше всего тайком",
        "Чувствую, что вы хотите",
        "Вы сами знаете, что следует",
        "Повелеваю",
    )

    private val secondWordList = listOf(
        "заняться этим прямо сейчас.",
        "ещё раз всё обдумать.",
        "предусмотреть путь для отступления.",
        "прыгнуть в омут с головой.",
        "поторопиться с принятием решения.",
        "забыть об этом.",
        "сделать, но никому не рассказывать.",
        "рассказать об этом другу и послушать его совета.",
        "послушать меня и сделать наоборот.",
        "перестать спрашивать у бесполезного меня и принять решение самостоятельно. В КОИ-ТО ВЕКИ!",
    )
}