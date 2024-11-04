package com.enetcom.roomwordsample.data;


import android.app.Application;

import androidx.lifecycle.LiveData;

import com.enetcom.roomwordsample.data.db.WordDao;
import com.enetcom.roomwordsample.data.db.WordRoomDatabase;
import com.enetcom.roomwordsample.model.Word;

import java.util.List;

public class WordRepository {

    private final WordDao mWordDao;
    private final LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> mWordDao.insert(word));
    }
}