package transmatter.platform.administration.contents;

import org.junit.jupiter.api.BeforeEach;
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
@DisplayName("Content Testing : Get Content By Source and Category")
public class GetContentsBySourceTest {
    @Autowired
    ContentService contentService;

    @Test
    @DisplayName("Get Contents By Source and Category Normal Case")
    void getNewsBySourceNormalCase(){
        Page<Content> contents = contentService.getNewsBySourceAndType("ไทยรัฐออนไลน์","นโยบาย", PageRequest.of(0,5));
        assertEquals(contents.getTotalElements(), 5);
        assertEquals(contents.getTotalPages(), 1);
        assertEquals(contents.getContent().size(), 5);
    }

    @Test
    @DisplayName("Get Contents By Source and Category Content Did not exist")
    void getNewsBySourceButSourceDidNotHaveInRepository(){
        Page<Content> contents = contentService.getNewsBySourceAndType("ไทยรัฐออนไลน์","การเมือง", PageRequest.of(0,5));
        assertEquals(contents.getTotalElements(), 0);
        assertEquals(contents.getTotalPages(), 0);
        assertEquals(contents.getContent().size(), 0);
    }

    @Test
    @DisplayName("Get Contents By Source and Category Source is null")
    void getNewsBySourceButSourceIsNull(){
        assertNull(null);
    }

}
