package ua.in.dris4ecoder.webControllersTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import ua.in.dris4ecoder.springTestConfigClasses.TestConfig;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;

/**
 * Created by admin on 30.11.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
public class MainWindowWebControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.annotationConfigSetup(TestConfig.class).build();
    }

    @Test
    public void indexTest() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/content/index.jsp"))
                .andExpect(model().attributeExists("authority"));
    }

    @Test
    public void mainPageTest() throws Exception {

        mockMvc.perform(get("/mainPage"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/content/mainPage.jsp"));
    }
}
