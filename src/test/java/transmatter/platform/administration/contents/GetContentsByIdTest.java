package transmatter.platform.administration.contents;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import transmatter.platform.administration.content.entity.Content;
import transmatter.platform.administration.content.exception.ContentNotFoundException;
import transmatter.platform.administration.content.service.ContentService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Content Testing : Get Individual Contents By Id")
public class GetContentsByIdTest {

    @Autowired
    ContentService contentService;

    @Test
    @DisplayName("Get Contents Normal Case")
    void getContentByIdNormalCase(){
        Content content = contentService.getContent("62fb4e5b8177f9a0836a28e0");
        assertEquals(content.getTitle(), "TOA เผยกำไรไตรมาส 2/65 แตะ 473 ล้าน เตรียมปันผล 0.25 บาทต่อหุ้น");
        assertEquals(content.getAuthor(), "ไทยรัฐออนไลน์");
        assertEquals(content.getSource(), "ไทยรัฐออนไลน์");
        assertEquals(content.getCategory(), "การลงทุน");
        assertTrue(true);
    }

    @Test
    @DisplayName("Get Contents Content Did not exist")
    void getContentIdDoesNotExist(){
        assertThrows(ContentNotFoundException.class, () -> {
            contentService.getContent("Sahachan");
        });
    }

    // todo - add a exception for Content is null
    @Test
    @DisplayName("Get Contents ,But id is null")
    void getContentByIdIsNull(){
        assertNull(null);
    }
}
