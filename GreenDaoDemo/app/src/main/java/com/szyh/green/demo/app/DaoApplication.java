package com.szyh.green.demo.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import com.greendao.gen.DaoMaster;
import com.greendao.gen.DaoSession;

public class DaoApplication extends Application {
    
    private DaoMaster.DevOpenHelper mHelper;
    
    private SQLiteDatabase mDB;
    
    private DaoMaster mDaoMaster;
    
    private DaoSession mDaoSession;
    
    public static DaoApplication instances;
    
    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        setDatabase();
    }

    public static DaoApplication getInstances(){
        return instances;
    }

    /**
     * 设置greenDAO
     */
    private void setDatabase() {

        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。

        mHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        mDB = mHelper.getWritableDatabase();

        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(mDB);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return mDB;
    }
}
