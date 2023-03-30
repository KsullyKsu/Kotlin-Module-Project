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