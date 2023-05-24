//package com.example.demo;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.example.demo.model.Ticket;
//import com.example.demo.repository.TicketRepository;
//import com.example.demo.service.TicketServiceImpl;
//
//@AutoConfigureMockMvc
//@SpringBootTest
//public class TicketServiceImplTest {
//
//	@Mock
//	private TicketRepository ticketRepo;
//	
//	@InjectMocks
//	private TicketServiceImpl ticketService;
//	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@BeforeEach
//	public void init()
//	{
//		MockitoAnnotations.openMocks(this);
//		mockMvc = MockMvcBuilders.standaloneSetup(ticketService).build();
//	}
//	
//	@Test
//	public void deleteTicketSuccess() throws Exception{
//		
//		Ticket t=new Ticket();
//		
//		t.setBookedSeats(20);
//		t.setBookingName("Ayantika");
//		t.setMovie_id_fk(101);
//		t.setSeatsAvailable(80);
//		t.setTransactionId(1);
//		
//		ticketRepo.delete(t);
//		
//		assertTrue(ticketService.deleteTicket(101));
//		
//		
//	}
//	
//	
//	
//	
//}
