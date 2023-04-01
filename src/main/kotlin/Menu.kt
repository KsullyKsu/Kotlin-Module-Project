abstract class Menu(
    private val typeElement: TitleTypes,
    private val archName: String?
) {
    fun onCreate() {
        UtilFun.printArchiveNotesHead(typeElement)
        if (typeElement == TitleTypes.NOTES) {
            archName?.let { println("Работа с архивом: $archName") }
        }
        UtilFun.printContext(typeElement)
        when (UtilFun.waitUserResponse(UserResponseType.SELECTED_MENU, 1, 3)) {
            TitleNamesText.EXIT.order.toString() -> {
                goPrevious()
            }
            TitleNamesText.CREATE_ARCHIVE.order.toString() -> {
                createElement()
            }
            TitleNamesText.SELECT_ARCHIVE.order.toString() -> {
                showAllElements()
            }
        }
    }

    abstract fun goPrevious()
    abstract fun goNext()
    abstract fun createElement()
    abstract fun selectElement()
    abstract fun showAllElements()
}