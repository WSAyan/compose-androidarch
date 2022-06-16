package com.sslwireless.androidarch.ui.screens.registration

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sslwireless.androidarch.ui.components.CommonToolbar
import com.sslwireless.androidarch.ui.screens.destinations.UploadNidScreenDestination
import com.sslwireless.androidarch.ui.theme.NipponCANTheme
import com.sslwireless.androidarch.ui.theme.SlateGrey
import com.sslwireless.androidarch.ui.theme.Typography
import com.sslwireless.androidarch.ui.util.datePicker

@Destination
@Composable
fun RegistrationScreen(navigator: DestinationsNavigator? = null) {
    val context = LocalContext.current

    val userTypes = listOf("Dealer", "Painter", "Manager")
    val (selectedUserType, onUserSelected) = remember { mutableStateOf(userTypes[0]) }
    var userTypeError by remember { mutableStateOf(false) }

    val divisions =
        listOf("Dhaka", "Chittagong", "Rajshahi", "Sylhet", "Khulna", "Barisal", "Mymensingh")
    var divisionExpanded by remember { mutableStateOf(false) }
    var division by remember { mutableStateOf("") }
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

    var dateOfBirth by remember { mutableStateOf("") }
    var dateOfBirthError by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf(false) }

    var rocketNumber by remember { mutableStateOf("") }
    var rocketNumberError by remember { mutableStateOf(false) }

    var linkedDealerCode by remember { mutableStateOf("") }
    var linkedDealerCodeError by remember { mutableStateOf(false) }

    var linkedDealerName by remember { mutableStateOf("") }
    var linkedDealerNameError by remember { mutableStateOf(false) }

    NipponCANTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            Scaffold(
                topBar = {
                    CommonToolbar(title = "GET REGISTRATION") {
                        navigator?.navigateUp()
                    }
                },
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
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
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "User Type",
                                style = Typography.caption,
                                color = if (userTypeError) Color.Red else SlateGrey
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Row(modifier = Modifier.fillMaxWidth()) {
                                userTypes.forEach {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        RadioButton(
                                            selected = (it == selectedUserType),
                                            onClick = {
                                                onUserSelected(it)
                                            }
                                        )

                                        Text(
                                            text = it,
                                            style = Typography.caption,
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(12.dp))
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
                                            selectedUserType,
                                        ) { isNameEmpty,
                                            isRocketNumberEmpty,
                                            isLinkedDealerCodeEmpty,
                                            isLinkedDealerNameEmpty,
                                            isDivisionEmpty,
                                            isDistrictEmpty,
                                            isDateOfBirthEmpty,
                                            isUserTypeEmpty ->
                                            nameError = isNameEmpty
                                            rocketNumberError = isRocketNumberEmpty
                                            linkedDealerCodeError = isLinkedDealerCodeEmpty
                                            linkedDealerNameError = isLinkedDealerNameEmpty
                                            divisionError = isDivisionEmpty
                                            districtError = isDistrictEmpty
                                            dateOfBirthError = isDateOfBirthEmpty
                                            userTypeError = isUserTypeEmpty
                                        }
                                    ) {
                                        //navController?.navigate(UPLOAD_NID_SCREEN)
                                        navigator?.navigate(UploadNidScreenDestination())
                                    }
                                },
                            ) {
                                Text(
                                    "CONTINUE",
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
    selectedUserType: String,
    onValidation: (
        Boolean,
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
        selectedUserType.isEmpty()
    )

    return name.isNotEmpty() ||
            rocketNumber.isNotEmpty() ||
            linkedDealerCode.isNotEmpty() ||
            linkedDealerName.isNotEmpty() ||
            division.isNotEmpty() ||
            district.isNotEmpty() ||
            dateOfBirth.isNotEmpty() ||
            selectedUserType.isNotEmpty()
}


@Preview
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen()
}