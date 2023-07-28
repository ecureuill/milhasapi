package ecureuill.milhasapi.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ecureuill.milhasapi.GenerateData;
import ecureuill.milhasapi.domain.testimonial.Testimonial;
import ecureuill.milhasapi.domain.testimonial.TestimonialCreateRecord;
import ecureuill.milhasapi.domain.testimonial.TestimonialDetailRecord;
import ecureuill.milhasapi.domain.testimonial.TestimonialRepository;
import ecureuill.milhasapi.domain.testimonial.TestimonialUpdateRecord;
import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class TestimonialControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<TestimonialDetailRecord> testimonialDetailRecord;
    
    @Autowired
    private JacksonTester<List<TestimonialDetailRecord>> testimonialsDetailRecord;


    @Autowired
    private JacksonTester<TestimonialCreateRecord> testimonialCreateRecord;

    @Autowired 
    JacksonTester<TestimonialUpdateRecord> testimonialUpdateRecord;

    @MockBean
    private TestimonialRepository repository;
    
    @Test
    @DisplayName("Should return status OK")
    void testGetAllStatusCode() throws Exception {
        var data = Collections.<Testimonial>emptyList();
        Mockito.when(repository.findAll()).thenReturn(data);

        var response = mockMvc
            .perform(get("/depoimentos"))
            .andReturn().getResponse();
                
        var record = Collections.<TestimonialDetailRecord>emptyList();
        var json = testimonialsDetailRecord.write(record).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(json);
    }   

    @Test
    @DisplayName("Should return list of TestimonialDetailRecord")
    void testGetAllContent() throws Exception {
        var data = GenerateData.randomTestimonials(4);
        Mockito.when(repository.findAll()).thenReturn(data);

        var response = mockMvc
            .perform(get("/depoimentos"))
            .andReturn().getResponse();
        
        
        var record = data.stream().map(TestimonialDetailRecord::new).collect(Collectors.toList());
        var json = testimonialsDetailRecord.write(record).getJson();

        assertThat(response.getContentAsString()).isEqualTo(json);
    }

    @Test
    @DisplayName("Should return empty list of TestimonialDetailRecord")
    void testGetAllStatusEmptyContent() throws Exception {
        var data = Collections.<Testimonial>emptyList();
        
        Mockito.when(repository.findAll()).thenReturn(data);

        var response = mockMvc
            .perform(get("/depoimentos"))
            .andReturn().getResponse();
                
        var record = Collections.<TestimonialDetailRecord>emptyList();
        var json = testimonialsDetailRecord.write(record).getJson();

        assertThat(response.getContentAsString()).isEqualTo(json);
    }

    @Test
    @DisplayName("Should return status CREATED when request body is valid")
    void testSaveStatusCodeCreated() throws Exception {
        var data = GenerateData.randomTestimonial();
        var record = new TestimonialCreateRecord(data.getName(), data.getPhoto(), data.getTestimonial());
        var json = testimonialCreateRecord.write(record).getJson();

        Mockito.when(repository.save(any())).thenReturn(data);

        var response = mockMvc
            .perform(post("/depoimentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andReturn().getResponse();
        
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

    }
    
    @Test
    @DisplayName("Should return status BAD REQUEST when request body is invalid")
    void testSaveStatusCodeBadRequest() throws Exception {
        var record = new TestimonialCreateRecord(null, null,  null);
        var json = testimonialCreateRecord.write(record).getJson();

        var response = mockMvc
            .perform(post("/depoimentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andReturn().getResponse();
        
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DisplayName("Should return created TestimonialDetailRecord when request body is valid")
    void testSaveContent() throws Exception {
        var data = GenerateData.randomTestimonial();
        var requestJson = testimonialCreateRecord.write(new TestimonialCreateRecord(data.getName(), data.getPhoto(), data.getTestimonial())).getJson();
        var responseJson = testimonialDetailRecord.write(new TestimonialDetailRecord(data)).getJson();

        Mockito.when(repository.save(any())).thenReturn(data);

        var response = mockMvc
            .perform(post("/depoimentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
            .andReturn().getResponse();
        
        assertThat(response.getContentAsString()).isEqualTo(responseJson);

    }
    
    @Test
    @DisplayName("Should return status BAD REQUEST when request body is invalid")
    void testUpdateStatusCodeBadRequest() throws Exception {
        var record = new TestimonialUpdateRecord(null, null,  null);
        var json = testimonialUpdateRecord.write(record).getJson();

        var response = mockMvc
            .perform(put("/depoimentos/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andReturn().getResponse();
        
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }
    
    @Test
    @DisplayName("Should return status OK when request body is valid")
    void testUpdateStatusCodeOK() throws Exception {
        var data = GenerateData.randomTestimonial();
        var updatedData = GenerateData.randomTestimonial();
        var record = new TestimonialUpdateRecord(updatedData.getName(), updatedData.getTestimonial(), updatedData.getPhoto());
        var json = testimonialUpdateRecord.write(record).getJson();

        Mockito.when(repository.getReferenceById(any())).thenReturn(data);

        var response = mockMvc
            .perform(put("/depoimentos/{id}", data.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andReturn().getResponse();
        
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
    
    @Test
    @DisplayName("Should return updated TestimonialDetailRecord when request body is valid")
    void testUpdateContent() throws Exception {
        var data = GenerateData.randomTestimonial();
        var requestJson = testimonialUpdateRecord.write(new TestimonialUpdateRecord(data.getName(), "altered testimonial", data.getPhoto())).getJson();
        var responseJson = testimonialDetailRecord.write(new TestimonialDetailRecord(data.getName(),data.getPhoto(), "altered testimonial")).getJson();

        Mockito.when(repository.getReferenceById(any())).thenReturn(data);

        var response = mockMvc
            .perform(put("/depoimentos/{id}", data.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
            .andReturn().getResponse();
        
        assertThat(response.getContentAsString()).isEqualTo(responseJson);

    }
    
    @Test
    @DisplayName("Should throw EntityNotFound and status NOTFOUND when path variable not exist")
    void testUpdateStatusCodeNotFound() throws Exception {
        var data = GenerateData.randomTestimonial();
        var requestJson = testimonialUpdateRecord.write(new TestimonialUpdateRecord(data.getName(), "altered testimonial", data.getPhoto())).getJson();
        
        Mockito.when(repository.getReferenceById(any())).thenThrow(new EntityNotFoundException("Unable to find ecureuill.milhasapi.domain.testimonial.Testimonial with id 99999"));

        mockMvc
            .perform(put("/depoimentos/{id}", data.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
            .andExpect(status().isNotFound())
            .andExpect(result -> assertTrue(result.getResolvedException() instanceof EntityNotFoundException));
    }

    @Test
    @DisplayName("Should return status NO CONTENT")
    void testDeleteStatucCodeNoContent() throws Exception {
        
        mockMvc
            .perform(delete("/depoimentos/{id}", 4))
            .andExpect(status().isNoContent());
        
        Mockito.verify(repository).deleteById(any());
    }
}
