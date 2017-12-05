package com.savtor.falconcalcultorFavoutive;

import com.savtor.falconcalaultorDatabase.Favouite_Item;
import com.savtor.falconcalaultorDatabase.Favourite_DataBasic;
import com.savtor.falconcalcultor.*;

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

/**
 * Created by GhostLeo_DT on 2/12/2017.
 */
public class Fragment_Favourite extends Fragment {

    Favourite_DataBasic favourite_dataBasic;
    Favourite_Adapter fav_adapter;
    List<Favourite_Data> favouriteData;
    TextView count_result_tv;
    RecyclerView favourite_recyclerview;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_favourite, container, false);
        Find_View(v);   // [1]

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.fav_toolbar_menu, menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search_view).getActionView();
        Search(searchView);     // [3]
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (favourite_dataBasic != null){
            Log.e("DATA BASIC ACTION : ","數據庫關閉, 共" + favourite_dataBasic.getCount() + "條紀錄");  // [Log.e]
            favourite_dataBasic.close();

        }

    }


    // [1] 加入畫面內容
    private void Find_View(View v){

        FragmentManager mFragmentManager = getFragmentManager();

        if (favourite_dataBasic == null){
            favourite_dataBasic = new Favourite_DataBasic(getActivity(), "Fragment_Favourite");
            Log.e("DATA BASIC ACTION : ","數據庫開啟, 共" + favourite_dataBasic.getCount() + "條紀錄"); // [Log.e]

        }

        favourite_recyclerview = (RecyclerView) v.findViewById(R.id.favourite_recyclerView);


        favouriteData = getDataBase_Data();     // [2]
        fav_adapter = new Favourite_Adapter(favouriteData, getActivity(), mFragmentManager);


        DefaultItemAnimator mDefaultItemAnimator = new DefaultItemAnimator();
        mDefaultItemAnimator.setRemoveDuration(100);
        favourite_recyclerview.setItemAnimator(mDefaultItemAnimator);
        favourite_recyclerview.setAdapter(fav_adapter);
        favourite_recyclerview.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false));

        count_result_tv = (TextView) v.findViewById(R.id.count_result_tv);
//        update_count_text();
    }

    // [2] 從 Sqlite Databasic 取出所有紀錄
    public List<Favourite_Data> getDataBase_Data() {

        List<Favouite_Item> items = favourite_dataBasic.query_all();

        favouriteData = new ArrayList<Favourite_Data>();

        for(Favouite_Item i : items){

            favouriteData.add(new Favourite_Data(
                    String.valueOf(i.getid()),
                    String.valueOf(i.getCreate_date()),
                    String.valueOf(i.getName()),
                    String.valueOf(i.getLoan_Amount()),
                    String.valueOf(i.getTrems()),
                    String.valueOf(i.getLoan_Rate()),
                    String.valueOf(i.getApply_status()),
                    String.valueOf(i.getLoan_Type()),
                    String.valueOf(i.getid()), // 改 installment
                    String.valueOf(i.getAlert_date())));
        }
        return  favouriteData;
    }

    // [3] 搜尋用戶輸入內容
    private void Search(SearchView searchView){

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                final List<Favourite_Data> search_list = filter(getDataBase_Data() ,newText);    // [4]
                fav_adapter.setFilter(search_list);

//                update_count_text();
                return true;
            }
        });

    }

    // [4] 與現有內容對比
    private List<Favourite_Data> filter (List<Favourite_Data> models, String query){

        query.toLowerCase().toString();

        final List<Favourite_Data> filterList = new ArrayList<>();

        for (Favourite_Data fav_data : models){

            if(fav_data.getName().toLowerCase().contains(query)){
                filterList.add(fav_data);
            }
        }
        return filterList;
    }



    // [7]
//    public void update_count_text(){
//
//        String count = favourite_dataBasic.getCount() + "";
//        count_result_tv.setText("一共搜索共" + count + "筆紀錄");
//
//    }

    // [5] 刪除一條紀錄


    // [6] 修改一條紀錄




}
