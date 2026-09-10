// package dev1.alexkjam64.SpringBootProject.service;


// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.bean.override.mockito.MockitoBean;
// import org.springframework.test.context.junit.jupiter.SpringExtension;

// import dev1.alexkjam64.SpringBootProject.repository.ClientUsername;
// import dev1.alexkjam64.SpringBootProject.repository.ClientRepository;

// @ExtendWith(SpringExtension.class)
// @ContextConfiguration(classes = {ClientService.class})
// public class ClientServiceTests {
//     @MockitoBean
//     private ClientRepository repoSample;

//     @Autowired
//     private ClientService service;

//     @Test
//     public void test1(){
//         ClientUsername test = new ClientUsername(1, "Bean", " ", "!");
//         try {
//             service.sanitizeData(test);
//         } catch (InvalidDataException e) {
//             Assertions.assertEquals("Last name is null or empty!", e.getMessage());
//         }
//     }

//     @Test
//     public void test2(){
//         ClientUsername test = new ClientUsername(1, "Bean", "Char!", null);
//         try {
//             service.sanitizeData(test);
//         } catch (InvalidDataException e) {
//             Assertions.assertEquals("Last name includes special characters!", e.getMessage());
//         }
//     }

//     @Test
//     public void test3(){
//         ClientUsername test = new ClientUsername(2, "Dango", "Flamingo", "Bingo");
//         try {
//             service.sanitizeData(test);
//         } catch (InvalidDataException e) {
//             Assertions.assertEquals("Middle initial surpasses 1 character!", e.getMessage());
//         }
//     }

//     @Test
//     public void test4(){
//         ClientUsername test = new ClientUsername(10, " ", null, null);
//         try {
//             service.sanitizeData(test);
//         } catch (InvalidDataException e) {
//             Assertions.assertEquals("First name is null or empty!", e.getMessage());
//         }
//     }

//     @Test
//     public void test5(){
//         ClientUsername test = new ClientUsername(10, "Not null", "null", null);
//         try {
//             service.sanitizeData(test);
//         } catch (InvalidDataException e) {
//             Assertions.assertEquals("First name includes special characters!", e.getMessage());
//         }
//     }

//     @Test
//     public void test6(){
//         ClientUsername test = new ClientUsername(0, "First", "Last", "Middle");
//         try {
//             service.sanitizeData(test);
//         } catch (InvalidDataException e) {
//             Assertions.assertEquals("Middle initial surpasses 1 character!", e.getMessage());
//         }
//     }
// }
