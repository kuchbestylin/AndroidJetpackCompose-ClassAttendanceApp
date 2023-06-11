package com.example.classattendance_androidapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.classattendance_androidapplication.ui.theme.ClassAttendanceAndroidApplicationTheme
import com.google.gson.JsonObject
import kotlinx.coroutines.coroutineScope
import okhttp3.ResponseBody
import retrofit2.Response

class SignUpOrganization : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClassAttendanceAndroidApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting2("Android")
                }
            }
        }
    }
}

private var apiService: ApiService = RetrofitHelper.getInstance("https://localhost:7288").create(ApiService::class.java)

@Composable
fun Greeting2(name: String) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
    ) {
        // Creating a Box composable with a specified size and background color.
        Box(modifier = Modifier
            .height(230.dp)
            .fillMaxWidth()
            .background(color = Color(red = 19, green = 3, blue = 40),)
        ) {

        }
        // Adding a Spacer for vertical space.
        Spacer(modifier = Modifier.height(50.dp))
        var organizationName by remember { mutableStateOf("") }
        var emailAddress by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var repeatPassword by remember { mutableStateOf("") }
        // Creating a nested Column with alignment set to center horizontally.
        Column (
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                ) {
            Column {
                // Creating a TextField for organization name input.
                TextField(
                    value = organizationName,
                    onValueChange = { newText -> organizationName = newText },
                    label = { Text("Organisation Name") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(7,10,70, alpha = 0),
                    )
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column {
                // Creating a nested Column with alignment set to center horizontally.
                TextField(
                    value = emailAddress,
                    onValueChange = { newText -> emailAddress = newText },
                    label = { Text("Email Address") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(7,10,70, alpha = 0),
                    )
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column {
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(7,10,70, alpha = 0),
                    )
                )
            }

            var showProgressBar by remember { mutableStateOf(false) }
            Spacer(modifier = Modifier.height(20.dp))
            Column {
                TextField(
                    value = repeatPassword,
                    onValueChange = { repeatPassword = it },
                    label = { Text("Repeat Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(7,10,70, alpha = 0),
                    )
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
            Row (modifier = Modifier.align(Alignment.CenterHorizontally)){
                var data: Response<ResponseBody>? = null
                var coroutineScope = rememberCoroutineScope()
                val context = LocalContext.current
                Button(
                    modifier = Modifier
                        .height(45.dp)
                        .width(250.dp)
                        .background(color = Color(red = 143, green = 255, blue = 0))
                    ,onClick = {
                        val intent = Intent(context, SignIn::class.java)
                        context.startActivity(intent)
//                        val signUpData = SignUpData(
//                            name = organizationName,
//                            email = emailAddress,
//                            password = password
//                        )
//                        coroutineScope.launch {
//
//                            showProgressBar = true
//                            getUserByID()
//                            updateUser()
//                            deleteUser()
//                            createUser()
//                            showProgressBar = false
//                        }
                    },  colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Green
                    )) {
                    Text(text = "Register")
                }
            }
            if (showProgressBar) {
                AlertDialog(
                    onDismissRequest = { },
                    title = { Text("Loading...") },
                    text = { Text("Please wait while we load your data") },
                    buttons = {}
                )
            }
        }
    }
}

//suspend fun getUserByID() {
//    coroutineScope {
//        var result = apiService.getUserByID("2")
//        if(result.isSuccessful){
//            Log.e("000000", "getUserByID success: ${result.body()?.data}")
//        }else{
//            Log.e("000000", "getUserByID failed: ${result.message()}")
//        }
//    }
//}
//
//suspend fun updateUser(){
//    coroutineScope {
//        val body = JsonObject().apply {
//            addProperty("name", "vis-hi coding dev")
//            addProperty("Job", "android developer")
//        }
//        val result = apiService.updateUser("2", body)
//        if (result.isSuccessful){
//            Log.e("000000", "updateUser success: ${result.body()}")
//        } else{
//            Log.e("000000", "updateUser failed: ${result.message()}")
//        }
//    }
//}
//
//suspend fun deleteUser(){
//    coroutineScope {
//        val result = apiService.deleteUser("2")
//        if(result.isSuccessful){
//            Log.e("000000", "deleteUser success: ${result.body()}" )
//        } else {
//            Log.e("000000", "deleteUser failed: ${result.message()}" )
//        }
//    }
//}
//
//suspend fun createUser(){
//    coroutineScope {
//        val body = JsonObject().apply {
//            addProperty("name", "Absolute Gorgeous")
//            addProperty("job", "Senior Manager")
//        }
//        val result = apiService.createUser(body)
//        if(result.isSuccessful){
//            Log.e("000000", "CreateUser success: ${result.body()}")
//        }else{
//            Log.e("000000", "createUser field: ${result.message()}")
//        }
//    }
//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ClassAttendanceAndroidApplicationTheme {
        Greeting2("Android")
    }
}