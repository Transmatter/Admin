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
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DisplayName("Content Testing : Get All Contents")
public class GetAllContentTest {
    @Autowired
    ContentService contentService;

    @Test
    @DisplayName("Get All Contents as pagination Normal Case")
    void getAllContentsNormalCase(){
        Page<Content> contents = contentService.getAllContents(PageRequest.of(1,10));
        assertEquals(contents.getTotalElements(), 39);
        assertEquals(contents.getTotalPages(), 4);
        assertEquals(contents.getContent().size(), 10);
    }
}
