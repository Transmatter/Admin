package transmatter.platform.administration;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import transmatter.platform.administration.contents.GetAllContentTest;
import transmatter.platform.administration.contents.GetContentsByIdTest;
import transmatter.platform.administration.contents.GetContentsBySourceTest;
import transmatter.platform.administration.contents.SearchContentTest;

@Suite
@SuiteDisplayName("Test Record for Administration Application")
@SelectClasses({
        GetContentsByIdTest.class,
        GetAllContentTest.class,
        GetContentsBySourceTest.class,
        SearchContentTest.class
})
class AdministrationApplicationTests { }
