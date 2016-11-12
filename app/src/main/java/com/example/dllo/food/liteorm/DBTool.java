package com.example.dllo.food.liteorm;

import android.os.Handler;
import android.os.Looper;

import com.example.dllo.food.base.MyApp;
import com.litesuits.orm.LiteOrm;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Ren on 16/11/9.
 */
public class DBTool {
    private static  DBTool sDBTool = new DBTool();

    private LiteOrm liteOrm;
    private Handler handler;
    private Executor threadPool;

    public DBTool() {
        liteOrm = LiteOrm.newSingleInstance(MyApp.getContext(), "food.db");
        handler = new Handler(Looper.getMainLooper());
        int coreNum = Runtime.getRuntime().availableProcessors();
        threadPool = Executors.newFixedThreadPool(coreNum + 1);
    }

    public static DBTool getInstance() {
        return sDBTool;
    }

    public void insertSearchHistory(final ArrayList<SearchHistory> searchHistories) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                liteOrm.insert(searchHistories);
            }
        });
    }

    public void insertSearchHistory(final SearchHistory searchHistory) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                liteOrm.insert(searchHistory);
            }
        });
    }

    public void queryAllHistory(OnQueryListener onQueryListener) {
        threadPool.execute(new QueryRunnable(onQueryListener));
    }

    public void deleteAllHistory() {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                liteOrm.deleteAll(SearchHistory.class);
            }
        });
    }

    class QueryRunnable implements Runnable{

        private OnQueryListener onQueryListener;

        public QueryRunnable(OnQueryListener onQueryListener) {
            this.onQueryListener = onQueryListener;
        }

        @Override
        public void run() {
            ArrayList<SearchHistory> query = liteOrm.query(SearchHistory.class);
            handler.post(new CallBackRunnable(onQueryListener, query));
        }
    }

    class CallBackRunnable implements Runnable {

        private OnQueryListener onQueryListener;
        private ArrayList<SearchHistory> searchHistories;

        public CallBackRunnable(OnQueryListener onQueryListener, ArrayList<SearchHistory> searchHistories) {
            this.onQueryListener = onQueryListener;
            this.searchHistories = searchHistories;
        }

        @Override
        public void run() {
            onQueryListener.onQuery(searchHistories);
        }
    }


    public interface OnQueryListener {
        void onQuery(ArrayList<SearchHistory> histories);
    }



    public void insertCollection(final Collection collection) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                liteOrm.insert(collection);
            }
        });
    }

    public void insertCollection(final ArrayList<Collection> collections) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                liteOrm.insert(collections);
            }
        });
    }

    public void queryAllCollection() {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                liteOrm.query(Collection.class);
            }
        });
    }
}
