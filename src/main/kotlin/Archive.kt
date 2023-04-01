import kotlin.system.exitProcess

class Archive(private val userArchive: MutableList<UserArchives>) :
    Menu(TitleTypes.ARCHIVES, null) {
    private var currArchive = 0
    override fun goPrevious() {
        println("Пока..")
        exitProcess(1)
    }

    override fun createElement() {
        print("Введите наименование архива: ")
        val newName = UtilFun.waitUserResponse(UserResponseType.TEXT, 0, 0)
        userArchive.add(UserArchives(newName, mutableListOf()))
        println("Создан новый архив: $newName")
    }

    override fun showAllElements() {
        UtilFun.printAllArchivesHead()
        if (userArchive.size > 0) {
            println("1. Выход")
            var cnt = 1
            for (i in userArchive) {
                println("${++cnt}. ${i.archiveName}")
            }
            selectElement()
        } else {
            println("Список архивов пуст")
            onCreate()
        }
    }

    override fun selectElement() {
        var userSelected: Int
        while (true) {
            userSelected = UtilFun.waitUserResponse(
                UserResponseType.SELECTED_MENU,
                1,
                userArchive.size + 1
            ).toInt()
            if (userSelected > userArchive.size + 1) println("Такого архива нет")
            else break
        }
        if (userSelected > 1) {
            currArchive = userSelected - 2
            println("Открываю ${userArchive[currArchive].archiveName}")
            goNext()
        }
    }

    override fun goNext() {
        Notes(userArchive[currArchive].notesList, userArchive[currArchive].archiveName).onCreate()
    }
}