package ru.oktemsec.audioguide

import ru.oktemsec.audioguide.exhibit.Exhibit

class Repository {
    val expositionsList: List<Exposition> = listOf(
            Exposition(
                R.string.zal_istoria_obraz,
                R.drawable.zal_istoria_obraz,
                "Экспозиция раскрывает становление и развитие системы образования в Хангаласском улусе, начиная с 1874 г. Об известных деятелях образования, становление школьного и дошкольного образования, педагогические династии улуса, история улусного управления образования и др. Выставлена также подборка книг об учителях и отдельных школах улуса и выставка технических средств обучения. Здесь также представлена история развития системы дополнительного образования и пионерского движения. Один из основных стендов посвящен педагогическим династиям Хангаласского улуса.",
                listOf<Sound>(
                    Sound("Добро пожаловать", R.raw.welcome),
                    Sound("Главное меню", R.raw.main_menu),
                )
            ),
            Exposition(
                R.string.zal_alex_narod,
                R.drawable.zal_alex_narod,
                "Экспозиция музея образования Хангаласского улуса открывается залом, посвященным деятельности Алексеевского народного училища (1874-1917гг). Здесь представлены копии фотографий, архивных документов, отчетов училища, газетных статей, свидетельствующих о деятельности народных учителей и сведения о выпускниках училища того времени. Среди экспонатов есть фотографии, архивные документы и сведения о первом учителе нашего улуса Неустроеве В.Г., о Широких А.Д.- общественно-политическом и государственном деятеле, благодаря которому построено в 1914 г. здание школы, в котором находится музей. Интересным документом является выдержка из статьи А. Линькова «Деятели по народному образованию» (журнал «Сибирский архив»), в котором указана дата открытия первой школы улуса – 6 января 1874 г. в 11ч. утра в здании, построенном на средства «…инородца Ивана Павлова» из Одунинского наслега.",
                listOf<Sound>(
                    Sound("Добро пожаловать", R.raw.welcome),
                    Sound("Главное меню", R.raw.main_menu),
                )
            ),
            Exposition(
                R.string.zal_class_soviet,
                R.drawable.zal_class_soviet,
                "Обстановка учебного класса 50 — х годов воссоздана по старой фотографии, снятой в этом классе примерно в 1953 г. Над школьной доской обязательный для того времени портрет В.И. Ленина с заветом: «Учиться, учиться и учиться». На учительском столе — старинный школьный колокольчик. Интересен тот факт, что сохранился шкаф того времени. Учебники, школьные парты и деревянная классная доска, учительский стол и стул, элементы пионерской атрибутики, касса букв и слогов (на тканевой основе) и другие предметы создают обстановку школьной жизни советской эпохи. В этом зале также есть стенд «Иитиллибит иэстээхпит» («В долгу перед школой, нас воспитавшей») отражающий заботу о родной школе выпускника 1953г., первого Президента РС(Я) М.Е. Николаева.",
                listOf<Sound>(
                    Sound("Добро пожаловать", R.raw.welcome),
                    Sound("Главное меню", R.raw.main_menu),
                )
            ),
            Exposition(
                R.string.zal_school_in_soviet,
                R.drawable.zal_school_in_soviet,
                "Экспозиции этого зала раскрывают историю школы в советский период. В годы установления Советской власти школа стала центром политических событий в улусе. Заведующий и учитель школы Николаев Г.П. создает первые в Западно-Кангаласском улусе пионерский отряд, комсомольскую организацию и первую партийную ячейку. Основная экспозиция «Школа в советский период: хроника, события, факты» кроме этих событий раскрывает деятельность Октемской неполной средней школы в период Великой Отечественной войны 1941 – 45 гг.",
                listOf<Sound>(
                    Sound("Добро пожаловать", R.raw.welcome),
                    Sound("Главное меню", R.raw.main_menu),
                )
            ),
            Exposition(
                R.string.zal_new_history_school,
                R.drawable.zal_new_history_school,
                "С 1995 г., когда в с. Чапаево было построено новое здание школы, начинается новый виток развития школы. Октемская неполная средняя школа становится средней, приобретает статус школы гимназии, а затем лицея. С получением статуса лицея четко определилась основная цель учебного заведения: подготовка выпускников, ориентированных на технические специальности. Экспозиции зала раскрывают этапы развития и становления школы как инновационного учебного заведения: школа- гимназия, лицей и научно – образовательный центр. Октемский лицей имел свои символы: гимн, флаг и эмблему. Одна из экспозиций посвящена символике лицея и научно-образовательного центра.",
                listOf<Sound>(
                    Sound("Добро пожаловать", R.raw.welcome),
                    Sound("Главное меню", R.raw.main_menu),
                )
            ),
            Exposition(
                R.string.zal_kraeved_ugolok,
                R.drawable.zal_kraeved_ugolok,
                "В краеведческом отделе собраны старинные вещи, орудия труда, домашняя утварь и предметы обстановки прошлой эпохи.  Нужно отметить, что экспонаты этого отдела были собраны под руководством Слепцовой И.И., учителя ИЗО. Знакомство с экспозицией краеведческого отдела помогает посетителям ближе узнать быт советской семьи, ознакомиться с предметами быта ушедшей эпохи.",
                listOf<Sound>(
                    Sound("Добро пожаловать", R.raw.welcome),
                    Sound("Главное меню", R.raw.main_menu),
                )
            ),
    )

    val exhibitLists: List<Exhibit> = listOf(
        Exhibit("https://xn--90ahbflhjgobv0ae.xn--p1ai/%D1%8D%D0%BA%D1%81%D0%BF%D0%BE%D0%BD%D0%B0%D1%82%D1%8B/#redflag", "Красное знамя", "https://xn--90ahbflhjgobv0ae.xn--p1ai/wp-content/uploads/2022/11/%D0%97%D0%BD%D0%B0%D0%BC%D1%8F-1024x891.jpg", "Ученическая бригада по заготовке кормов для общественного скота в годы войны становилась победителем в республиканском  социалистическом соревновании и была награждена Красным Знаменем ОК ВЛКСМ, наркомата просвещения и наркомата земледелия ЯАССР. Это Красное Знамя, как свидетельство трудового вклада школьников в Победу, хранится в этом зале."),
        Exhibit("https://xn--90ahbflhjgobv0ae.xn--p1ai/%D1%8D%D0%BA%D1%81%D0%BF%D0%BE%D0%BD%D0%B0%D1%82%D1%8B/#tuos", "Коробка вышитая бисером", "https://музейоброктем.рф/wp-content/uploads/2022/11/%D0%A2%D1%83%D0%BE%D1%81.gif", "Береста, ивовые прутья, конский волос, бисер. Высота 15 см , диаметр 18 см. Предположительно, конец 19 века. Автор неизвестен. Экспонат передан в дар музею  Григорьевой Г.К."),
    )
}