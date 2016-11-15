package com.example.dllo.food.liteorm;

import android.os.Handler;
import android.os.Looper;

import com.example.dllo.food.base.MyApp;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.WhereBuilder;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Ren on 16/11/9.
 *
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

//    public void insertSearchHistory(final ArrayList<SearchHistory> searchHistories) {
//        threadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//                liteOrm.insert(searchHistories);
//            }
//        });
//    }

    public void insertSearchHistory(final SearchHistory searchHistory) {

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                liteOrm.delete(new WhereBuilder(SearchHistory.class).where("history=?", new Object[] {searchHistory.getHistory()}));
                liteOrm.insert(searchHistory);
                ArrayList<SearchHistory> list = liteOrm.query(SearchHistory.class);
                if (list.size() > 10) {
                    liteOrm.delete(SearchHistory.class, 1, 1, "id");
                }
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

//    public void insertCollection(final ArrayList<Collection> collections) {
//        threadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//                liteOrm.insert(collections);
//            }
//        });
//    }

    public void queryAllCollection(OnCollectionQueryListener listener) {
        threadPool.execute(new QueryCollectionRunnable(listener));
    }

    public void deleteCollectionByLink(final String getLink) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                liteOrm.delete(new WhereBuilder(Collection.class).where("link=?", new Object[]{getLink}));
            }
        });
    }

//    public void queryCollectionByLink(final String getLink) {
//        threadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//                liteOrm.query(new QueryBuilder<Collection>(Collection.class).where("link=?", new String[]{getLink}));
//
//            }
//        });
//    }


    class QueryCollectionRunnable implements Runnable{

        private OnCollectionQueryListener listener;

        public QueryCollectionRunnable(OnCollectionQueryListener listener) {
            this.listener = listener;
        }

        @Override
        public void run() {
            ArrayList<Collection> query = liteOrm.query(Collection.class);
            handler.post(new CollectionCallBackRunnable(listener, query));
        }
    }

    class CollectionCallBackRunnable implements Runnable {

        private OnCollectionQueryListener listener;
        private ArrayList<Collection> collections;

        public CollectionCallBackRunnable(OnCollectionQueryListener listener, ArrayList<Collection> collections) {
            this.listener = listener;
            this.collections = collections;
        }

        @Override
        public void run() {
            listener.onQuery(collections);
        }
    }


    public interface OnCollectionQueryListener {
        void onQuery(ArrayList<Collection> collections);
    }

}
