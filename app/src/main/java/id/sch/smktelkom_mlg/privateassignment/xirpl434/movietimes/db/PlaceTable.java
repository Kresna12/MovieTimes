package id.sch.smktelkom_mlg.privateassignment.xirpl434.movietimes.db;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import id.sch.smktelkom_mlg.privateassignment.xirpl434.movietimes.News_list;

public class PlaceTable extends Table {

    public static final String NAME = "tplace";

    public static final String[] COLNAME =
            new String[]{"IMAGE", "JUDUL", "POPULAR", "DETILDESKRIPSI"};
    public static final String[] COLTYPE = new String[]{"TEXT", "TEXT", "TEXT", "TEXT"};

    public static List<News_list> ITEMS = new ArrayList<News_list>();

    public static String getSQLCreate() {
        return getSQLCreateParam(NAME, COLNAME, COLTYPE);
    }

    public static String getSQLDrop() {
        return getSQLDropParam(NAME);
    }

    private static ContentValues getValues(News_list news_list) {
        ContentValues values = new ContentValues();
        values.put(COLNAME[0], news_list.getImage());
        values.put(COLNAME[1], news_list.getJudul());
        values.put(COLNAME[2], news_list.getPopular());

        return values;
    }

    public static void getAll(DatabaseHandler db) {
        ITEMS.clear();
        Cursor cursor = db.getAll(NAME);

        if (cursor != null && cursor.getCount() > 0) {
            do {
                News_list place = new News_list(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2));
                ITEMS.add(place);
            } while (cursor.moveToNext());
        }
    }

    public static void getPlaceWhereJudul(DatabaseHandler db, String judul) {
        ITEMS.clear();
        String query = "SELECT " + NAME + "." + COLNAME[0] + ", " + NAME + "." + COLNAME[1] +
                ", " + NAME + "." + COLNAME[2] + ", " + NAME + "." + COLNAME[3] +
                " FROM " + NAME +
                " WHERE " + NAME + "." + COLNAME[0] + "=?";
        Cursor cursor = db.getWhere(query, new String[]{judul});
        if (cursor != null && cursor.getCount() > 0) {
            do {
                News_list place = new News_list(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2));
                ITEMS.add(place);
            } while (cursor.moveToNext());
        }
    }

    public static boolean isEmpty(DatabaseHandler db) {
        return !db.isExist(NAME, null, null, null);
    }

    private static boolean isExist(DatabaseHandler db, String colName, String value) {
        return db.isExist(NAME, new String[]{colName}, colName + "=?", new String[]{value});
    }

    public static boolean isExistPlace(DatabaseHandler db, String judul) {
        return isExist(db, COLNAME[1], judul);
    }

    public static void add(DatabaseHandler db, News_list news_list) {
        db.add(NAME, getValues(news_list));
    }

    public static void updateWhereJudul(DatabaseHandler db, String judul, News_list place) {
        db.update(NAME, getValues(place), COLNAME[0] + "=?", new String[]{judul});
    }

    private static void delete(DatabaseHandler db, String colName, String value) {
        db.delete(NAME, colName + "=?", new String[]{value});
    }

    public static void delete(DatabaseHandler db, News_list place) {
        delete(db, COLNAME[0], place.getJudul());
    }

}
