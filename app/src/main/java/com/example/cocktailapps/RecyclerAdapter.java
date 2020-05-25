package com.example.cocktailapps;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.cocktailsapp.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context context;
    private List<Drinks> drinksList;


    public RecyclerAdapter(Context context, List<Drinks> drinksList) {
        this.context = context;
        this.drinksList = drinksList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.cardview_drinks,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
      holder.title.setText(drinksList.get(position).getStrDrink());
        Glide.with(context)
                .load(drinksList.get(position).getStrDrinkThumb())
                .into(holder.imageCoctails);
     holder.cardView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(context, InfoDrink.class);
             intent.putExtra("Name",drinksList.get(position).getStrDrink());
             intent.putExtra("Alcohol",drinksList.get(position).getStrAlcoholic());
             intent.putExtra("Glass",drinksList.get(position).getStrGlass());
             intent.putExtra("Ingradient1",drinksList.get(position).getStrIngredient1());
             intent.putExtra("Ingradient2",drinksList.get(position).getStrIngredient2());
             intent.putExtra("Ingradient3",drinksList.get(position).getStrIngredient3());
             intent.putExtra("Ingradient4",drinksList.get(position).getStrIngredient4());
             intent.putExtra("Measure1",drinksList.get(position).getStrMeasure1());
             intent.putExtra("Measure2",drinksList.get(position).getStrMeasure2());
             intent.putExtra("Measure3",drinksList.get(position).getStrMeasure3());
             intent.putExtra("Measure4",drinksList.get(position).getStrMeasure4());
             intent.putExtra("Instruction",drinksList.get(position).getStrInstructions());
             intent.putExtra("URL",drinksList.get(position).getStrDrinkThumb());
             context.startActivity(intent);

         }
     });
    }

    @Override
    public int getItemCount() throws NullPointerException {
        try {
            return drinksList.size();
        }catch (Exception e){
            return 0;
        }
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageCoctails;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.book_title_id);
            imageCoctails = (ImageView) itemView.findViewById(R.id.image_view);
            cardView = (CardView) itemView.findViewById(R.id.card_view);

        }
    }

}
