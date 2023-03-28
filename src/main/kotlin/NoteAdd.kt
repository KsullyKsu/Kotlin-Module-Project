import java.util.*
import kotlin.collections.ArrayList

object NoteAdd {
    private val menu: MutableMap<Int, List<String>> = mutableMapOf()
    init { onCreate() }

    fun printContext(type: TitleTypes) {
        when (type.name) {
            TitleTypes.HEAD.toString() -> printHeadTitle()
            TitleTypes.ARCHIVES.toString() -> printArchiveTitle()
            TitleTypes.NOTES.toString() -> printNotesTitle()
        }
    }
    fun printArchiveNotesHead(titleType: TitleTypes) {
        val item: MutableList<String> = mutableListOf()
        val s = titleType.text.expandBySymbols(10, ' ')
        item.add("Меню $s")
        printList(item)
    }
    fun printAllArchivesHead() {
        val item: MutableList<String> = mutableListOf()
        item.add("Список архивов:")
        printList(item)
    }
    fun printAllNotesHead() {
        val item: MutableList<String> = mutableListOf()
        item.add("Список заметок:")
        printList(item)
    }
    private fun onCreate() {
        menu[TitleTypes.HEAD.ordinal] = createMenuHead()
        menu[TitleTypes.ARCHIVES.ordinal] = createArchivesTitles()
        menu[TitleTypes.NOTES.ordinal] = createNotesTitles()
    }
    private fun createMenuHead(): List<String> {
        val menu: MutableList<String> = mutableListOf()
        menu.add("Приложение для заметок")
        return menu
    }
    private fun printHeadTitle() {
        printItem(TitleTypes.HEAD.ordinal)
    }
    private fun printArchiveTitle() {
        printItem(TitleTypes.ARCHIVES.ordinal)
    }
    private fun printNotesTitle() {
        printItem(TitleTypes.NOTES.ordinal)
    }
    private fun printList(item: MutableList<String>) {
        item.let { list -> list.forEach { println(it) } }
    }
    private fun printItem(item: Int?) {
        if (item != null) {
            menu[item]?.let { list -> list.forEach { println(it) } }
        }
    }
    private fun createArchivesTitles(): List<String> {
        val titles: ArrayList<String> = arrayListOf()
        titles.add(oneTitleCreate(TitleNamesText.EXIT.order, TitleNamesText.EXIT.text))
        titles.add(oneTitleCreate(TitleNamesText.CREATE_ARCHIVE.order, TitleNamesText.CREATE_ARCHIVE.text))
        titles.add(oneTitleCreate(TitleNamesText.SELECT_ARCHIVE.order, TitleNamesText.SELECT_ARCHIVE.text))
        return titles
    }
    private fun createNotesTitles(): List<String> {
        val titles: ArrayList<String> = arrayListOf()
        titles.add(oneTitleCreate(TitleNamesText.EXIT.order, TitleNamesText.EXIT.text))
        titles.add(oneTitleCreate(TitleNamesText.CREATE_NOTE.order, TitleNamesText.CREATE_NOTE.text))
        titles.add(oneTitleCreate(TitleNamesText.SELECT_NOTE.order, TitleNamesText.SELECT_NOTE.text))
        return titles
    }
    private fun oneTitleCreate(num: Int, title: String): String {
        return "$num. $title"
    }
    fun waitUserResponse(waitType: UserResponseType, minVal: Int, maxVal: Int): String {
        val scan = Scanner(System.`in`)
        when (waitType.name) {
            "SELECTED_MENU" -> {
                print("Введите номер меню: ")
                while (true) {
                    val inp = scan.nextLine()
                    val num: Int? = inp.toIntOrNull()
                    if (num != null) {
                        if (num in minVal..maxVal) {
                            return inp
                        } else println("Пункта $num. в меню нет, попробуйте еще раз")
                    } else println("Некорректный ввод, попробуйте еще раз")
                }
            } else -> return scan.nextLine()
        }
    }
    enum class TitleNamesText(val order: Int, val text: String) {
        EXIT(1, "Выход"),
        CREATE_ARCHIVE(2, "Создать архив"),
        SELECT_ARCHIVE(3, "Выбрать архив"),
        CREATE_NOTE(2, "Создать заметку"),
        SELECT_NOTE(3, "Выбрать заметку"),
    }
    enum class TitleTypes(val text: String) {
        HEAD("Заголовок:"),
        ARCHIVES("Архивы:"),
        NOTES("Заметки:"),
    }
    enum class UserResponseType { SELECTED_MENU, TEXT, }
    private fun String.expandBySymbols(setLen: Int, symbol: Char): String {
        var res = this
        for (i in res.length until setLen) res += symbol.toString()
        return res
    }
}