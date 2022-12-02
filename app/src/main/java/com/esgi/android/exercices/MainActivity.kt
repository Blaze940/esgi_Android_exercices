package com.esgi.android.exercices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esgi.android.exercices.databinding.VueListBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding : VueListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = VueListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO Changer cette ligne en fonction des layouts
        //setContentView(R.layout.vue_list)

        val bookings = listOf(
            generateFakeBookings(),
            generateFakeBookings(),
            generateFakeBookings(),
            generateFakeBookings(),
            generateFakeBookings(),
        )


    }

}

class ListAdapter(
    private val bookings: List<Booking>,
    private val listener: OnBookingListener,
) : RecyclerView.Adapter<BookingViewHolder>() {

    override fun getItemCount(): Int = bookings.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        return BookingViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.vue_list, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        holder.bind(bookings[position], listener)
    }
}

fun generateFakeBookings(): List<Booking> {
    return listOf(
        Booking(
            "Sudirman District Space",
            "This is the adress of the parking place",
            BookingStatus.ON_PROCESS,
            //date in kotlin with hours

            Date(2021, 10, 22, 11, 0),
            Date(2021, 10, 22, 13,30)
        ),
        Booking(
            "Sudirman District Space",
            "This is the adress of the parking place",
            BookingStatus.UPCOMING,
            //date in kotlin with hours

            Date(2021, 10, 22, 11, 0),
            Date(2021, 10, 22, 13,30)
        ),
        Booking(
            "Sudirman District Space",
            "This is the adress of the parking place",
            BookingStatus.COMPLETED,
            //date in kotlin with hours

            Date(2021, 10, 22, 11, 0),
            Date(2021, 10, 22, 13,30)
        ),
        Booking(
            "Sudirman District Space",
            "This is the adress of the parking place",
            BookingStatus.COMPLETED,
            //date in kotlin with hours

            Date(2021, 10, 22, 11, 0),
            Date(2021, 10, 22, 13,30)
        )
    )
}

interface OnBookingListener {
    fun onClicked(booking: Booking, position: Int)
}

class BookingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
    private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
    private val statusTextView: TextView = itemView.findViewById(R.id.statusTextView)
    private val arrivalDateTextView: TextView = itemView.findViewById(R.id.arrivalDateTextView)
    private val exitDateTextView: TextView = itemView.findViewById(R.id.exitDateTextView)

    fun bind(booking: Booking, listener: OnBookingListener) {
        titleTextView.text = booking.name
        descriptionTextView.text = booking.streetAddress
        statusTextView.text = booking.status.toString()
        arrivalDateTextView.text = booking.arrivalDate.toString()
        exitDateTextView.text = booking.exitDate.toString()

        itemView.setOnClickListener {
            listener.onClicked(booking, adapterPosition)
        }
    }
}