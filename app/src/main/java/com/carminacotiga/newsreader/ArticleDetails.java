package com.carminacotiga.newsreader;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.carminacotiga.newsreader.ui.feature.adapter.ArticleAdapter;

public class ArticleDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details2);

        TextView titleTextView = findViewById(R.id.detailsTitle);
        TextView contentTextView = findViewById(R.id.detailsContent);

        Intent intent = getIntent();

        String title = intent.getStringExtra(ArticleAdapter.EXTRA_TITLE);
        String content = intent.getStringExtra(ArticleAdapter.EXTRA_CONTENT);

        titleTextView.setText(title);
        contentTextView.setText(content);

    }
}