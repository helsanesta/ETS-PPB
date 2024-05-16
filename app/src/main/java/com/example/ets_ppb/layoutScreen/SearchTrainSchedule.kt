package com.example.ets_ppb.layoutScreen

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ets_ppb.R
import com.example.ets_ppb.ScreenB
import com.example.ets_ppb.ScreenC
import com.example.ets_ppb.data.StationsData
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun SearchTrainSchedule(navController: NavHostController, args: ScreenB) {
    var selectedDepartureStation by remember { mutableStateOf(StationsData.stations[0]) }
    var selectedArrivalStation by remember { mutableStateOf(StationsData.stations[1]) }
    var selectedDate by remember { mutableStateOf("") }
    var adultCount by remember { mutableStateOf("") }
    var childCount by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Scaffold (
            topBar = {
                TopAppBar(navController)
            },
            bottomBar = {
                BottomAppBar()
            },

            ) {
                values ->
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(values)
                    .padding(horizontal = 16.dp),
            ) {
                Text(
                    text = "Selamat datang ${args.name}! Kemana kamu akan pergi hari ini?",
                    modifier = Modifier
                        .padding(top = 16.dp)
                )
                DynamicSelectTextField(
                    selectedValue = selectedDepartureStation,
                    options = StationsData.stations,
                    label = "Pilih Stasiun Keberangkatan",
                    onValueChangedEvent = { newValue ->
                        selectedDepartureStation = newValue
                    }
                )

                DynamicSelectTextField(
                    selectedValue = selectedArrivalStation,
                    options = StationsData.stations,
                    label = "Pilih Stasiun Tujuan",
                    onValueChangedEvent = { newValue ->
                        selectedArrivalStation = newValue
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                DatePickerField(selectedDate) { date ->
                    selectedDate = date
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = adultCount,
                    onValueChange = { adultCount = it },
                    label = { Text("Jumlah Penumpang Dewasa") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFED6B23), // Atur warna saat fokus
                        unfocusedBorderColor = Color.Gray, // Atur warna saat tidak fokus
                    ),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_twotone_group),
                            contentDescription = null
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = childCount,
                    onValueChange = { childCount = it },
                    label = { Text("Jumlah Penumpang Anak-Anak") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFED6B23), // Atur warna saat fokus
                        unfocusedBorderColor = Color.Gray, // Atur warna saat tidak fokus
                    ),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_twotone_group),
                            contentDescription = null
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Submit Button
                Button(
                    onClick = {
                        navController.navigate(
                            ScreenC (
                                departureStation = selectedDepartureStation,
                                arrivalStation = selectedArrivalStation,
                                totalPassangers = adultCount.toInt() + childCount.toInt(),
                                dateDeparture = selectedDate
                            )
                        )
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFFED6B23)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Submit", color = Color.White, fontSize = 18.sp)
                }
            }
        }
    }
}

@Composable
private fun BottomAppBar() {
    BottomAppBar(
        modifier = Modifier
            .clip(
                RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )
            .fillMaxWidth(),
        containerColor = Color(0xFF252525),
        contentColor = Color.White,
        contentPadding = PaddingValues(vertical = 4.dp),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(Icons.Outlined.Home, contentDescription = "Home")
                    }
                    Text(
                        text = "Home",
                        fontSize = 14.sp,
                        color = Color.White,
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            Icons.Filled.ShoppingCart,
                            contentDescription = "Ticket",
                            tint = Color(0xFFED6B23)
                        )
                    }
                    Text(
                        text = "Ticket",
                        fontSize = 14.sp,
                        color = Color(0xFFED6B23),
                        fontWeight = FontWeight.Bold
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            Icons.Outlined.AccountCircle,
                            contentDescription = "Account",
                        )
                    }
                    Text(
                        text = "Account",
                        fontSize = 14.sp,
                        color = Color.White,
                    )
                }
            }
        },
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopAppBar(navController: NavHostController) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF155E9F),
            titleContentColor = Color.White,
        ),
        title = { Text(text = "Pilih Kereta") },
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
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicSelectTextField(
    selectedValue: String,
    options: List<String>,
    label: String,
    onValueChangedEvent: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedValue,
            onValueChange = {},
            label = { Text(text = label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFED6B23), // Atur warna saat fokus
                unfocusedBorderColor = Color.Gray, // Atur warna saat tidak fokus
            ),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option: String ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        expanded = false
                        onValueChangedEvent(option)
                    }
                )
            }
        }
    }
}

@Composable
fun DatePickerField(selectedDate: String, onDateSelected: (String) -> Unit) {
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = remember {
        DatePickerDialog(context, { _, selectedYear, selectedMonth, selectedDayOfMonth ->
            // Set the calendar to the selected date
            calendar.set(selectedYear, selectedMonth, selectedDayOfMonth)
            // Format the date
            val formattedDate = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id")).format(calendar.time)
            onDateSelected(formattedDate)
        }, year, month, day)
    }

    OutlinedTextField(
        value = selectedDate,
        onValueChange = {},
        label = { Text("Pilih Tanggal") },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = null,
                modifier = Modifier.clickable { datePickerDialog.show() }
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFFED6B23),
            unfocusedBorderColor = Color.Gray,
        ),
        readOnly = true,
        modifier = Modifier.fillMaxWidth()
    )
}



