fun main() {
    val userArchive = mutableListOf<UserArchives>()
    while (true) { Archive(userArchive).onCreate() }
}
data class UserArchives(val archiveName: String, val notesList: MutableList<UserNotes>)
data class UserNotes(val noteName: String, val noteText: String)