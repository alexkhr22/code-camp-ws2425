import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import com.google.firebase.functions.dagger.assisted.Assisted
import com.google.firebase.functions.dagger.assisted.AssistedInject
import com.uebung_basics.network.RetrofitInstance
import kotlinx.coroutines.delay
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

@HiltWorker
class AppWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        try {
            while (true) {
                // Solange die Coroutine aktiv ist, wird hier der Shuffling-Aufruf gemacht
                val response = RetrofitInstance.api.createNewDeck()
                if (response.success) {
                    Log.d("AppWorker", "Deck erfolgreich gescheffelt: ${response.deck_id}")
                } else {
                    Log.e("AppWorker", "Fehler beim Shuffeln des Decks")
                }


                delay(2000)
            }
        } catch (e: Exception) {
            Log.e("AppWorker", "Fehler beim Shuffeln: ${e.message}")
            return Result.failure() // Fehler im Worker
        }
        // Erfolgreiche Beendigung der Methode
        return Result.success()
    }
}