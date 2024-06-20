package in.ineuron.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="Stock-Service")
public interface StockClient {
	
	@GetMapping("/stock/price/{companyName}")
	public ResponseEntity<Double> getStockPrice(@PathVariable String companyName);
	
}
