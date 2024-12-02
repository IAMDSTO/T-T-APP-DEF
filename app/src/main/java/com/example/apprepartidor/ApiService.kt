import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// Solicitud y respuesta
data class LoginRequest(val email: String, val password: String)
data class LoginResponse(
    val success: Boolean,
    val message: String,
    val data: UserData?
)
data class UserData(
    val id: Int,
    val nombreUsuario: String,
    val email: String,
    val rolID: Int
)

interface ApiService {
    @POST("loginAndroid.php")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}
