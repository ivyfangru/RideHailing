package springcloud.dispatchservice;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "location-service")
public interface LocationServiceFeignClient extends LocationService{
}
