package com.beleavemebe.solevarnya.model

object PepeAvatars {
    private val urls = listOf(
        "https://www.meme-arsenal.com/memes/a19e699a978c37b12491e61bbbb10c9f.jpg",
        "https://www.meme-arsenal.com/memes/5cade8f24829fff94b7650313871ddfb.jpg",
        "https://www.meme-arsenal.com/memes/6eeef2ebc69b498c04aa8aadf032b32f.jpg",
        "https://i.pinimg.com/474x/7a/73/fb/7a73fb338b6ded8373e322cf8fe2f9e4.jpg",
        "https://thumbnailer.mixcloud.com/unsafe/300x300/extaudio/a/9/4/4/58b0-2e09-4471-a48b-5011152da84c",
        "https://alekbo.com/wp-content/uploads/2019/07/rage-pepe-300x300.jpg",
        "http://pm1.narvii.com/6504/7960c8a3bce57228b8f6e5fd9bb562dc2b37cc9b_00.jpg",
        "https://pbs.twimg.com/profile_images/1442299386654969860/KTzEcq1z_400x400.jpg",
        "https://i1.sndcdn.com/artworks-Af2OohvaSGfqrJ7V-xcrK8w-t500x500.jpg",
        "https://cdn.frankerfacez.com/emoticon/603660/4",
        "https://sun9-8.userapi.com/impf/c840731/v840731773/1aed/KExKw7NlBb4.jpg?size=358x358&quality=96&sign=041ce0bb0af114b4f801cac3b5ece1d5&type=album",
    )

    fun getRandomAvatarUrl(): String =
        urls.random()
}

object AnimeAvatars {
    private val urls = listOf(
        "https://avatarfiles.alphacoders.com/466/thumb-1920-46621.jpg",
        "https://avatarfiles.alphacoders.com/699/thumb-1920-69905.png",
        "https://avatarfiles.alphacoders.com/832/thumb-1920-83296.png",
        "https://avatarfiles.alphacoders.com/307/thumb-1920-307658.jpg",
        "https://avatarfiles.alphacoders.com/152/thumb-1920-152197.png",
        "https://avatarfiles.alphacoders.com/667/thumb-1920-66748.jpg",
        "https://avatarfiles.alphacoders.com/260/thumb-1920-260.jpg",
        "https://avatarfiles.alphacoders.com/457/thumb-1920-45744.png",
        "https://avatarfiles.alphacoders.com/488/thumb-1920-48873.jpg",
        "https://avatarfiles.alphacoders.com/185/thumb-1920-185709.jpg",
        "https://avatarfiles.alphacoders.com/212/thumb-1920-212926.jpg",
        "https://avatarfiles.alphacoders.com/840/thumb-1920-84079.jpg",
        "https://avatarfiles.alphacoders.com/500/thumb-1920-50024.jpg",
    )

    fun getRandomAvatarUrl(): String =
        urls.random()
}