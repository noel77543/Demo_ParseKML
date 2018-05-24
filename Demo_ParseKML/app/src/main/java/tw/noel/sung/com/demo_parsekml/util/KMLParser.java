package tw.noel.sung.com.demo_parsekml.util;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import com.google.android.gms.maps.model.LatLng;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by noel on 2018/5/23.
 */

public class KMLParser {
    private XmlPullParser xmlPullParser;
    private Context context;
    private InputStreamReader inputStreamReader;
    private String dataStr;
    private ArrayList<LatLng> pointArrayList;

    //---------
    public KMLParser(Context context) {
        this.context = context;
        init();
    }
    //------------

    /***
     *  init
     */
    private void init() {
        pointArrayList = new ArrayList<>();
        xmlPullParser = Xml.newPullParser();
    }


    //------------

    /***
     * "test.kml"
     *  cast to string
     * @return
     */
    private String getStringFromInputStream(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(context.getAssets().open(fileName), "utf-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String str;
            while ((str = reader.readLine()) != null) {
                sb.append(str);
            }
            reader.close();
            inputStreamReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    //------------

    /***
     *  依序取得 起點 路徑 終點 的點位
     */
    public ArrayList<LatLng> getPoints(String fileName) {
        dataStr = getStringFromInputStream(fileName);
        try {
            xmlPullParser.setInput(new StringReader(dataStr));
            for (int eventType = xmlPullParser.getEventType(); eventType != XmlPullParser.END_DOCUMENT; eventType = xmlPullParser.next()) {
                if (eventType == XmlPullParser.START_TAG && xmlPullParser.getName().equals("coordinates")) {
                    String point = xmlPullParser.nextText().trim();
                    Log.e("a", point);

                    String[] pointArray = point.split(",|\\ ");
                    for (int i = 0; i < pointArray.length; i += 3) {
                        Log.e("b", pointArray[i+1]);
//                        pointArrayList.add(new LatLng(Double.parseDouble(pointArray[i + 1]), Double.parseDouble(pointArray[i])));
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return pointArrayList;
    }
}
