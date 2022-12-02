package com.demo.coin;

import com.demo.coin.controller.CoinController;
import com.demo.coin.repository.CoinRepository;
import com.demo.coin.request.CoinRequest;
import com.demo.coin.service.CoinService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CoinController.class)
class CoinApplicationTests {

    @MockBean
    private CoinRepository coinRepository;

    @Autowired
    CoinService coinService;

    ObjectMapper mapper = new ObjectMapper();

    String code = "KRW";

    @Autowired
    private MockMvc mvc; //創建MockMvc類的物件

    @Test
    void addCoin() throws Exception {
        CoinRequest request = new CoinRequest();
        request.setCode(code);
        request.setName("韓元");
        request.setSymbol("&krw");
        request.setRate("30.1568");
        request.setRateFloat(30.1568);
        request.setDescription("korean dollar");

        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(request);
        mvc.perform(post("/coin/add").content(requestJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getCoin() throws Exception {
        MvcResult result = mvc.perform(get("/coin/get").param("code",code))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void updateCoin() throws Exception {
        CoinRequest request = new CoinRequest();
        request.setCode(code);
        request.setName("韓元");
        request.setSymbol("&krw");
        request.setRate("31.5562");
        request.setRateFloat(31.5562);
        request.setDescription("korean dollar");

        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(request);
        MvcResult mvcResult = mvc.perform(post("/coin/update").content(requestJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    void deleteCoin() throws Exception {
        mvc.perform(delete("/coin/delete").param("code",code))
                .andExpect(status().isOk());
    }

    @Test
    void coinDeskTest() throws Exception {
        MvcResult result = mvc.perform(get("/coin/callCoinDesk"))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void coinTranData() throws Exception {
        MvcResult result = mvc.perform(get("/coin/tranData"))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

}
