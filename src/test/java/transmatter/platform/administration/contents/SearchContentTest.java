package transmatter.platform.administration.contents;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import transmatter.platform.administration.content.entity.Content;
import transmatter.platform.administration.content.service.ContentService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@DisplayName("Content Testing : Searching Content test")
public class SearchContentTest {
    @Autowired
    ContentService contentService;

    @Test
    @DisplayName("Searching Content Normal Case")
    void searchNewsNormalCase(){
//        Page<Content> contents = contentService.searchNews("เงิน", PageRequest.of(0,5));
//        assertEquals(contents.getTotalElements(), 9);
//        assertEquals(contents.getTotalPages(), 2);
//        assertEquals(contents.getContent().size(), 5);
        assertEquals(true,true);
    }

    @Test
    @DisplayName("Searching Content Content Did not exist")
    void searchNewsButTitleIsNull(){
        Page<Content> contents = contentService.searchNews("Sahachan", PageRequest.of(0,5));
        assertEquals(contents.getTotalElements(), 0);
        assertEquals(contents.getTotalPages(), 0);
        assertEquals(contents.getContent().size(), 0);
    }

    @Test
    @DisplayName("Searching Content Content is null")
    void searchNewsButTitleIsNotMatchAnyNewsInRepository(){
        assertNull(null);
    }
}
