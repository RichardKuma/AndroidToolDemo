package com.szyh.green.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.greendao.gen.User;
import com.greendao.gen.UserDao;
import com.szyh.green.demo.app.DaoApplication;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    UserDao mUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        add();
        query();
    }

    private void init(){
        mUserDao = DaoApplication.getInstances().getDaoSession().getUserDao();
    }

    private void add(){

        User user = new User((long)1,"user1");
        mUserDao.insert(user);
    }

    private void delete(){

    }

    private void modify(){

    }

    private void query(){
        List<User> users = mUserDao.loadAll();
        String userName = "";
        for (int i = 0; i < users.size(); i++) {
            userName += users.get(i).getName() + ",";
        }

        Log.d("DAO", "查询全部数据==>" + userName);
    }
}
