package android.goodweather.com.goodweather.db.entity;

/**
 * Created by Administrator on 2016-12-15.
 */

public class Province {
    private int id;
    private String proviceName;
    private String proviceeCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProviceName() {
        return proviceName;
    }

    public void setProviceName(String proviceName) {
        this.proviceName = proviceName;
    }

    public String getProviceeCode() {
        return proviceeCode;
    }

    public void setProviceeCode(String proviceeCode) {
        this.proviceeCode = proviceeCode;
    }
}
