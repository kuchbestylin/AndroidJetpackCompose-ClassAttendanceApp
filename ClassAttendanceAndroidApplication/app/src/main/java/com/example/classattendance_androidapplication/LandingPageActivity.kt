package com.example.classattendance_androidapplication

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.room.Room
import com.example.classattendance_androidapplication.ui.theme.ClassAttendanceAndroidApplicationTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.LocalDate

class LandingPageActivity : ComponentActivity() {
    var courses: List<User> = listOf()
    val gson = Gson()
    val listType = object : TypeToken<List<User>>() {}.type
    var activities: JsonArray? = null

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var applicationContext: Context = applicationContext
        setContent {
            ClassAttendanceAndroidApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {

                    val db = Room.databaseBuilder(
                        applicationContext,
                        AppDatabase::class.java, "AppDatabase"
                    ).build()
                    var coroutineScope = rememberCoroutineScope()
                    fun getActivities(callback: (JsonArray?) -> Unit) {
                        coroutineScope.launch {
                            courses = db.getUserDao().getAllUsers()
                            activities = getActivities()
                            callback(activities)
                            Log.e("000000", "Adding Courses deleteUser success: ${activities}" )
                            db.getUserDao().addUser(courses)
                        }
                    }

                    getActivities { activities ->
                        courses = gson.fromJson(activities, listType)
                        Log.e("000000", "deleteUser success: ${activities}" )

                    }
                    Greeting4("Android", courses, db)
                }
            }
        }
    }
}

private var apiService: ApiService = RetrofitHelper.getInstance("http://10.199.110.120:5141/").create(ApiService::class.java)

@Composable
fun FormDialog() {
    val openDialog = remember { mutableStateOf(false) }
    var activityName by remember { mutableStateOf("") }
    val checkedState = remember { mutableStateOf(false) }
    var coroutineScope = rememberCoroutineScope()
    var body: NewActivityObject = NewActivityObject("", false)
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Add Activity")
            },
            text = {
                Column (modifier = Modifier){
                    Row(modifier = Modifier
                        .background(color = Color.White)
                        .fillMaxWidth()
                        .height(1.dp)){
                        Text(text = "wrgggwrw")
                    }
                    TextField(
                        value = activityName,
                        onValueChange = { newText -> activityName = newText },
                        label = { Text("Enter name of Course/Activity") },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color(7,30,70, alpha = 0),
                        ),
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = checkedState.value,
                            onCheckedChange = { checkedState.value = it },
                            colors = CheckboxDefaults.colors(uncheckedColor = Color.Gray, checkedColor = Color(red = 143, green = 255, blue = 0))
                        )
                        Text(text = "Let Your Organisation add Participants")
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                        coroutineScope.launch{
                            body.name = activityName
                            body.booleanValue = checkedState.value
                            createActivity(body)
                            activityName = ""
                            checkedState.value = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors( backgroundColor = Color(red = 143, green = 255, blue = 0)),
                    modifier = Modifier.width(100.dp)
                ) {
                    Text(text = "OK")
                }
            }
        )
    }
    Button(onClick = { openDialog.value = true; },
        modifier = Modifier.size(70.dp),
        colors = ButtonDefaults.buttonColors( backgroundColor = Color(red = 143, green = 255, blue = 0)), shape = RoundedCornerShape(15.dp)
    ) {
        Icon(
            imageVector = Icons.Sharp.Add,
            contentDescription = null,
            modifier = Modifier
                .size(45.dp),
            tint = Color.White

        )
    }
}



