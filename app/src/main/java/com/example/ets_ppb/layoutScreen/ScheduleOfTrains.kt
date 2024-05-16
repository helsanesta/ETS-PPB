package com.example.ets_ppb.layoutScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavHostController
import com.example.ets_ppb.ScreenC
import java.time.Duration
import java.time.LocalTime
import kotlin.random.Random

val famousTrainNames = listOf(
    "Argo Bromo Anggrek",
    "Argo Dwipangga",
    "Argo Parahyangan",
    "Taksaka Pagi",
    "Gajayana"
)

val trainClassType = listOf(
    "Ekonomi",
    "Eksekutif",
    "Bisnis",
    "Super Eksekutif"
)

@Composable
fun ScheduleOfTrains(navController: NavHostController, args: ScreenC){
    Scaffold(
        topBar = { TopBar(navController, args) },
        bottomBar = { BottomBar() }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            items(10) {
                val totalSeats = Random.nextInt(100, 201) // Total seats between 100 and 200
                val bookedSeats = Random.nextInt(0, totalSeats + 1)

                val trainName = famousTrainNames.random()
                val classType = trainClassType.random()
                val departureTime = LocalTime.of(Random.nextInt(0, 22), Random.nextInt(0, 60))
                val arrivalTime = departureTime.plusHours(Random.nextLong(2, 8)).plusMinutes(Random.nextLong(0, 60))

                val price = (Random.nextInt(0, 6) * 10000) + 150000

                val duration = Duration.between(departureTime, arrivalTime).abs()

                val hours = duration.toHours()
                val minutes = duration.toMinutes() % 60
                val durationString = "${hours}h ${minutes}m"

                TrainCard(
                    trainName = trainName,
                    classType = classType,
                    departureTime = departureTime.toString(),
                    arrivalTime = arrivalTime.toString(),
                    price = "Rp $price",
                    duration = durationString,
                    bookedSeats = bookedSeats,
                    totalSeats = totalSeats
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavHostController, args: ScreenC) {
    CenterAlignedTopAppBar(
        title = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${args.departureStation} -> ${args.arrivalStation}",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${args.totalPassangers} Penumpang",
                    color = Color.White,
                    fontSize = 14.sp
                )
                Text(
                    text = args.dateDeparture,
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Arrow Back",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF155E9F),
            titleContentColor = Color.White,
        )
    )
}

@Composable
fun BottomBar() {
    BottomAppBar (
        modifier = Modifier
            .clip(
                RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )
            .fillMaxWidth(),
        containerColor = Color(0xFF252525),
        contentColor = Color.White,
        contentPadding = PaddingValues(vertical = 4.dp),
        content = {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowUp,
                            contentDescription = "Sort",
                        )
                    }
                    Text(
                        text = "Urutkan",
                        fontSize = 16.sp,
                        color = Color.White,
                    )
                }

                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = "Sort",
                        )
                    }
                    Text(
                        text = "Filter",
                        fontSize = 16.sp,
                        color = Color.White,
                    )
                }
            }
        }
    )
}

@Composable
fun TrainCard(
    trainName: String,
    classType: String,
    departureTime: String,
    arrivalTime: String,
    price: String,
    duration: String,
    bookedSeats: Int,  // Jumlah kursi yang sudah dipesan
    totalSeats: Int    // Total kapasitas kursi
) {
    val availableSeats = totalSeats - bookedSeats
    val isCapacityAbove80Percent = availableSeats <= totalSeats * 0.2

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = trainName, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = classType, fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = departureTime, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = " -> ", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = arrivalTime, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = duration, fontSize = 14.sp, color = Color.Gray)
            }
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .padding(start = 16.dp)
            ) {
                Text(text = price, color = Color(0xFFFFA500), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(4.dp))
                if (isCapacityAbove80Percent) {
                    Text(text = "Sisa $availableSeats Kursi", color = Color.Red, fontSize = 14.sp)
                }
            }
        }
    }
}