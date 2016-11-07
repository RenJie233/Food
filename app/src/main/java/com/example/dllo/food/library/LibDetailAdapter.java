package com.example.dllo.food.library;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.food.R;
import com.example.dllo.food.entity.LibDetailBean;
import com.example.dllo.food.volleyandgson.VolleySingleTon;

/**
 * Created by Ren on 16/11/2.
 */
public class LibDetailAdapter extends BaseAdapter {

    private LibDetailBean bean;
    private String code;


    public void setBean(LibDetailBean bean) {
        this.bean = bean;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int getCount() {
        return bean.getFoods() == null ? 0 : bean.getFoods().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getFoods().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LibDetailViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lib_detail, parent, false);
            viewHolder = new LibDetailViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (LibDetailViewHolder) convertView.getTag();
        }
        VolleySingleTon.getInstance().getImage(bean.getFoods().get(position).getThumb_image_url(), viewHolder.thumbIv);
        viewHolder.libDetailName.setText(bean.getFoods().get(position).getName());
        switch (code) {
            case "calory":
                viewHolder.count.setText((int) (4.184 * Integer.parseInt(bean.getFoods().get(position).getCalory())) + "");
                break;
            case "protein":
                viewHolder.count.setText(bean.getFoods().get(position).getProtein());
                viewHolder.unit.setText("克/100克");
                break;
            case "fat":
                viewHolder.count.setText(bean.getFoods().get(position).getFat());
                viewHolder.unit.setText("克/100克");
                break;
            case "fiber_dietary":
                viewHolder.count.setText(bean.getFoods().get(position).getFiber_dietary());
                viewHolder.unit.setText("克/100克");
                break;
            case "carbohydrate":
                viewHolder.count.setText(bean.getFoods().get(position).getCarbohydrate());
                viewHolder.unit.setText("克/100克");
                break;
            case "vitamin_a":
                viewHolder.count.setText(bean.getFoods().get(position).getVitamin_a());
                viewHolder.unit.setText("IU/100克");
                break;
            case "thiamine":
                viewHolder.count.setText(bean.getFoods().get(position).getThiamine());
                viewHolder.unit.setText("豪克/100克");
                break;
            case "lactoflavin":
                viewHolder.count.setText(bean.getFoods().get(position).getLactoflavin());
                viewHolder.unit.setText("豪克/100克");
                break;
            case "vitamin_c":
                viewHolder.count.setText(bean.getFoods().get(position).getVitamin_c());
                viewHolder.unit.setText("豪克/100克");
                break;
            case "vitamin_e":
                viewHolder.count.setText(bean.getFoods().get(position).getVitamin_e());
                viewHolder.unit.setText("豪克/100克");
                break;
            case "niacin":
                viewHolder.count.setText(bean.getFoods().get(position).getNiacin());
                viewHolder.unit.setText("豪克/100克");
                break;
            case "natrium":
                viewHolder.count.setText(bean.getFoods().get(position).getNatrium());
                viewHolder.unit.setText("豪克/100克");
                break;
            case "calcium":
                viewHolder.count.setText(bean.getFoods().get(position).getCalcium());
                viewHolder.unit.setText("豪克/100克");
                break;
            case "iron":
                viewHolder.count.setText(bean.getFoods().get(position).getIron());
                viewHolder.unit.setText("豪克/100克");
                break;
            case "kalium":
                viewHolder.count.setText(bean.getFoods().get(position).getKalium());
                viewHolder.unit.setText("豪克/100克");
                break;
            case "iodine":
                viewHolder.count.setText(bean.getFoods().get(position).getIodine());
                viewHolder.unit.setText("豪克/100克");
                break;
            case "zinc":
                viewHolder.count.setText(bean.getFoods().get(position).getZinc());
                viewHolder.unit.setText("豪克/100克");
                break;
            case "selenium":
                viewHolder.count.setText(bean.getFoods().get(position).getSelenium());
                viewHolder.unit.setText("豪克/100克");
                break;
            case "magnesium":
                viewHolder.count.setText(bean.getFoods().get(position).getMagnesium());
                viewHolder.unit.setText("豪克/100克");
                break;
            case "copper":
                viewHolder.count.setText(bean.getFoods().get(position).getCopper());
                viewHolder.unit.setText("豪克/100克");
                break;
            case "manganese":
                viewHolder.count.setText(bean.getFoods().get(position).getManganese());
                viewHolder.unit.setText("豪克/100克");
                break;
            case "cholesterol":
                viewHolder.count.setText(bean.getFoods().get(position).getCholesterol());
                viewHolder.unit.setText("豪克/100克");
                break;
            default:
                viewHolder.count.setText((int) (4.184 * Integer.parseInt(bean.getFoods().get(position).getCalory())) + "");
                break;
        }

        if (bean.getFoods().get(position).getHealth_light() == 1) {
            viewHolder.healthLight.setImageResource(R.mipmap.point_recommend);
        } else if (bean.getFoods().get(position).getHealth_light() == 2) {
            viewHolder.healthLight.setImageResource(R.mipmap.point_suit);
        } else {
            viewHolder.healthLight.setImageResource(R.mipmap.point_less);
        }
        return convertView;
    }

    public void addBean(LibDetailBean response) {
        this.bean.addData(response.getFoods());
        notifyDataSetChanged();
    }

    private class LibDetailViewHolder {

        private ImageView thumbIv, healthLight;
        private TextView libDetailName, count, unit;

        public LibDetailViewHolder(View convertView) {
            thumbIv = (ImageView) convertView.findViewById(R.id.thumbIv);
            healthLight = (ImageView) convertView.findViewById(R.id.healthLight);
            libDetailName = (TextView) convertView.findViewById(R.id.libDetailName);
            count = (TextView) convertView.findViewById(R.id.count);
            unit = (TextView) convertView.findViewById(R.id.unit);
        }
    }
}
