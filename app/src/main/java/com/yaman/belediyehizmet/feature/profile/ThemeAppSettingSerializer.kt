import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.yaman.belediyehizmet.feature.profile.ThemeAppSettings
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object ThemeAppSettingSerializer : Serializer<ThemeAppSettings> {
    override val defaultValue: ThemeAppSettings = ThemeAppSettings()

    override suspend fun readFrom(input: InputStream): ThemeAppSettings {
        return try {
            Json.decodeFromString(
                ThemeAppSettings.serializer(),
                input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            throw CorruptionException("Unable to read ThemeAppSettings", e)
        }
    }

    override suspend fun writeTo(t: ThemeAppSettings, output: OutputStream) {
        output.write(Json.encodeToString(ThemeAppSettings.serializer(), t).encodeToByteArray())
    }
}
