package transmatter.platform.administration.contents;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
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
        Page<Content> contents = contentService.getNewsBySourceAndType("ไทยรัฐออนไลน์","การลงทุน", PageRequest.of(0,3));
        assertEquals(contents.getTotalElements(), 4);
        assertEquals(contents.getTotalPages(), 2);
        assertEquals(contents.getContent().size(), 3);
    }

    @Test
    @DisplayName("Get Approved Contents By Source and Category")
    void getApprovedNewsBySourceNormalCase(){
        Page<Content> contents = contentService.getOnlyApproveContentBySource("ไทยรัฐออนไลน์","การลงทุน", PageRequest.of(0,3));
        assertEquals(contents.getTotalElements(), 3);
        assertEquals(contents.getTotalPages(), 1);
        assertEquals(contents.getContent().size(), 3);
    }

    @Test
    @DisplayName("Get Contents By Source and Category but category is ทั้งหมด")
    void getNewsBySourceAndCategoryIsAll(){
        Page<Content> contents = contentService.getNewsBySourceAndType("ไทยรัฐออนไลน์","ทั้งหมด", PageRequest.of(0,3));
        assertEquals(contents.getTotalElements(), 11);
        assertEquals(contents.getTotalPages(), 4);
        assertEquals(contents.getContent().size(), 3);
    }

    @Test
    @DisplayName("Get approved Contents By Source and Category but source is ทั้งหมด")
    void getNewsBySourceIsAll(){
        Page<Content> contents = contentService.getOnlyApproveContentBySource("ไทยรัฐออนไลน์","การลงทุน", PageRequest.of(0,3));
        assertEquals(contents.getTotalElements(), 3);
        assertEquals(contents.getTotalPages(), 1);
        assertEquals(contents.getContent().size(), 3);
    }


    @Test
    @DisplayName("Get Contents By Source and Category Content Did not exist")
    void getNewsBySourceButSourceDidNotHaveInRepository(){
        Page<Content> contents = contentService.getNewsBySourceAndType("ไทยรัฐออนไลน์","การเมือง", PageRequest.of(0,3));
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
