package leapwise.internship.internshipProject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class MealControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mvc;

    @Test
    public void testGet() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/daily/menu")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testPost() throws Exception {
        List<Long> items = new ArrayList<>();

        items.add(1L);
        items.add(2L);

        String json = new ObjectMapper().writeValueAsString(items);

        this.mvc.perform(MockMvcRequestBuilders.post("/order").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    public void testPutOk() throws Exception {
        //first add order with post
        List<Long> items = new ArrayList<>();
        items.add(1L);
        items.add(2L);
        String json = new ObjectMapper().writeValueAsString(items);
        this.mvc.perform(MockMvcRequestBuilders.post("/order").contentType(MediaType.APPLICATION_JSON).content(json));

        //then change it with put
        List<Long> newItems = new ArrayList<>();

        newItems.add(5L);
        newItems.add(6L);

        json = new ObjectMapper().writeValueAsString(newItems);

        this.mvc.perform(MockMvcRequestBuilders.put("/order/1").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testPutNotOk() throws Exception {
        //try to change order, but order with provided id doesnt exist
        List<Long> newItems = new ArrayList<>();

        newItems.add(5L);
        newItems.add(6L);

        String json = new ObjectMapper().writeValueAsString(newItems);

        this.mvc.perform(MockMvcRequestBuilders.put("/order/7").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
