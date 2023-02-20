package ru.oktemsec.audioguide.questgame

class QuestRepository {
    val exhibitsList: ArrayList<Exhibit> = arrayListOf(
        Exhibit(
            name = "Красное знамя",
            description = "Ученическая бригада по заготовке кормов для общественного скота в годы войны становилась победителем в республиканском  социалистическом соревновании и была награждена Красным Знаменем ОК ВЛКСМ, наркомата просвещения и наркомата земледелия ЯАССР. Это Красное Знамя, как свидетельство трудового вклада школьников в Победу, хранится в этом зале.",
            prompt = "Большое, прямоугольное, оранжевое",
            qrCode = "https://xn--90ahbflhjgobv0ae.xn--p1ai/%D1%8D%D0%BA%D1%81%D0%BF%D0%BE%D0%BD%D0%B0%D1%82%D1%8B/#redflag"
        ),

        Exhibit(
            name = "Маллаах иһит",
            description = "Береста, ивовые прутья, конский волос, бисер. Высота 15 см , диаметр 18 см. Предположительно, конец 19 века. Автор неизвестен. Экспонат передан в дар музею  Григорьевой Г.К.",
            prompt = "Высота 15 см , диаметр 18 см",
            qrCode = "https://xn--90ahbflhjgobv0ae.xn--p1ai/%D1%8D%D0%BA%D1%81%D0%BF%D0%BE%D0%BD%D0%B0%D1%82%D1%8B/#redflag"
        ),
    )
}