package com.jp.firstcompose

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by Jignesh Chauhan on 24-08-2023
 */

/*  Column and LazyColumn  */
@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "ContactData")
@Composable
fun ShowData() {

    /*Column(modifier = Modifier.padding(20.dp)) {
        Spacer(modifier = Modifier.size(20.dp))
        ListItems(R.drawable.ic_person, "Jignesh", "Developer", "7016160357")
        ListItems(R.drawable.ic_person, "Vivek", "Developer", "9876543210")
        ListItems(R.drawable.ic_person, "Ashwin", "QA Tester", "9876543210")
        ListItems(R.drawable.ic_person, "Sheetal", "Developer", "9876543210")
        ListItems(R.drawable.ic_person, "Arpit", "Designer", "9876543210")
        ListItems(R.drawable.ic_person, "Drashti", "Designer", "9876543210")
        ListItems(R.drawable.ic_person, "Ayushi", "Content Writter", "9876543210")
    }*/

    Column {
        var sContact by remember { mutableStateOf("") }
        OutlinedTextField(
            value = sContact,
            onValueChange = { sContact = it },
            label = { Text("Search Contact") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )

        /*Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            getData().map {
                ContactItem(imgID = it.imgID, name = it.name, contactNo = it.contactNo)
            }
        }*/

        LazyColumn(content = {
            items(getData()) {
                ContactItem(imgID = it.imgID, name = it.name, contactNo = it.contactNo)
            }
        })
    }

}

@Composable
fun ContactItem(imgID: Int, name: String, contactNo: String) {
    val context = LocalContext.current

    Card(colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$contactNo"))
                context.startActivity(intent)
            }

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 10.dp)
        ) {
            Image(
                painter = painterResource(imgID),
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
                    .padding(10.dp)
                    .weight(.2f)
            )
            Column(modifier = Modifier.weight(.8f)) {
                Text(
                    text = name, style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Contact : $contactNo", style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

data class ContactData(val imgID: Int, val name: String, val contactNo: String)

fun getData(): MutableList<ContactData> {
    val list = mutableListOf<ContactData>()
    list.add(ContactData(R.drawable.ic_person, "Jignesh", "7016160357"))
    list.add(ContactData(R.drawable.ic_person, "Prince", "7016519486"))
    list.add(ContactData(R.drawable.ic_person, "Sandip", "9510048151"))
    list.add(ContactData(R.drawable.ic_person, "Pradip", "9712464250"))
    list.add(ContactData(R.drawable.ic_person, "Jignesh", "7016160357"))
    list.add(ContactData(R.drawable.ic_person, "Prince", "7016519486"))
    list.add(ContactData(R.drawable.ic_person, "Sandip", "9510048151"))
    list.add(ContactData(R.drawable.ic_person, "Pradip", "9712464250"))
    list.add(ContactData(R.drawable.ic_person, "Jignesh", "7016160357"))
    list.add(ContactData(R.drawable.ic_person, "Prince", "7016519486"))
    list.add(ContactData(R.drawable.ic_person, "Sandip", "9510048151"))
    list.add(ContactData(R.drawable.ic_person, "Pradip", "9712464250"))
    list.add(ContactData(R.drawable.ic_person, "Jignesh", "7016160357"))
    list.add(ContactData(R.drawable.ic_person, "Prince", "7016519486"))
    list.add(ContactData(R.drawable.ic_person, "Sandip", "9510048151"))
    list.add(ContactData(R.drawable.ic_person, "Pradip", "9712464250"))

    return list
}


/* Composable State Hoisting */
@Preview(showSystemUi = true)
@Composable
fun StateHoisting() {
    val count: MutableState<Int> = rememberSaveable { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NotificationCounter(count.value) { count.value++ }
        MessageBar(count.value)
    }
}

@Composable
fun MessageBar(count: Int) {
    Card(
        modifier = Modifier.padding(10.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_notifications),
                contentDescription = "",
            )
            Text(text = "message sent so far - $count")
        }
    }

}

@Composable
fun NotificationCounter(count: Int, counter: () -> Int) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "You have sent $count notifications")
        Button(
            onClick = { counter() },
            shape = ButtonDefaults.elevatedShape
        ) {
            Text(text = "send notification")
        }
    }

}
