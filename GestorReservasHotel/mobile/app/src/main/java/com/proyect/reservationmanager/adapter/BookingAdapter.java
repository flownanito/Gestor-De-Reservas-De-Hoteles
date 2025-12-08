package com.proyect.reservationmanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.proyect.reservationmanager.R;
import com.proyect.reservationmanager.model.Room;
import com.proyect.reservationmanager.model.RoomType;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

  private List<Room> roomList;
  private OnBookingClickListener listener;

  public interface OnBookingClickListener {
    void onBookClick(Room room);
  }

  public BookingAdapter(List<Room> roomList, OnBookingClickListener listener) {
    this.roomList = roomList;
    this.listener = listener;
  }

  @NonNull
  @Override
  public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking_room, parent, false);
    return new BookingViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
    Room room = roomList.get(position);
    RoomType type = room.getRoomType();

    if (type != null) {
      holder.tvTitle.setText(type.getName() != null ? type.getName() : "Habitación");

      if (type.getPricePerNight() != null) {
        holder.tvPrice.setText(type.getPricePerNight() + " €");
      } else {
        holder.tvPrice.setText("Consultar");
      }
    } else {
      holder.tvTitle.setText("Habitación " + room.getRoomNumber());
      holder.tvPrice.setText("-- €");
    }

    String desc = "Planta " + room.getFloor() + " • Puerta " + room.getRoomNumber();
    if (type != null && type.getDescription() != null) {
      desc += "\n" + type.getDescription();
    }

    holder.tvDesc.setText(desc);


    holder.btnBook.setOnClickListener(v -> listener.onBookClick(room));
  }

  @Override
  public int getItemCount() {
    return roomList != null ? roomList.size() : 0;
  }

  static class BookingViewHolder extends RecyclerView.ViewHolder {
    TextView tvTitle, tvPrice, tvDesc;
    ImageView imgRoom;
    Button btnBook;

    public BookingViewHolder(@NonNull View itemView) {
      super(itemView);
      tvTitle = itemView.findViewById(R.id.tvBookingTitle);
      tvPrice = itemView.findViewById(R.id.tvBookingPrice);
      tvDesc = itemView.findViewById(R.id.tvBookingDesc);
      imgRoom = itemView.findViewById(R.id.imgRoomBooking);
      btnBook = itemView.findViewById(R.id.btnBookNow);
    }
  }
}