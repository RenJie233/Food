package com.example.dllo.food.library.search;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.food.R;
import com.example.dllo.food.base.BaseViewHolder;
import com.example.dllo.food.entity.SearchResultBean;

/**
 * Created by Ren on 16/11/7.
 */
public class SearchResultAdapter extends BaseAdapter {

    private SearchResultBean bean;
    private String code;

    public void setBean(SearchResultBean bean) {
        this.bean = bean;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int getCount() {
        return bean.getItems() == null ? 0 : bean.getItems().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder viewHolder = BaseViewHolder.getViewHolder(convertView, parent, R.layout.item_lib_detail);

        viewHolder.setImage(R.id.thumbIv, bean.getItems().get(position).getThumb_image_url());
        viewHolder.setText(R.id.libDetailName, bean.getItems().get(position).getName());

        switch (code) {
            case "normal":
                viewHolder.setText(R.id.count, bean.getItems().get(position).getCalory());// TODO: 16/11/8  转化单位

                break;
            case "calory":
//                double count = 4.184 * Integer.parseInt(bean.getItems().get(position).getCalory());
//                int count = Integer.parseInt(bean.getItems().get(position).getCalory().toString());
                viewHolder.setText(R.id.count, bean.getItems().get(position).getCalory());// TODO: 16/11/8  转化单位
                break;
            case "protein":
                viewHolder.setText(R.id.count, bean.getItems().get(position).getProtein());
                viewHolder.setText(R.id.unit, "克/100克");
                break;
            case "fat":
                viewHolder.setText(R.id.count, bean.getItems().get(position).getFat());
                viewHolder.setText(R.id.unit, "克/100克");
                break;
            case "fiber_dietary":
                viewHolder.setText(R.id.count, bean.getItems().get(position).getFiber_dietary());
                viewHolder.setText(R.id.unit, "克/100克");
                break;
            case "carbohydrate":
                viewHolder.setText(R.id.count, bean.getItems().get(position).getCarbohydrate());
                viewHolder.setText(R.id.unit, "克/100克");
                break;
            case "vitamin_a":
                viewHolder.setText(R.id.count, bean.getItems().get(position).getVitamin_a());
                viewHolder.setText(R.id.unit, "IU/100克");
                break;
            case "thiamine":
                viewHolder.setText(R.id.count, bean.getItems().get(position).getThiamine());
                viewHolder.setText(R.id.unit, "豪克/100克");
                break;
            case "lactoflavin":
                viewHolder.setText(R.id.count, bean.getItems().get(position).getLactoflavin());
                viewHolder.setText(R.id.unit, "豪克/100克");
                break;
            case "vitamin_c":
                viewHolder.setText(R.id.count, bean.getItems().get(position).getVitamin_c());
                viewHolder.setText(R.id.unit, "豪克/100克");
                break;
            case "vitamin_e":
                viewHolder.setText(R.id.count, bean.getItems().get(position).getVitamin_e());
                viewHolder.setText(R.id.unit, "豪克/100克");
                break;
            case "niacin":
                viewHolder.setText(R.id.count, bean.getItems().get(position).getNiacin());
                viewHolder.setText(R.id.unit, "豪克/100克");
                break;
            case "natrium":
                viewHolder.setText(R.id.count, bean.getItems().get(position).getNatrium());
                viewHolder.setText(R.id.unit, "豪克/100克");
                break;
            case "calcium":
                viewHolder.setText(R.id.count, bean.getItems().get(position).getCalcium());
                viewHolder.setText(R.id.unit, "豪克/100克");
                break;
            case "iron":
                viewHolder.setText(R.id.count, bean.getItems().get(position).getIron());
                viewHolder.setText(R.id.unit, "豪克/100克");
                break;
            case "kalium":
                viewHolder.setText(R.id.count, bean.getItems().get(position).getKalium());
                viewHolder.setText(R.id.unit, "豪克/100克");
                break;
            case "iodine":
                viewHolder.setText(R.id.count, bean.getItems().get(position).getIodine());
                viewHolder.setText(R.id.unit, "豪克/100克");
                break;
            case "zinc":
                viewHolder.setText(R.id.count, bean.getItems().get(position).getZinc());
                viewHolder.setText(R.id.unit, "豪克/100克");
                break;
            case "selenium":
                viewHolder.setText(R.id.count, bean.getItems().get(position).getSelenium());
                viewHolder.setText(R.id.unit, "豪克/100克");
                break;
            case "magnesium":
                viewHolder.setText(R.id.count, bean.getItems().get(position).getMagnesium());
                viewHolder.setText(R.id.unit, "豪克/100克");
                break;
            case "copper":
                viewHolder.setText(R.id.count, bean.getItems().get(position).getCopper());
                viewHolder.setText(R.id.unit, "豪克/100克");
                break;
            case "manganese":
                viewHolder.setText(R.id.count, bean.getItems().get(position).getManganese());
                viewHolder.setText(R.id.unit, "豪克/100克");
                break;
            case "cholesterol":
                viewHolder.setText(R.id.count, bean.getItems().get(position).getCholesterol());
                viewHolder.setText(R.id.unit, "豪克/100克");
                break;
            default:
                viewHolder.setText(R.id.count, String.valueOf(4.184 * Integer.parseInt(bean.getItems().get(position).getCalory())));
                break;
        }

        if (bean.getItems().get(position).getHealth_light() == 1) {
            viewHolder.setImage(R.id.healthLight, R.mipmap.point_recommend);
        } else if (bean.getItems().get(position).getHealth_light() == 2) {
            viewHolder.setImage(R.id.healthLight, R.mipmap.point_suit);
        } else {
            viewHolder.setImage(R.id.healthLight, R.mipmap.point_less);
        }
        return viewHolder.getItemView();
    }

    public void addBean(SearchResultBean response) {
        this.bean.addData(response.getItems());
        notifyDataSetChanged();
    }
}
