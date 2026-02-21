package com.abrsoftware.fletiotestapp.view.util

/**
 * Representa un evento único en la UI que no debe ser tratado como estado persistente
 * Usada para mostrar Snackbars, Toasts o navegar sin afectar el estado
 */
sealed class UiEvent {
    data class ShowError(val message: String) : UiEvent()
    data class ShowSuccess(val message: String) : UiEvent()
    data class ShowMessage(val message: String) : UiEvent()
    object DismissError : UiEvent()
}
