package com.example.tuanspk.baitaptuan09gamedapchuot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tuanspk.baitaptuan09gamedapchuot.Adapter.BinderData;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class TheRankingsActivity extends Activity {

    TextView textViewScore;

    Button buttonPlayAgain;

    ListView listViewTheRankings;

    // XML node keys
    static final String KEY_TAG = "rankdata";                                                       // parent node
    static final String KEY_ID = "id";
    static final String KEY_NAME = "name";
    static final String KEY_SCORE = "score";

    BinderData adapter = null;

    List<HashMap<String, String>> rankDataCollection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.the_rankings_layout);

        textViewScore = (TextView) findViewById(R.id.textview_score);
        textViewScore.setText(String.valueOf(PlayGameActivity.iScore));

        buttonPlayAgain = (Button) findViewById(R.id.button_play_again);
        buttonPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDestroy();
            }
        });

//        InputStream inputStream;

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(getAssets().open("the_rankings_data.xml"));

            rankDataCollection = new ArrayList<HashMap<String, String>>();

            // Normalize text representation
            document.getDocumentElement().normalize();

            NodeList rankList = document.getElementsByTagName("rankdata");

            HashMap<String, String> map = null;

            for (int i = 0; i < rankList.getLength(); i++) {
                map = new HashMap<String, String>();

                Node firstRankNode = rankList.item(i);
                if (firstRankNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element firstRankElement = (Element) firstRankNode;

                    // id
                    NodeList idList = firstRankElement.getElementsByTagName(KEY_ID);
                    Element firstIdElement = (Element) idList.item(0);
                    NodeList textIdList = firstIdElement.getChildNodes();
                    map.put(KEY_ID, ((Node) textIdList.item(0)).getNodeValue().trim());

                    // name
                    NodeList titleList = firstRankElement.getElementsByTagName(KEY_NAME);
                    Element firstTitleElement = (Element) titleList.item(0);
                    NodeList textTitleList = firstTitleElement.getChildNodes();
                    map.put(KEY_NAME, ((Node) textTitleList.item(0)).getNodeValue().trim());

                    // score
                    NodeList timesList = firstRankElement.getElementsByTagName(KEY_SCORE);
                    Element firstTimesElement = (Element) timesList.item(0);
                    NodeList textTimesList = firstTimesElement.getChildNodes();
                    map.put(KEY_SCORE, ((Node) textTimesList.item(0)).getNodeValue().trim());

                    // Add to the Arraylist
                    rankDataCollection.add(map);
                }
            }

            BinderData binderData = new BinderData(this, rankDataCollection);

            listViewTheRankings = (ListView) findViewById(R.id.listview_the_rankings);
            Log.i("BEFORE", "<<------------- Before SetAdapter-------------->>");
            listViewTheRankings.setAdapter(binderData);
            Log.i("AFTER", "<<------------- After SetAdapter-------------->>");

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent intent = new Intent(TheRankingsActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
