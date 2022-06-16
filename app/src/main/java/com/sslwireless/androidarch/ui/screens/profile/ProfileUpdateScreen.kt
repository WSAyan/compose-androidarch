package com.sslwireless.androidarch.ui.screens.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sslwireless.androidarch.R
import com.sslwireless.androidarch.ui.components.CommonToolbar
import com.sslwireless.androidarch.ui.screens.destinations.UploadNidScreenDestination
import com.sslwireless.androidarch.ui.theme.NipponCANTheme
import com.sslwireless.androidarch.ui.theme.Typography
import com.sslwireless.androidarch.ui.util.datePicker

@Destination
@Composable
fun ProfileUpdateScreen(
    navigator: DestinationsNavigator? = null
) {
    val context = LocalContext.current

    val divisions =
        listOf("Dhaka", "Chittagong", "Rajshahi", "Sylhet", "Khulna", "Barisal", "Mymensingh")
    var divisionExpanded by remember { mutableStateOf(false) }
    var division by remember { mutableStateOf("Dhaka") }
    var divisionError by remember { mutableStateOf(false) }

    val districts = mapOf(
        "Dhaka" to listOf(
            "Dhaka",
            "Tangail",
            "Gazipur",
        ),
        "Chittagong" to listOf("Chittagong", "Rangamati", "Bandarban", "Cox's Bazar"),
        "Rajshahi" to listOf("Rajshahi", "Rangpur", "Natore"),
        "Sylhet" to listOf("Sylhet", "Sunamgonj", "Srimongol"),
        "Khulna" to listOf("Khulna", "Bagerhat", "Jashore"),
        "Barisal" to listOf("Barisal", "Noakhali", "Potuakhali"),
        "Mymensingh" to listOf("Mymensingh", "Netrokona"),
    )
    var districtExpanded by remember { mutableStateOf(false) }
    var district by remember { mutableStateOf(districts[division]?.first() ?: "") }
    var districtError by remember { mutableStateOf(false) }

    var dateOfBirth by remember { mutableStateOf("15/12/1925") }
    var dateOfBirthError by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf("John Doe") }
    var nameError by remember { mutableStateOf(false) }

    var rocketNumber by remember { mutableStateOf("01545454") }
    var rocketNumberError by remember { mutableStateOf(false) }

    var linkedDealerCode by remember { mutableStateOf("1234546") }
    var linkedDealerCodeError by remember { mutableStateOf(false) }

    var linkedDealerName by remember { mutableStateOf("Lorem Ipsum Mia") }
    var linkedDealerNameError by remember { mutableStateOf(false) }

    NipponCANTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            Scaffold(
                topBar = {
                    CommonToolbar(
                        title = "Profile Update",
                        onBackPressed = {
                            navigator?.navigateUp()
                        },
                    )
                },
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 20.dp)
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_person_24),
                            contentDescription = "",
                            modifier = Modifier
                                .size(100.dp)
                                .clip(shape = CircleShape)
                                .background(color = MaterialTheme.colors.secondary)
                        )
                        Spacer(modifier = Modifier.height(35.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp)
                        ) {
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = name,
                                label = { Text(text = "Name") },
                                maxLines = 1,
                                isError = nameError,
                                onValueChange = {
                                    name = it.trim()
                                },
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = rocketNumber,
                                label = { Text(text = "Rocket No.") },
                                maxLines = 1,
                                isError = rocketNumberError,
                                onValueChange = {
                                    rocketNumber = it.trim()
                                },
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = linkedDealerCode,
                                label = { Text(text = "Linked Dealer Code") },
                                maxLines = 1,
                                isError = linkedDealerCodeError,
                                onValueChange = {
                                    linkedDealerCode = it.trim()
                                },
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = linkedDealerName,
                                label = { Text(text = "Linked Dealer Name") },
                                maxLines = 1,
                                isError = linkedDealerNameError,
                                onValueChange = {
                                    linkedDealerName = it.trim()
                                },
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            ExposedDropdownMenuBox(
                                expanded = divisionExpanded,
                                onExpandedChange = {
                                    divisionExpanded = !divisionExpanded
                                }
                            ) {
                                OutlinedTextField(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    readOnly = true,
                                    value = division,
                                    isError = divisionError,
                                    onValueChange = { },
                                    label = { Text("Division") },
                                    trailingIcon = {
                                        ExposedDropdownMenuDefaults.TrailingIcon(
                                            expanded = divisionExpanded
                                        )
                                    },
                                )
                                ExposedDropdownMenu(
                                    expanded = divisionExpanded,
                                    onDismissRequest = {
                                        divisionExpanded = false
                                    }
                                ) {
                                    divisions.forEach { selectionOption ->
                                        DropdownMenuItem(
                                            onClick = {
                                                division = selectionOption
                                                district =
                                                    districts[division]?.first() ?: ""
                                                divisionExpanded = false
                                            }
                                        ) {
                                            Text(text = selectionOption)
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            ExposedDropdownMenuBox(
                                expanded = districtExpanded,
                                onExpandedChange = {
                                    districtExpanded = !districtExpanded
                                }
                            ) {
                                OutlinedTextField(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    readOnly = true,
                                    value = district,
                                    onValueChange = { },
                                    isError = districtError,
                                    label = { Text("District") },
                                    trailingIcon = {
                                        ExposedDropdownMenuDefaults.TrailingIcon(
                                            expanded = districtExpanded
                                        )
                                    },
                                )
                                ExposedDropdownMenu(
                                    expanded = districtExpanded,
                                    onDismissRequest = {
                                        districtExpanded = false
                                    }
                                ) {
                                    districts[division]?.forEach { selectionOption ->
                                        DropdownMenuItem(
                                            onClick = {
                                                district = selectionOption
                                                districtExpanded = false
                                            }
                                        ) {
                                            Text(text = selectionOption)
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        context
                                            .datePicker {
                                                dateOfBirth = it
                                            }
                                            .show()
                                    },
                                value = dateOfBirth,
                                enabled = false,
                                isError = dateOfBirthError,
                                label = { Text(text = "Date of Birth") },
                                maxLines = 1,
                                onValueChange = {

                                },
                            )
                            Spacer(modifier = Modifier.height(50.dp))
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = MaterialTheme.colors.primary
                                ),
                                onClick = {
                                    if (validateForm(
                                            name,
                                            rocketNumber,
                                            linkedDealerCode,
                                            linkedDealerName,
                                            division,
                                            district,
                                            dateOfBirth,
                                        ) { isNameEmpty,
                                            isRocketNumberEmpty,
                                            isLinkedDealerCodeEmpty,
                                            isLinkedDealerNameEmpty,
                                            isDivisionEmpty,
                                            isDistrictEmpty,
                                            isDateOfBirthEmpty ->
                                            nameError = isNameEmpty
                                            rocketNumberError = isRocketNumberEmpty
                                            linkedDealerCodeError = isLinkedDealerCodeEmpty
                                            linkedDealerNameError = isLinkedDealerNameEmpty
                                            divisionError = isDivisionEmpty
                                            districtError = isDistrictEmpty
                                            dateOfBirthError = isDateOfBirthEmpty
                                        }
                                    ) {
                                        navigator?.navigate(UploadNidScreenDestination())
                                    }
                                },
                            ) {
                                Text(
                                    "UPDATE",
                                    style = Typography.button,
                                    color = MaterialTheme.colors.onSecondary
                                )
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                }
            )
        }

    }
}


private fun validateForm(
    name: String,
    rocketNumber: String,
    linkedDealerCode: String,
    linkedDealerName: String,
    division: String,
    district: String,
    dateOfBirth: String,
    onValidation: (
        Boolean,
        Boolean,
        Boolean,
        Boolean,
        Boolean,
        Boolean,
        Boolean
    ) -> Unit
): Boolean {
    onValidation(
        name.isEmpty(),
        rocketNumber.isEmpty(),
        linkedDealerCode.isEmpty(),
        linkedDealerName.isEmpty(),
        division.isEmpty(),
        district.isEmpty(),
        dateOfBirth.isEmpty(),
    )

    return name.isNotEmpty() ||
            rocketNumber.isNotEmpty() ||
            linkedDealerCode.isNotEmpty() ||
            linkedDealerName.isNotEmpty() ||
            division.isNotEmpty() ||
            district.isNotEmpty() ||
            dateOfBirth.isNotEmpty()
}


@Preview
@Composable
fun ProfileUpdateScreenPreview() {
    ProfileUpdateScreen()
}