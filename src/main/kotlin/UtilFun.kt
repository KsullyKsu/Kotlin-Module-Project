import java.util.*
import kotlin.collections.ArrayList

object UtilFun {
    private val menu: MutableMap<Int, List<String>> = mutableMapOf()

    init {
        onCreate()
    }

    fun printContext(type: TitleTypes) {
        when (type) {
            TitleTypes.HEAD -> printHeadTitle()
            TitleTypes.ARCHIVES -> printArchiveTitle()
            TitleTypes.NOTES -> printNotesTitle()
        }
    }

    fun printArchiveNotesHead(titleType: TitleTypes) {
        val s = titleType.text.expandBySymbols(10, ' ')
        printList(mutableListOf("Меню $s"))
    }

    fun printAllArchivesHead() {
        printList(mutableListOf("Список архивов:"))
    }

    fun printAllNotesHead() {
        printList(mutableListOf("Список заметок:"))
    }

    private fun onCreate() {
        menu[TitleTypes.ARCHIVES.ordinal] = createArchivesTitles()
        menu[TitleTypes.NOTES.ordinal] = createNotesTitles()
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
            }
            else -> return scan.nextLine()
        }
    }

    private fun String.expandBySymbols(setLen: Int, symbol: Char): String {
        var res = this
        for (i in res.length until setLen) res += symbol.toString()
        return res
    }
}