package transmatter.platform.administration.contents;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
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
        Content content = contentService.getContent("62ac4bae05c05d8fbd5a4908");
        assertEquals(content.getTitle(), "สภาดิจิทัลฯ ขอบคุณรัฐบาลออกพ.ร.ฎ.เว้น Capital Gains Tax ดันสตาร์ทอัพไทย");
        assertEquals(content.getAuthor(), "ไทยรัฐออนไลน์");
        assertEquals(content.getSource(), "ไทยรัฐออนไลน์");
        assertEquals(content.getType(), "นโยบาย");
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
