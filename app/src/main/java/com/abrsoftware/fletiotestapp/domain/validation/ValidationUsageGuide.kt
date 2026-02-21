package com.abrsoftware.fletiotestapp.domain.validation

/**
 * Guía de uso del sistema de validación centralizado
 * 
 * EJEMPLO 1: Validar ID de vehículo en Repository
 * ```kotlin
 * class VehicleRepositoryImpl(
 *     private val api: FleetioTestApi,
 *     @ApplicationContext private val context: Context
 * ) : VehicleRepository {
 * 
 *     override suspend fun getVehicles(id: String): Vehicle {
 *         when (val validation = InputValidators.validateVehicleId(id)) {
 *             is ValidationResult.Invalid -> 
 *                 throw AppException.ValidationException(validation.message)
 *             ValidationResult.Valid -> {
 *                 // Proceder con la llamada API
 *             }
 *         }
 *     }
 * }
 * ```
 * 
 * EJEMPLO 2: Usar validador en ViewModel antes de procesar
 * ```kotlin
 * @HiltViewModel
 * class MyViewModel @Inject constructor(
 *     private val repository: MyRepository
 * ) : ViewModel() {
 * 
 *     private val _state = MutableStateFlow(MyState())
 *     val state: StateFlow<MyState> = _state.asStateFlow()
 * 
 *     fun processData(input: String) {
 *         val validation = InputValidators.validateNotBlank(input, "Nombre")
 *         if (validation !is ValidationResult.Valid) {
 *             val errorMsg = (validation as ValidationResult.Invalid).message
 *             _state.value = _state.value.copy(error = errorMsg)
 *             return
 *         }
 *         // Proceder con validación exitosa
 *     }
 * }
 * ```
 * 
 * CASOS DE USO DISPONIBLES:
 * 
 * 1. validateVehicleId(id: String?) - Valida IDs de vehículos
 * 2. validateNotBlank(value: String?, fieldName: String) - Campo no vacío
 * 3. validatePageNumber(page: Int, minPage: Int) - Número de página válido
 * 4. validateEmail(email: String?) - Email válido
 * 5. validateMinLength(value: String?, minLength: Int, fieldName: String) - Longitud mínima
 * 6. validateMaxLength(value: String?, maxLength: Int, fieldName: String) - Longitud máxima
 */
object ValidationUsageGuide