@SuppressLint("NewApi")
@Composable
fun Greeting4(name: String, courses: List<User>, db: AppDatabase) {
    /**
     * This is the BackgroundImage composable function.
     * It displays an image as the background using a Box composable.
     */
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.homebg),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .zIndex(-10f),
            contentScale = ContentScale.Crop
        )
    }
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(0.dp),
    ) {

        /**
         * This is the Header composable function.
         * It displays a header with menu, date, and search icons.
         */
        Column (modifier = Modifier
            .padding(horizontal = 15.dp)
            .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            /*
             * Start building the Header composable function.
             */

            // Create a Row composable with the maximum width, space between items, and center vertical alignment.
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                /*
                 * Create a nested Row composable with top padding and center vertical alignment.
                 */
                Row (
                    modifier = Modifier.padding(top = 4.dp)
                    ,verticalAlignment = Alignment.CenterVertically
                ) {
                    /*
                     * Display an IconButton composable with a menu icon.
                     */
                    IconButton(onClick = {}){
                        Icon(
                            imageVector = Icons.Sharp.Menu,
                            contentDescription = null,
                            modifier = Modifier.size(40.dp),
                            tint = Color(red = 143, green = 255, blue = 0)
                        )
                    }
                    //Create a variable to store the current date.
                    var dateTime = LocalDate.now()
                    //Display a Text composable with the current date, padding, font weight, color, and font size.
                    Text(text = dateTime.toString(), modifier = Modifier.padding(start = 5.dp), fontWeight = FontWeight.Bold, color = Color.White, fontSize = 15.sp)
                }
                // Create a nested Row composable with center vertical alignment.
                Row (verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Sharp.Search,
                            contentDescription = null,
                            modifier = Modifier
                                .size(36.dp)
                                .padding(top = 3.dp),
                            tint = Color.White,

                            )
                    }
                    //Display an IconButton composable with a date icon.
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Sharp.DateRange,
                            contentDescription = null,
                            modifier = Modifier
                                .size(35.dp)
                                .padding(start = 5.dp),
                            tint = Color.White
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            // Define a data class MyObject with myInt and myString properties
            data class MyObject(val myInt: Int, val myString: String)

            val items = listOf(
                MyObject(20, "Sun"),
                MyObject(21, "Mon"),
                MyObject(22, "Tue"),
                MyObject(23, "Wed"),
                MyObject(24, "Thu"),
                MyObject(25, "Fri"),
                MyObject(26, "Sat"),
            )
            // Create a LazyRow composable with contentPadding and horizontalArrangement
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Iterate over the items list
                items(items) { item ->
                    Column(modifier = Modifier
                        .clip(RoundedCornerShape(30))) {
                        Column (
                            modifier = Modifier
                                .background(color = Color(red = 65, green = 30, blue = 60))
                                .height(55.dp)
                                .width(40.dp),
                        ) {
                            Box(modifier = Modifier
                                .background(color = Color.Transparent)
                                .height(20.dp)
                                .fillMaxWidth(),
                                contentAlignment = Alignment.Center){
                                Text(
                                    text = item.myString,
                                    modifier = Modifier
                                        .padding(bottom = 2.dp)
                                        .background(color = Color.Transparent),
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                            Column (modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                                .background(Color.Transparent)
                                .height(50.dp)) {
                                Box(modifier = Modifier
                                    .background(color = Color(red = 74, green = 49, blue = 105))
                                    .fillMaxWidth()
                                    .fillMaxHeight(), contentAlignment = Alignment.Center){
                                    Text(
                                        text = item.myInt.toString(),
                                        modifier = Modifier
                                            .padding(bottom = 2.dp)
                                            .background(color = Color.Transparent),
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Gray
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }











        // In this section, I am creating a Column composable with a weight of 1f to occupy available space.

        Column(modifier = Modifier
            .weight(1f, fill = true)) {



            var refreshing by remember { mutableStateOf(false) }
            LaunchedEffect(refreshing) {
                if (refreshing) {
                    delay(4000)
                    refreshing = false
                }
            }

            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = refreshing),
                onRefresh = { refreshing = true },
            ) {

                displayCourses(courses)

            }

        }



        // In this section, I am creating a Column composable that fills the width of its parent.
        Column (modifier = Modifier.fillMaxWidth()) {
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.End) {
                FormDialog()
            }


            // I am adding a Spacer to create some vertical space.
            Spacer(modifier = Modifier.height(20.dp))

            // Now, I am creating a Row composable that fills the width of its parent and has a height of 80.dp.
            // It also has a background color and horizontal padding.
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(color = Color(red = 19, green = 3, blue = 40))
                .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // The first Button represents the "Today" option.
                Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors( backgroundColor = Color.Transparent),
                    elevation = ButtonDefaults.elevation(0.dp)
                ) {
                    Column( verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Sharp.List,
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp),
                            tint = Color(red = 143, green = 255, blue = 0)
                        )
                        Text(text = "Today", color = Color(red = 143, green = 255, blue = 0))
                    }
                }
                // The second Button represents the "StudentProfiles" option.
                Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors( backgroundColor = Color.Transparent),
                    elevation = ButtonDefaults.elevation(0.dp)
                ) {
                    Column( verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Sharp.Person,
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp),
                            tint = Color.White
                        )
                        Text(text = "StudentProfiles", color = Color.White)
                    }
                }
                // The third Button represents the "Settings" option.
                Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors( backgroundColor = Color.Transparent),
                    elevation = ButtonDefaults.elevation(0.dp)
                ) {
                    Column( verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Sharp.Settings,
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp),
                            tint = Color.White
                        )
                        Text(text = "Settings", color = Color.White)
                    }
                }
        }
        }



    }
}
@Composable
fun displayCourses(courses: List<User>) {
    if (courses.isEmpty()){
        Spacer(modifier = Modifier.height(80.dp))
        LazyColumn{
            item(null,){
                val name = "Takudzwa"
                Text(text = "Good Afternoon, ${name}",
                    modifier = Modifier.padding(start = 30.dp, top = 70.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White)
                Spacer(modifier = Modifier.height(180.dp))
                Column (modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "There is nothing scheduled for today", fontSize = 23.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Try adding new activities", color = Color.Gray, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
    else{

        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            items(courses) { course ->
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth().padding(top = 15.dp)) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(140.dp)
                            .shadow(50.dp),
                        shape = RoundedCornerShape(1.dp)
                    ) {
                        Column (modifier = Modifier.padding(top = 18.dp, bottom = 5.dp, start = 20.dp, end = 20.dp),
                            verticalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "${course.name}", fontWeight = FontWeight.Bold, fontSize = 25.sp, color = Color(red = 19, green = 3, blue = 40))
                            Row {
                                Text(text = "0 Participants,", color = Color.Gray, fontSize = 15.sp)
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(text = "0 days a week,", color = Color.Gray, fontSize = 15.sp)
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(text = "avg. 0 attendees", color = Color.Gray, fontSize = 15.sp)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                                Row {
                                    Text(text = "Present Today:", color = Color(red = 19, green = 3, blue = 40), fontWeight = FontWeight.Bold)
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(text = "0", fontWeight = FontWeight.Bold, color = Color.Blue)
                                }
                                Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors( backgroundColor = Color(red = 19, green = 3, blue = 40)), shape = RoundedCornerShape(0.dp),
                                    modifier = Modifier
                                        .height(32.dp)
                                        .width(100.dp)) {
                                    Text(text = "View", color = Color.White)
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

suspend fun createActivity(body: NewActivityObject){
    coroutineScope {
        var obj = JsonObject().apply {
            addProperty("name", body.name)
            addProperty("booleanValue", body.booleanValue)
        }
        val result = apiService.createActivity(obj)
        var activities = apiService.getActivities()
        if(result.isSuccessful){
            Log.e("000000", "CreateUser success: ${result.body()}")
            Log.e("000000", "CreateUser success: ${activities.body()}")
        }else{
            Log.e("000000", "createUser field: ${result.message()}")
            Log.e("000000", "createUser field: ${activities.message()}")
        }
    }
}

suspend fun getActivities(): JsonArray?{
    var activities: Response<JsonArray>
    coroutineScope {
        activities = apiService.getActivities()
        if(activities.isSuccessful){
            Log.e("000000", "CreateUser success: ${activities.body()}")
        }else{
            Log.e("000000", "createUser field: ${activities.message()}")
        }
    }
    return activities.body();
}


//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview4() {
//    ClassAttendanceAndroidApplicationTheme {
//        Greeting4("Android", courses)
//    }
//}

data class NewActivityObject(var name: String, var booleanValue: Boolean)