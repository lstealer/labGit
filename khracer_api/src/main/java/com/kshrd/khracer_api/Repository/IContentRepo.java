package com.kshrd.khracer_api.Repository;


import com.kshrd.khracer_api.Repository.Model.KHContentModel;
import com.kshrd.khracer_api.Repository.Model.TypingContentModel;
import com.kshrd.khracer_api.Repository.Provider.ContentProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface IContentRepo {

    @SelectProvider(value = ContentProvider.class,method = "SelectRandomContentSQL")
    @Result(column = "content_id",property = "id")
    @Result(column = "kh_content",property = "khContent")
    @Result(column = "content_title",property = "title")
    @Result(column = "content_description",property = "description")
    @Result(column = "content_author",property = "author")
    @Result(column = "content_image",property = "image")
    @Result(column = "is_disabled",property = "isDisabled")
    TypingContentModel selectRandomContent();

    @InsertProvider(value = ContentProvider.class, method = "InsertContentSQL")
    boolean InsertContent(TypingContentModel typingContentModel);

    @DeleteProvider(value = ContentProvider.class, method="DeleteContentSQL")
    boolean DeleteContent(int id);

    @UpdateProvider(value = ContentProvider.class,method = "UpdateKHContentSQL")
    boolean UpdateKHContent(KHContentModel khContentModel);

    @Select("SELECT content_id FROM typing_contents WHERE content_id = #{contentId}")
    String selectContentId(int contentId);
}
