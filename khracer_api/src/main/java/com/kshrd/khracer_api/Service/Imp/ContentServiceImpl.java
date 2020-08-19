package com.kshrd.khracer_api.Service.Imp;

import com.kshrd.khracer_api.Repository.IContentRepo;
import com.kshrd.khracer_api.Repository.Model.KHContentModel;
import com.kshrd.khracer_api.Repository.Model.TypingContentModel;
import com.kshrd.khracer_api.Service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl implements ContentService {
    private IContentRepo iContentRepo;
    @Autowired
    public void setIContentRepo(IContentRepo iContentRepo){
        this.iContentRepo=iContentRepo;
    }

    @Override
    public TypingContentModel selectRandomContent() {
        TypingContentModel typingContentModel=iContentRepo.selectRandomContent();
        return typingContentModel.isDisabled()?
                iContentRepo.selectRandomContent():typingContentModel;
    }

    @Override
    public boolean InsertContent(TypingContentModel typingContentModel) {
        if(typingContentModel.getKhContent().equals("")||
                typingContentModel.getKhContent().equals(null)||
            typingContentModel.getAuthor().equals(null)||
                typingContentModel.getAuthor().equals("")||
                typingContentModel.getTitle().equals(null)||
                typingContentModel.getTitle().equals("")
        )
            throw new NullPointerException("recheck all field");
        System.out.println("Service");
        return iContentRepo.InsertContent(typingContentModel);
    }

    @Override
    public boolean DeleteContent(int id) {
        return iContentRepo.DeleteContent(id);
    }

    @Override
    public boolean UpdateKHContent(KHContentModel khContentModel) {
        return iContentRepo.UpdateKHContent(khContentModel);
    }
}
