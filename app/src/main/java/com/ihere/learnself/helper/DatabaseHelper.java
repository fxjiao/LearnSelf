package com.ihere.learnself.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ihere.learnself.bean.MayWordBean;
import com.ihere.learnself.bean.NovelBean;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static android.media.CamcorderProfile.get;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TABLE_NAME = "learnSelf.db";
    /**
     * userDao ，每张表对于一个
     */
    private Dao<MayWordBean, Integer> mayWordDao;
    private Dao<NovelBean,Integer>novelDao;

    private DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            int a = TableUtils.createTable(connectionSource, MayWordBean.class);
            int b = TableUtils.createTable(connectionSource, NovelBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, MayWordBean.class, true);
            TableUtils.dropTable(connectionSource, NovelBean.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DatabaseHelper instance;

    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     */
    public static synchronized DatabaseHelper getHelper(Context context) {
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null)
                    instance = new DatabaseHelper(context);
            }
        }

        return instance;
    }

    /**
     * @return
     * @throws SQLException
     */
    public Dao<MayWordBean, Integer> getMayWordDao() throws SQLException {
        if (mayWordDao == null) {
            mayWordDao = getDao(MayWordBean.class);
        }
        return mayWordDao;
    }

    public boolean isMayWordDatasExist(Context context) {
        int result = 0;
        try {
            result = (getHelper(context).getMayWordDao().queryForAll()).size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    /**
     * 添加所有城市信息
     *
     * @param context
     * @param mayWordBean
     * @return
     */
    public int addMayWords(Context context, MayWordBean mayWordBean) {
        int result = -1;
        if (mayWordBean != null) {
            try {
                result = getHelper(context).getMayWordDao().create(mayWordBean);
            } catch (SQLException e) {
                e.printStackTrace();
                result = -1;
            }
        }
        return result;
    }

    public boolean isTheMayWordExist(Context context, MayWordBean mayWordBean) {
        int result = 0;
        try {
            result = (getHelper(context).getMayWordDao().queryForEq("word", mayWordBean.getWord())).size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    /**
     * 查询区间数据
     *
     * @param context
     * @param from
     * @param to
     * @return
     */
    public List<MayWordBean> getOrderMayWords(Context context, int from, int to) {
        List<MayWordBean> mayWordBeanList = new LinkedList<>();
        try {
            mayWordBeanList = (getHelper(context).getMayWordDao()).queryBuilder().where().between("id", from, to).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mayWordBeanList;
    }

    /**
     * 查询区间数据
     *
     * @param context
     * @return
     */
    public List<MayWordBean> getAllNoteMayWords(Context context) {
        List<MayWordBean> mayWordBeanList = new LinkedList<>();
        try {
            mayWordBeanList = (getHelper(context).getMayWordDao()).queryBuilder().where().eq("isNewWord", true).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mayWordBeanList;
    }

    /**
     * 按首字母查询符合条件的所有单词
     *
     * @param context
     * @return
     */
    public List<MayWordBean> getMayWordsByFirstLetter(Context context,String firstLetter) {
        List<MayWordBean> mayWordBeanList = new LinkedList<>();
        try {
            mayWordBeanList = (getHelper(context).getMayWordDao()).queryBuilder().where().like("word", firstLetter + "%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mayWordBeanList;
    }

    /**
     * 按等级查询符合条件的所有单词
     *
     * @param context
     * @return
     */
    public List<MayWordBean> getMayWordsByLevel(Context context,String level) {
        List<MayWordBean> mayWordBeanList = new LinkedList<>();
        try {
            mayWordBeanList = (getHelper(context).getMayWordDao()).queryBuilder().where().eq("level", level).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mayWordBeanList;
    }

    /**
     * 查询区间数据
     *
     * @param context
     * @return
     */
    public List<MayWordBean> getAllMayWords(Context context) {
        List<MayWordBean> mayWordBeanList = new LinkedList<>();
        try {
            mayWordBeanList = (getHelper(context).getMayWordDao()).queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mayWordBeanList;
    }

    public boolean updateMayWord(Context context, MayWordBean mayWordBean) {
        int result = 0;
        try {
            result = getHelper(context).getMayWordDao().update(mayWordBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }


    //--------------------------------------------  小说start -------------------------------------------

    /**
     * @return
     * @throws SQLException
     */
    public Dao<NovelBean, Integer> getNovelDao() throws SQLException {
        if (novelDao == null) {
            novelDao = getDao(NovelBean.class);
        }
        return novelDao;
    }

    /**
     * 添加最新章节
     *
     * @param context
     * @param novelBean
     * @return
     */
    public int addChapter(Context context, NovelBean novelBean) {
        int result = -1;
        if (novelBean != null) {
            try {
                result = getHelper(context).getNovelDao().createOrUpdate(novelBean).getNumLinesChanged();
            } catch (SQLException e) {
                e.printStackTrace();
                result = -1;
            }
        }
        return result;
    }

    /**
     * 更新章节内容
     * @param context
     * @param novelBean
     * @return
     */
    public boolean updateChapter(Context context, NovelBean novelBean) {
        int result = 0;
        try {
            result = getHelper(context).getNovelDao().update(novelBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    public List<NovelBean> getAllChapters(Context context){
        List<NovelBean> mayWordBeanList = new LinkedList<>();
        try {
            mayWordBeanList = (getHelper(context).getNovelDao()).queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mayWordBeanList;
    }

    public NovelBean getChapterByLink(Context context,String link){
        try {
           QueryBuilder queryBuilder = (getHelper(context).getNovelDao()).queryBuilder();
            queryBuilder.where().eq("link",link);
            List<NovelBean> list = (List<NovelBean>) queryBuilder.query();
            if(list != null && list.size() > 0){
               return  list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //--------------------------------------------  小说end -------------------------------------------

}

