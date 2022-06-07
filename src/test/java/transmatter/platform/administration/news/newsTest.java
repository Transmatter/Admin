package transmatter.platform.administration.news;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import transmatter.platform.administration.news.entity.News;
import transmatter.platform.administration.news.repository.NewsRepository;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class newsTest {
    @Autowired
    NewsRepository newsRepository;

    List<News> news;

    @BeforeEach
    void init(){
        this.news = newsRepository.findAll();
    }

    @Test
    void getNews(){}

    @Test
    void GetNewsById(){}

    @Test
    void GetNewsBySearching(){}

    @Test
    void GetNewsFromThairath(){}

    @Test
    void GetNewsFromSanook(){}

    @Test
    void GetNewsFromDekD(){}

    @Test
    void GetNewsFromOther(){}
}
