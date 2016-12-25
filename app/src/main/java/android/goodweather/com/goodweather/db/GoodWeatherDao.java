package android.goodweather.com.goodweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.goodweather.com.goodweather.db.entity.City;
import android.goodweather.com.goodweather.db.entity.County;
import android.goodweather.com.goodweather.db.entity.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-12-15.
 */

public class GoodWeatherDao {
    /**
     * 数据库名
     */
    public static final String DB_NAME="cool_weather";
    /**
     * 数据库版本
     */
    public static final int VERSION=1;

    private static GoodWeatherDao coolWeatherDao;

    private SQLiteDatabase db;
    /**
     * 将构成方法私有化
     */
    private GoodWeatherDao(Context context){
        GoodWeatherOpenHelper dbHelper=new GoodWeatherOpenHelper(context,
                DB_NAME,null,VERSION);
        db=dbHelper.getWritableDatabase();
    }
    /**
     * 获取CoolWeatherDao的实例
     */
    public  static GoodWeatherDao getInstance(Context context){
        if (coolWeatherDao==null){
            synchronized (GoodWeatherDao.class){
                if (coolWeatherDao==null){
                    coolWeatherDao=new GoodWeatherDao(context);
                }
            }
        }
        return coolWeatherDao;
    }
    /**
     * 将Province实例存储到数据库
     */
    public void saveProvince(Province province){
        if (province!=null){
            ContentValues values=new ContentValues();
            values.put("province_name",province.getProviceName());
            values.put("province_code",province.getProviceeCode());
            db.insert("Province",null,values);
        }
    }
    /**
     * 从数据库读取全国所有的省份信息
     */
    public List<Province> loadProvinces(){
        List<Province> list=new ArrayList<Province>();
        Cursor cursor=db.query("Province",null,null,null,null,null,null );
        if (cursor.moveToFirst()){
            do {
                Province province=new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProviceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProviceeCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            }while (cursor.moveToNext());
        }
        return list;
    }
    /**
     * 将City实例存储到数据库
     */
    public void saveCity(City city){
        if (city!=null){
            ContentValues values=new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityCode());
            values.put("province_id",city.getProvinceId());
            db.insert("City",null,values);
        }
    }
    /**
     * 从数据库读取某省下所有的城市信息
     */
    public List<City> loadCities(int provinceId){
        List<City> list=new ArrayList<City>();
        Cursor cursor=db.query("City",null,"province_id=?",
                new String[]{String.valueOf(provinceId)},null,null,null);
        if (cursor.moveToFirst()){
            do {
                City city=new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            }while (cursor.moveToNext());
        }
        return list;
    }
    /**
     * 将county实例存储到数据库
     */
    public void saveCounty(County county){
        if (county!=null){
            ContentValues values=new ContentValues();
            values.put("county_name",county.getCountyName());
            values.put("county_code",county.getCountyCode());
            values.put("city_id",county.getCityId());
            values.put("weater_id",county.getWeatherId());
            db.insert("County",null,values);
        }
    }
    /**
     * 从数据库读取某城市下所有的县信息
     */
    public List<County> loadCounties(int cityId){
        List<County> list=new ArrayList<County>();
        Cursor cursor=db.query("County",null,"city_id=?",
                new String[]{String.valueOf(cityId)},null,null,null );
        if (cursor.moveToFirst()){
            do {
                County county=new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setWeatherId(cursor.getString(cursor.getColumnIndex("weather_id")));
                county.setCityId(cityId);
                list.add(county);
            }while (cursor.moveToNext());
        }
        return list;
    }
}
