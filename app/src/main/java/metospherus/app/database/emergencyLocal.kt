package metospherus.app.database

fun emergencyLocal(
    country: String?,
    onReturnEmergencyContact: (String) -> Unit
) {
    if (country!!.contains("Uganda", ignoreCase = true)) {
        onReturnEmergencyContact("999")
    }
}