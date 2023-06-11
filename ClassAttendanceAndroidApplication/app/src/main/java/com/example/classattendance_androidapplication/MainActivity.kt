package com.example.classattendance_androidapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.classattendance_androidapplication.ui.theme.ClassAttendanceAndroidApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClassAttendanceAndroidApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val context = LocalContext.current
    Column {
        // Creating a Box composable with a specified size and background color.
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .fillMaxWidth()
            .background(
                brush = SolidColor(Color(red = 19, green = 3, blue = 40)),
                shape = RectangleShape
            )
        )
        // Adding a Spacer to create vertical space.
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        )
        // Creating a Box composable with a specified size and background color.
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier.height(56.dp)
                ,onClick = {
                    val intent = Intent(context, SignIn::class.java)
                    context.startActivity(intent)
                },  colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(red = 143, green = 255, blue = 0)
                )) {
                // Adding an Icon and Text inside the Button.
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Continue with your Organisation",)
            }
            // Creating a Row with vertical alignment and padding.
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 50.dp)
            ) {
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.width(90.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Or login with")
                Spacer(modifier = Modifier.width(16.dp))
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.width(90.dp)
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    shape = RoundedCornerShape(10.dp) ,
                    modifier = Modifier
                        .height(56.dp)
                        .width(170.dp)
                        .padding(3.dp)
                        .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(11))
                    ,onClick = { /*TODO*/ },  colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White
                    )) {
                    Icon(
                        imageVector = Icons.Default.AccountBox,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Google")
                }
                Button(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .height(56.dp)
                        .width(170.dp)
                        .padding(3.dp)
                        .border(1.dp, Color.Gray, RoundedCornerShape(11))
                    ,onClick = { /*TODO*/ },  colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White
                    )) {
                    Icon(
                        imageVector = Icons.Default.AccountBox,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Facebook")
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Row {
                Text(text = "Don't have an account?")
                Spacer(modifier = Modifier.width(10.dp))
                ClickableText(text = AnnotatedString("Register"),
                    onClick = {
                        val intent = Intent(context, SignUpOrganization::class.java)
                        context.startActivity(intent)
                    },
                    style = MaterialTheme.typography.body1.copy(
                        color = Color(red = 143, green = 255, blue = 0),
                        textDecoration = TextDecoration.Underline
                    )

                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Row {
                Text(
                    text = "By continuing, with the services above, you agree to RollCall+ Terms and Services and Privacy Policy.",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(horizontal = 30.dp),
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ClassAttendanceAndroidApplicationTheme {
        Greeting("Android")
    }
}