package com.savtor.Wish_List;

import com.savtor.Credit_Profile.Product_Type;
import com.savtor.WishList_Database.WishList_Item;
import com.savtor.WishList_Database.WishList_DataBasic;
import com.savtor.falconcalcultor.*;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import android.widget.*;

/**
 * Created by GhostLeo_DT on 2/12/2017.
 */
public class WishList_Main extends Fragment {

    private WishList_RecycleView mRecycleView;
    private WishList_Adapter mAdapter;
    private WishList_DataBasic mDataBasic;
	private LinearLayout Empty_View, Create_Wish_Btn;
    private List<WishList_Item> WishList_Data;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.wish_main, container, false);
        Find_View(v);
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

        /**     will change     **/
        FragmentManager mFragmentManager = getFragmentManager();

        Empty_View = (LinearLayout) v.findViewById(R.id.empty_data_linear);

        WishList_Data = getDataBase_Data();
        mAdapter = new WishList_Adapter(WishList_Data, getActivity(), mFragmentManager);

        mRecycleView = (WishList_RecycleView) v.findViewById(R.id.favourite_recyclerView);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false));
        DefaultItemAnimator mDefaultItemAnimator = new DefaultItemAnimator();
        mDefaultItemAnimator.setRemoveDuration(100);
        mRecycleView.setItemAnimator(mDefaultItemAnimator);
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setEmptyView(Empty_View);

        Create_Wish_Btn = (LinearLayout) v.findViewById(R.id.create_wish_btn);
        Create_Wish_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity_Main)getActivity()).Fragment_Transaction(new Product_Type());
            }
        });
    }


/*================================================================================================
 *                                      Get Data Base Data
================================================================================================ */
    public List<WishList_Item> getDataBase_Data() {

        mDataBasic = new WishList_DataBasic(getActivity(), "WishList_Main");

        Log.e("DATA BASIC ACTION : ","數據庫開啟, 共" + mDataBasic.getCount() + "條紀錄");

        List<WishList_Item> items = mDataBasic.query_orderby_date();

        WishList_Data = new ArrayList<WishList_Item>();

        for(WishList_Item i : items){

            WishList_Data.add(new WishList_Item(
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

        mDataBasic.close();
        return  WishList_Data;
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

                final List<WishList_Item> search_list = filter(getDataBase_Data() ,newText.toLowerCase());
                mAdapter.setFilter(search_list);

                return true;
            }
        });

    }
/*================================================================================================
 *                                     與現有內容對比
================================================================================================ */
    private List<WishList_Item> filter (List<WishList_Item> models, String query){

        query.toLowerCase().toString();

        final List<WishList_Item> filterList = new ArrayList<>();

        for (WishList_Item fav_data : models){

            if(fav_data.getProduct_Name().toLowerCase().contains(query)){
                filterList.add(fav_data);
            }
        }
        return filterList;
    }


}
