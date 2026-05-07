package com.example.velora.velora.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.velora.ui.theme.BodyNormal
import com.example.velora.ui.theme.BodySmall
import com.example.velora.ui.theme.ElementsColors
import com.example.velora.ui.theme.H1TextStyle
import com.example.velora.velora.presentation.utils.VSpacer
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderScreen(){

    var selectedDate by remember {
        mutableStateOf(LocalDate.now())
    }
    var expanded by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Velora",
                        style = H1TextStyle().copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                },

                actions = {

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {

                        Box{
                            IconButton(
                                onClick = {expanded = true}
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = "Settings"
                                )
                            }

                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Follow System", style = BodyNormal()) },
                                    onClick = {
                                        expanded = false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Light Theme", style = BodyNormal()) },
                                    onClick = {
                                        expanded = false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Dark Theme", style = BodyNormal()) },
                                    onClick = {
                                        expanded = false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Logout", style = BodyNormal()) },
                                    onClick = {
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            )
        },

        floatingActionButton = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FloatingActionButton( onClick = {} ) {
                    Icon(Icons.Default.Share, contentDescription = "Share")
                }

                VSpacer()

                FloatingActionButton( onClick = {} ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Reminder")
                }

            }
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text("Hi ansh, You completing plan!", style = BodyNormal().copy(fontWeight = FontWeight.Bold))
            VSpacer(4.dp)
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
//                progress = {},
            )
            Text(
                "Let's create your first reminder",
                style = BodySmall()
            )
            VSpacer(16.dp)

            DateSelector(
                selectedDate = selectedDate,
                onDateSelected = {selectedDate = it}
            )

            VSpacer(16.dp)

//            AnimatedContent(
//                targetState = when {
//                    isLoading -> "loading"
//                    else -> "list"
//                },
//                transitionSpec = {}
//            ){state ->
//                when(state){
//
//                }
//            }


        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateSelector(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
){
    val today = LocalDate.now()
    val dates = (0..6).map{today.plusDays(it.toLong())}

    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(dates){date->
            val isSelected = date == selectedDate
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(if(isSelected) ElementsColors.DarkGray.color else Color.Transparent)
                    .clickable{onDateSelected(date)}
                    .padding(vertical = 8.dp, horizontal = 12.dp)
            ) {
                Text(date.dayOfWeek.name.take(3), style = BodySmall())
                Text(date.dayOfMonth.toString(), style = BodyNormal())
            }
        }
    }
}