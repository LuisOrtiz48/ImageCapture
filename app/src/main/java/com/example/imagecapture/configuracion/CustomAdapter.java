package com.example.imagecapture.configuracion;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.imagecapture.R;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<personas> implements View.OnClickListener
{

    private List<personas> DataSet;
    private Context context;

    private static class ViewHolder
    {
        TextView txtnombres;
        TextView txtdecription;
        ImageView imageperson;
    }

    public CustomAdapter(List<personas> data, @NonNull Context context) {
        super(context, R.layout.row_personas);
        this.DataSet = data;
        this.context = context;
    }

    @Override
    public void onClick(View view) {

    }


    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        personas dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        final View result;

        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_personas, parent, false);
            viewHolder.txtnombres = (TextView) convertView.findViewById(R.id.PersonName);
            viewHolder.txtdecription = (TextView) convertView.findViewById(R.id.PersonDescrip);
            viewHolder.imageperson = (ImageView) convertView.findViewById(R.id.ImagePerson);

            result=convertView;
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtnombres.setText(dataModel.getNombres());
        viewHolder.txtdecription.setText(dataModel.getDescripcion());
        viewHolder.imageperson.setImageBitmap(ConvertBase64toImage(dataModel.getFoto()));
        return convertView;
    }

    private Bitmap ConvertBase64toImage(String Base64String)
    {
        String base64Image = Base64String.split(",")[1];
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }

}
