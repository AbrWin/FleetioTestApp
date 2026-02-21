/**
 * GUÍA DE MIGRACIÓN: Reactive Programming Standards
 * 
 * PROBLEMA ANTERIOR:
 * - Mix de mutableStateOf (Compose) y Flow (Coroutines)
 * - Inconsistencia en cómo se accede al estado entre ViewModels
 * - Dificultad para testear debido a acceso directo a var
 * 
 * SOLUCIÓN IMPLEMENTADA:
 * 
 * 1. ESTADO PERSISTENTE → StateFlow
 *    ---------------------
 *    Antes:
 *    var state: CommentState by mutableStateOf(CommentState())
 *    
 *    Ahora:
 *    private val _state = MutableStateFlow(CommentState())
 *    val state: StateFlow<CommentState> = _state.asStateFlow()
 *    
 *    Actualizar:
 *    _state.value = newState
 * 
 * 2. EVENTOS ÚNICOS → SharedFlow
 *    ---------------
 *    Usado para operaciones que no deben ser estado persistente:
 *    - Mostrar Toasts/Snackbars
 *    - Navegar a otra pantalla
 *    - Iniciar efectos secundarios
 *    
 *    private val _uiEvent = MutableSharedFlow<UiEvent>()
 *    val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()
 *    
 *    Emitir evento:
 *    _uiEvent.emit(UiEvent.ShowSuccess("Éxito"))
 * 
 * 3. EN LA UI (Composable)
 *    ------------------
 *    Convertir StateFlow a State:
 *    val state by viewModel.state.collectAsState()
 *    
 *    Escuchar eventos:
 *    LaunchedEffect(Unit) {
 *        viewModel.uiEvent.collect { event ->
 *            when (event) {
 *                is UiEvent.ShowError -> showErrorSnackbar(event.message)
 *                is UiEvent.ShowSuccess -> showSuccessSnackbar(event.message)
 *            }
 *        }
 *    }
 * 
 * ARQUITECTURA RECOMENDADA:
 * ========================
 * 
 * @HiltViewModel
 * class MyViewModel @Inject constructor(
 *     private val repository: MyRepository
 * ) : ViewModel() {
 * 
 *     // Estado principal (persistente)
 *     private val _state = MutableStateFlow(MyState())
 *     val state: StateFlow<MyState> = _state.asStateFlow()
 *     
 *     // Eventos (no persistentes)
 *     private val _uiEvent = MutableSharedFlow<UiEvent>()
 *     val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()
 *     
 *     fun loadData(id: String) {
 *         // 1. Validar entrada
 *         val validation = InputValidators.validateVehicleId(id)
 *         if (validation !is ValidationResult.Valid) {
 *             _uiEvent.emit(UiEvent.ShowError(
 *                 (validation as ValidationResult.Invalid).message
 *             ))
 *             return
 *         }
 *         
 *         // 2. Establecer estado de carga
 *         _state.value = _state.value.copy(isLoading = true)
 *         
 *         // 3. Cargar datos
 *         viewModelScope.launch {
 *             try {
 *                 val result = repository.getData(id)
 *                 _state.value = _state.value.copy(
 *                     data = result,
 *                     isLoading = false
 *                 )
 *                 _uiEvent.emit(UiEvent.ShowSuccess("Datos cargados"))
 *             } catch (e: Exception) {
 *                 _state.value = _state.value.copy(isLoading = false)
 *                 _uiEvent.emit(UiEvent.ShowError(e.message ?: "Error"))
 *             }
 *         }
 *     }
 * }
 * 
 * VENTAJAS DE ESTA ARQUITECTURA:
 * ==============================
 * ✅ Código testeable sin dependencias de Compose
 * ✅ Separación clara entre estado persistente y eventos
 * ✅ Compatible con corrutinas
 * ✅ Recomposición eficiente en Compose
 * ✅ Manejo de ciclo de vida correcto
 * ✅ Soporte para backpressure en SharedFlow
 */
