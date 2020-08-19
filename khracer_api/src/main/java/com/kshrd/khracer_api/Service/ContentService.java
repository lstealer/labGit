package com.kshrd.khracer_api.Service;


import com.kshrd.khracer_api.Repository.Model.KHContentModel;
import com.kshrd.khracer_api.Repository.Model.TypingContentModel;

public interface ContentService {


    TypingContentModel selectRandomContent();

    boolean InsertContent(TypingContentModel typingContentModel);

    boolean DeleteContent(int id);

    boolean UpdateKHContent(KHContentModel khContentModel);
}
