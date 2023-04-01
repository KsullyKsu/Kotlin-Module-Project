class Notes(private val userNote: MutableList<UserNotes>, archName: String) :
    Menu(TitleTypes.NOTES, archName) {
    override fun goPrevious() {
        println("Возврат в меню архивов")
    }

    override fun createElement() {
        print("Введите наименование заметки: ")
        val name = UtilFun.waitUserResponse(UserResponseType.TEXT, 0, 0)
        print("Введите текст заметки: ")
        val text = UtilFun.waitUserResponse(UserResponseType.TEXT, 0, 0)
        userNote.add(UserNotes(name, text))
        println("Наименование: $name")
        println("Текст: $text")
        onCreate()
    }

    override fun selectElement() {
        var uS: Int
        while (true) {
            uS = UtilFun.waitUserResponse(
                UserResponseType.SELECTED_MENU,
                1,
                userNote.size + 1
            )
                .toInt()
            if (uS > userNote.size + 1) println("Такой заметки нет")
            if (uS == 1) onCreate()
            else break
        }
        if (uS > 1) {
            showSelectedNote(userNote[uS - 2])
            onCreate()
        }
    }

    override fun showAllElements() {
        UtilFun.printAllNotesHead()
        if (userNote.size > 0) {
            println("1. Выход")
            var cnt = 1
            for (i in userNote) {
                println("${++cnt}. Заметка: ${i.noteName}")
            }
            selectElement()
        } else {
            println("Список заметок пуст")
            onCreate()
        }
    }

    private fun showSelectedNote(note: UserNotes?) {
        note?.let {
            println("Наименование заметки: ${it.noteName}")
            println("Текст заметки: ${it.noteText}")
        }
    }

    override fun goNext() {}
}