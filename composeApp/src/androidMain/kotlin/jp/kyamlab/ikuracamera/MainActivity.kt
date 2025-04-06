package jp.kyamlab.ikuracamera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.google.firebase.Firebase
import com.google.firebase.vertexai.vertexAI
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val generativeModel = Firebase.vertexAI.generativeModel("gemini-2.0-flash")
        // Provide a prompt that contains text
        val prompt = "Write a story about a magic backpack."

        lifecycleScope.launch {
            // To generate text output, call generateContent with the text input
            val response = generativeModel.generateContent(prompt = prompt)
            print(response.text)
        }

        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}