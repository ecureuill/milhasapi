package ecureuill.milhasapi.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ecureuill.milhasapi.GenerateData;
import ecureuill.milhasapi.domain.destination.Destination;
import ecureuill.milhasapi.domain.destination.DestinationCreateRecord;
import ecureuill.milhasapi.domain.destination.DestinationDetailRecord;
import ecureuill.milhasapi.domain.destination.DestinationRepository;
import ecureuill.milhasapi.domain.destination.DestinationUpdateRecord;
import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class DestinationControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    JacksonTester<DestinationCreateRecord> destinationCreateRecord;

    @Autowired
    JacksonTester<DestinationDetailRecord> destinationDetailRecord;

    @Autowired
    JacksonTester<DestinationUpdateRecord> destinationUpdateRecord;

    @MockBean
    private DestinationRepository repository;
    
    @Test
    @DisplayName("Should return DestinationDetailed object when body is valid")
    void testSaveStatusCodeCreated() throws Exception {
        var data = GenerateData.randomDestination();
        var record = new DestinationCreateRecord(data.getName(), data.getPhoto(), data.getPhoto2(), data.getMeta(), data.getDescription(), data.getPrice());
        var json = destinationCreateRecord.write(record).getJson();

        var responseJson = destinationDetailRecord.write( new DestinationDetailRecord(data.getId(), data.getName(), data.getPhoto(), data.getPhoto2(), data.getMeta(), data.getDescription(), data.getPrice())).getJson();

        Mockito.when(repository.save(any())).thenReturn(data);

        mockMvc.perform(
            post("/destinos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(MockMvcResultMatchers.content().json(responseJson));
    }
    
    @Test
    @DisplayName("Should return status CREATED when body is valid")
    void testSaveContent() throws Exception {
        var data = GenerateData.randomDestination();
        var record = new DestinationCreateRecord(data.getName(), data.getPhoto(), data.getPhoto2(), data.getMeta(), data.getDescription(), data.getPrice());
        var json = destinationCreateRecord.write(record).getJson();

        Mockito.when(repository.save(any())).thenReturn(data);

        mockMvc.perform(
            post("/destinos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Should return status BADREQUEST when body is invalid")
    void testSaveStatusBadRequest() throws Exception {
        var record = new DestinationCreateRecord(null, null, null, null, null, null);
        var json = destinationCreateRecord.write(record).getJson();

        mockMvc.perform(
            post("/destinos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return status OK when list is empty")
    void testGetAllStatusOKEmptyList() throws Exception {
        var data = Collections.<Destination>emptyList();

        Mockito.when(repository.findAll()).thenReturn(data);

        mockMvc.perform(
            get("/destinos"))
            .andExpect(status().isOk()); 
    }

    @Test
    @DisplayName("Should return status OK")
    void testGetAllStatusOK() throws Exception {
        var data = GenerateData.randomDestinations(3);

        Mockito.when(repository.findAll()).thenReturn(data);
        
        mockMvc.perform(
            get("/destinos"))
            .andExpect(status().isOk()); 
    }

    @Test
    @DisplayName("Should return status OK when filter by name")
    void testGetAllStatusOKFilterByName() throws Exception {
        var data = GenerateData.randomDestinations(3);
        var nameFilter = "abc";
        Mockito.when(repository.findAllByNameStartingWith(nameFilter)).thenReturn(data);
        
        mockMvc.perform(
            get("/destinos")
                .param("name",nameFilter))
            .andExpect(status().isOk()); 
    }

    @Test
    @DisplayName("Should return status NotFound when filter by not existent name")
    void testGetAllStatusNotFoundFilterByName() throws Exception {
        var data = Collections.<Destination>emptyList();
        var nameFilter = "abc";
        Mockito.when(repository.findAllByNameStartingWith(nameFilter)).thenReturn(data);
        
        mockMvc.perform(
            get("/destinos")
                .param("name",nameFilter))
            .andExpect(status().isNotFound()); 
    }

    @Test
    @DisplayName("Should return status OK when get one")
    void testGetOneStatusOK() throws Exception  {
        var data = GenerateData.randomDestination();
        Mockito.when(repository.getReferenceById(data.getId())).thenReturn(data);
        
        mockMvc.perform(
                get("/destinos/{id}", data.getId()))
            .andExpect(status().isOk()); 
    }

    @Test
    @DisplayName("Should return status NOT FOUND when get one with not existent id")
    void testGetOneStatusNotFound() throws Exception  {
        var data = GenerateData.randomDestination();
        Mockito.when(repository.getReferenceById(data.getId())).thenThrow(EntityNotFoundException.class);
        
        mockMvc.perform(
                get("/destinos/{id}", data.getId()))
            .andExpect(status().isNotFound()); 
    }

    @Test
    void testUpdateStatusOK()throws Exception {

        var data = GenerateData.randomDestination();
        var record = new DestinationUpdateRecord(data.getName(), data.getPhoto(), data.getPhoto2(), data.getMeta(), data.getDescription(), data.getPrice());
        var json = destinationUpdateRecord.write(record).getJson();
        
        Mockito.when(repository.getReferenceById(data.getId())).thenReturn(data);
        
        mockMvc
            .perform(put("/destinos/{id}", data.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isOk());
    }

    @Test
    void testUpdateStatusNotFound()throws Exception {

        var data = GenerateData.randomDestination();
        var record = new DestinationUpdateRecord(data.getName(), data.getPhoto(), data.getPhoto2(), data.getMeta(), data.getDescription(), data.getPrice());
        var json = destinationUpdateRecord.write(record).getJson();
        
         Mockito.when(repository.getReferenceById(data.getId())).thenThrow(EntityNotFoundException.class);
        
        mockMvc.perform(
            put("/destinos/{id}", data.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isNotFound()); 
    }

    @Test
    void testDeleteStatusOK() throws Exception  {
        mockMvc.perform(
                delete("/destinos/{id}", 1L))
            .andExpect(status().isNoContent()); 

        Mockito.verify(repository).deleteById(1L);
    }
}
