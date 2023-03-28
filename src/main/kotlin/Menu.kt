import NoteAdd.TitleNamesText.*

abstract class Menu(
    private val typeElement: NoteAdd.TitleTypes,
    private val archName: String?
) {
    fun onCreate() {
        NoteAdd.printArchiveNotesHead(typeElement)
        if (typeElement.name == NoteAdd.TitleTypes.NOTES.toString()) {
            archName?.let { println("Работа с архивом: $archName") }
        }
        NoteAdd.printContext(typeElement)
        when (NoteAdd.waitUserResponse(NoteAdd.UserResponseType.SELECTED_MENU, 1, 3)) {
            EXIT.order.toString() -> { goPrevious() }
            CREATE_ARCHIVE.order.toString() -> { createElement() }
            SELECT_ARCHIVE.order.toString() -> { showAllElements() }
        }
    }
    abstract fun goPrevious()
    abstract fun goNext()
    abstract fun createElement()
    abstract fun selectElement()
    abstract fun showAllElements()
}