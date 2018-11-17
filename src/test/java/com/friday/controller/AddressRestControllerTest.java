package com.friday.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Diego de Souza on 16/11/18.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AddressRestControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void oneStreetnameAndOneDigitNumberNoCommaIT() throws Exception {
        final String content = "Winterallee 3";
        final String expectedReturn = "{\"street\": \"Winterallee\", \"housenumber\": \"3\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/parse/address/").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andExpect(MockMvcResultMatchers.content().json(expectedReturn));
    }

    @Test
    public void oneStreetnameAndTwoDigitsNumberNoCommaIT() throws Exception {
        final String content = "Musterstrasse 45";
        final String expectedReturn = "{\"street\": \"Musterstrasse\", \"housenumber\": \"45\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/parse/address/").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andExpect(MockMvcResultMatchers.content().json(expectedReturn));
    }

    @Test
    public void twoStreetnameAndtwoDigitsNumberNoCommaIT() throws Exception {
        final String content = "Am Bächle 23";
        final String expectedReturn = "{\"street\": \"Am Bächle\", \"housenumber\": \"23\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/parse/address/").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andExpect(MockMvcResultMatchers.content().json(expectedReturn));

    }

    @Test
    public void oneStreetnameThreeDigitNumberWithLetterNoCommaIT() throws Exception {
        final String content = "Blaufeldweg 123B";
        final String expectedReturn = "{\"street\": \"Blaufeldweg\", \"housenumber\": \"123B\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/parse/address/").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andExpect(MockMvcResultMatchers.content().json(expectedReturn));

    }

    @Test
    public void threeStreetnameTwoDigitsNumberWithLetterNoCommaIT() throws Exception {
        final String content = "Auf der Vogelwiese 23 b";
        final String expectedReturn = "{\"street\": \"Auf der Vogelwiese\", \"housenumber\": \"23 b\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/parse/address/").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andExpect(MockMvcResultMatchers.content().json(expectedReturn));

    }


    @Test
    public void numberStartingAddressWithCommaIT() throws Exception {
        final String content = "4, rue de la revolution";
        final String expectedReturn = "{\"street\": \"rue de la revolution\", \"housenumber\": \"4\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/parse/address/").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andExpect(MockMvcResultMatchers.content().json(expectedReturn));

    }

    @Test
    public void numberStartingAddressWithNoCommaIT() throws Exception {
        final String content = "200 Broadway Av";
        final String expectedReturn = "{\"street\": \"Broadway Av\", \"housenumber\": \"200\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/parse/address/").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andExpect(MockMvcResultMatchers.content().json(expectedReturn));

    }

    @Test
    public void twoStreetnameTwoDigitsNumberWithCommaIT() throws Exception {
        final String content = "Calle Aduana, 29";
        final String expectedReturn = "{\"street\": \"Calle Aduana\", \"housenumber\": \"29\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/parse/address/").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andExpect(MockMvcResultMatchers.content().json(expectedReturn));

    }

    @Test
    public void streetNameWithNumberOnItIT() throws Exception {
        final String content = "Calle 39 No 1540";

        final String expectedReturn = "{\"street\": \"Calle 39\", \"housenumber\": \"No 1540\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/parse/address/").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andExpect(MockMvcResultMatchers.content().json(expectedReturn));

    }

    @Test
    public void throwNoHousenumberExceptionTest() throws Exception {
        final String content = "Rua Ministro Calogeras esquina com Getulio Vargas";

        final String expectedReturn = "No house number found in the given address";

        mockMvc.perform(MockMvcRequestBuilders.post("/parse/address/").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(MockMvcResultMatchers.content().string(expectedReturn));

    }



}