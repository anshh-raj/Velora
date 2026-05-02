package com.example.velora.velora.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.velora.ui.theme.BodyNormal
import com.example.velora.ui.theme.H1TextStyle
import com.example.velora.velora.presentation.utils.VSpacer

@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderScreen(){

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
            modifier = Modifier.padding(padding)
        ) { }
    }
}