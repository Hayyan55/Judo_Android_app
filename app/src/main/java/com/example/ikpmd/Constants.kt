package com.example.ikpmd

object Constants {

    val name_waza: String = "a name"
    val pic_waza: String = "d"

    fun getMoves(): ArrayList<Waza> {

        val movesList = ArrayList<Waza>()

        val move1 = Waza(
            1,
            "Kata Guruma　肩車",
            R.drawable.kata_guruma,
            "'Kata' means 'shoulder', 'guruma' means wheel"
        )

        val move2 = Waza(
            2,
            "Osoto Gari　大外刈",
            R.drawable.osoto_gari,
            "'Kata' means 'shoulder', 'guruma' means wheel"
        )

        val move3 = Waza(
            3,
            "Sankaku　三角",
            R.drawable.sankaku,
            "'Kata' means 'shoulder', 'guruma' means wheel"
        )

        val move4 = Waza(4,
            "Tai Otoshi　体落",
            R.drawable.tai_otoshi,
            "'Kata' means 'shoulder', 'guruma' means wheel"
        )

        val move5 = Waza(5,
            "Uchimata　内股",
            R.drawable.uchimata,
            "'Kata' means 'shoulder', 'guruma' means wheel"
        )

        val move6 = Waza(6,
            "Yoko Tomoe Nage　横巴投",
            R.drawable.yoko_tomoe_nage,
            "'Kata' means 'shoulder', 'guruma' means wheel"
        )

        movesList.add(move1)
        movesList.add(move2)
        movesList.add(move3)
        movesList.add(move4)
        movesList.add(move5)
        movesList.add(move6)

        return movesList
    }

//    fun getWazaNames(): ArrayList<String> {
//
//        val namesList = ArrayList<String>()
//
//        for (waza in getMoves()) {
//            val itemName = waza.waza_name
//            namesList.add(itemName)
//        }
//
//        return namesList
//    }
}
