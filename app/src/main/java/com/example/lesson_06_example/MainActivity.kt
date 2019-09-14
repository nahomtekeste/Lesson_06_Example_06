package com.example.lesson_06_example

import android.app.DatePickerDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var listOfSelections = arrayListOf<String>()

    override fun onCreate(  savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Dialog
        btnDialog.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Are you sure?")
            builder.setPositiveButton("Yes",
                DialogInterface.OnClickListener {
                        dialog,
                        id ->
                })
            builder.setNegativeButton("No",
                DialogInterface.OnClickListener{
                        dialog,
                        id ->
                })
            builder.create()
            builder.show()
        }


        // button on ListOfSelection

        btnList.setOnClickListener{
            listOfSelections.clear()
            val builder = AlertDialog.Builder(this)
            val colours = arrayOf("Blue","Red","Green",
                "Yellow","Purple","Cyan")
            builder.setTitle("Select your colours")
            builder.setMultiChoiceItems(
                colours, null,
                DialogInterface.OnMultiChoiceClickListener {
                    dialog, which, isChecked ->
                    if(isChecked){
                        listOfSelections.add(colours[which])
                    } else if (
                        listOfSelections.contains(colours[which])){
                        listOfSelections.remove(colours[which])
                    }
                }
            )
            builder.setPositiveButton("Select",
                DialogInterface.OnClickListener{
                    dialog, id ->
                    var s:String = ""
                    for(i in 0 until listOfSelections.size){
                        s += " " + listOfSelections[i]
                    }
                    txtColours.text = s
                })
            builder.setNegativeButton("Cancle",
                DialogInterface.OnClickListener{
                    dialog, id ->
                        listOfSelections.clear()
                }
            )
            builder.create()
            builder.show()
        }

        //DateDialog
        var y = 2019
        var m = 8
        var d = 22
        btnDate.setOnClickListener{
            var builder = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener{
                    view, year, month, dayOfMonth ->
                    txtDate.setText("" + dayOfMonth + "/" +
                            (month+1) + "/" + year)
                },y,m,d
            )
            builder.create()
            builder.show()
        }


        //Notification
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager
        val id = "Channel_01"
        val name = "Default_Channel"
        val importance  = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(id, name, importance)
        channel.lightColor = Color.CYAN
        notificationManager.createNotificationChannel(channel)


        btnNotify.setOnClickListener{
            var nCBuilder = NotificationCompat.Builder(this,
                "Channel_01")
            nCBuilder.setSmallIcon(R.drawable.notification_icon_background)
            nCBuilder.setContentTitle("Notification")
            nCBuilder.setContentText("This is a notification")

            val mNoficiationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager

            mNoficiationManager.notify(1, nCBuilder.build())
        }

        //"Simple" notifications
        //Snackbar
        //Can include active elements
        btnSnackbar.setOnClickListener{
            var builder = Snackbar.make(it,
                "Snackbar", Snackbar.LENGTH_LONG)
            builder.show()
        }


        //Toast
        //Simple message
        btnToast.setOnClickListener{
            Toast.makeText(this,
                "This is a Toast", Toast.LENGTH_LONG)
                .show()
        }

    }
}
