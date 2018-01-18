package com.savtor.Wish_List;

import com.savtor.Credit_Database.Favouite_Item;
import com.savtor.Credit_Database.Favourite_DataBasic;
import com.savtor.falconcalcultor.*;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import android.widget.*;

/**
 * Created by GhostLeo_DT on 2/12/2017.
 */
public class WishList_Main extends Fragment {

    Favourite_DataBasic favourite_dataBasic;
    WishList_Adapter fav_adapter;
    List<Favouite_Item> favouriteData;
    TextView count_result_tv;
    RecyclerView favourite_recyclerview;
	private LinearLayout CountText_Linear;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.favourite_main, container, false);
        Find_View(v);   // [1]
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.fav_toolbar_menu, menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search_view).getActionView();
        Search(searchView);

        //        改 menu icon 顏色
        for(int i = 0; i < menu.size(); i++){
            Drawable drawable = menu.getItem(i).getIcon();
            if(drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(getResources().getColor(R.color.deep_teal_200), PorterDuff.Mode.SRC_ATOP);
            }
        }
    }


/*================================================================================================
 *                                      Find View
================================================================================================ */
    private void Find_View(View v){

        FragmentManager mFragmentManager = getFragmentManager();

        favourite_recyclerview = (RecyclerView) v.findViewById(R.id.favourite_recyclerView);

        favouriteData = getDataBase_Data();
        fav_adapter = new WishList_Adapter(favouriteData, getActivity(), mFragmentManager);

        DefaultItemAnimator mDefaultItemAnimator = new DefaultItemAnimator();
        mDefaultItemAnimator.setRemoveDuration(100);
        favourite_recyclerview.setItemAnimator(mDefaultItemAnimator);
        favourite_recyclerview.setAdapter(fav_adapter);
        favourite_recyclerview.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false));

		CountText_Linear = (LinearLayout) v.findViewById(R.id.count_text_linear);
        count_result_tv = (TextView) v.findViewById(R.id.count_result_tv);
        update_count_text();
    }


/*================================================================================================
 *                                      Get Data Base Data
================================================================================================ */
    public List<Favouite_Item> getDataBase_Data() {

        favourite_dataBasic = new Favourite_DataBasic(getActivity(), "WishList_Main");

        Log.e("DATA BASIC ACTION : ","數據庫開啟, 共" + favourite_dataBasic.getCount() + "條紀錄");

        List<Favouite_Item> items = favourite_dataBasic.query_orderby_date();

        favouriteData = new ArrayList<Favouite_Item>();

        for(Favouite_Item i : items){

            favouriteData.add(new Favouite_Item(
				i.getID(),
				i.getCreate_Date().substring(0, 10),
				i.getProduct_Name(),
				i.getProduct_Type(),
				i.getProduct_Status(),
				i.getLoan_No(),
				i.getLoan_Amount(),
				i.getLoan_Trems(),
				i.getLoan_Rate(),
                i.getLoan_Installment(),
				i.getFirst_Due(),
				i.getFinal_Due(),
				i.getEOM_DueDate(),
				i.getSetup_Alarm(),
				i.getAlarm_Time(),
				i.getAddress(),
				i.getPhone_No(),
				i.getRemarks(),
				i.getRequestCode()));
        }

        favourite_dataBasic.close();
        return  favouriteData;
    }

/*================================================================================================
 *                                     搜尋用戶輸入內容
================================================================================================ */
    private void Search(SearchView searchView){

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                final List<Favouite_Item> search_list = filter(getDataBase_Data() ,newText);
                fav_adapter.setFilter(search_list);

                return true;
            }
        });

    }
/*================================================================================================
 *                                     與現有內容對比
================================================================================================ */
    private List<Favouite_Item> filter (List<Favouite_Item> models, String query){

        query.toLowerCase().toString();

        final List<Favouite_Item> filterList = new ArrayList<>();

        for (Favouite_Item fav_data : models){

            if(fav_data.getProduct_Name().toLowerCase().contains(query)){
                filterList.add(fav_data);
            }
        }
        return filterList;
    }

/*================================================================================================
 *                                     顯示有幾條紀錄 (未完成)
================================================================================================ */
    public void update_count_text() {

        if (fav_adapter.getItemCount() == 0){
			CountText_Linear.setVisibility(View.VISIBLE);
            count_result_tv.setText("沒有任何紀錄");
        }else{
            //String count_text = String.valueOf(fav_adapter.getItemCount());
            //count_result_tv.setText("一共搜索共" + count_text + "條紀錄");
			CountText_Linear.setVisibility(View.GONE);
        }

    }



}