package in.ineuron.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ineuron.client.StockClient;

@RestController
@RequestMapping("/calc")
public class StockCalculationRestController {

	@Autowired
	private StockClient client;
	
	@GetMapping("/calculation/{companyName}/{quantity}")
	public ResponseEntity<?> calculate(@PathVariable String companyName,@PathVariable Integer quantity){
		
		ResponseEntity<?> responseEntity=null;
		Double totalPrice=null;
		
		try {
			responseEntity=client.getStockPrice(companyName);
			int statusCode=responseEntity.getStatusCode().value();
			
			if(statusCode==200) {
				Double stockPrice=(Double)responseEntity.getBody();
				totalPrice=stockPrice*quantity;
				String totalCost="Total cost : "+totalPrice;
				responseEntity=new ResponseEntity<String>(totalCost,HttpStatus.OK);
			}
			
		}catch(Exception e) {
			responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
}
