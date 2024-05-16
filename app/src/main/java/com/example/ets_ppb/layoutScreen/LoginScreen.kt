package com.example.ets_ppb.layoutScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ets_ppb.R
import com.example.ets_ppb.ScreenB

@Composable
fun LoginScreen(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(32.dp)
    ) {
        // Logo PT. Kereta Api Indonesia
        Image(
            painter = painterResource(id = R.drawable.logo_pt_kai),
            contentDescription = "Logo PT KAI",
            modifier = Modifier
                .size(120.dp),
            contentScale = ContentScale.FillWidth
        )

        // Gambar Traveller
        Image(
            painter = painterResource(id = R.drawable.traveller),
            contentDescription = "Gambar Traveller",
            modifier = Modifier
                .padding(top = 8.dp)
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Login Text
        Text(
            text = "Login KAI Access",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF155E9F),
        )

        // Subtitle
        Text(
            text = "Life is a journey, enjoy every mile with us.",
            fontSize = 13.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Username",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF155E9F),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 16.dp)
        )
        // username input
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = {
                Text(
                    text = "Masukkan Username",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black, // Mengatur warna teks menjadi putih
                unfocusedTextColor = Color.Black,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Password",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF155E9F),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 16.dp)
        )
        // password input
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(
                    text = "Masukkan Password",
                    style = TextStyle(
                        fontSize = 14.sp, // Ukuran font label
                        color = Color.Gray // Warna label
                    )
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black, // Mengatur warna teks menjadi putih
                unfocusedTextColor = Color.Black,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Login Button
        Button(
            onClick = {
                navController.navigate(
                    ScreenB(
                        name = username
                    )
                )
            },
            colors = ButtonDefaults.buttonColors(Color(0xFFED6B23)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(50.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Login", color = Color.White, fontSize = 18.sp)
        }
    }
}