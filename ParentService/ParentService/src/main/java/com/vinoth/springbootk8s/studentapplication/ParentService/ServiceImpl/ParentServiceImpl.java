package com.vinoth.springbootk8s.studentapplication.ParentService.ServiceImpl;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.vinoth.springbootk8s.studentapplication.ParentService.DTO.AddressDTO;
import com.vinoth.springbootk8s.studentapplication.ParentService.DTO.ParentDTO;
import com.vinoth.springbootk8s.studentapplication.ParentService.Entity.Parent;
import com.vinoth.springbootk8s.studentapplication.ParentService.Repository.ParentRepository;
import com.vinoth.springbootk8s.studentapplication.ParentService.Service.ParentService;

import reactor.core.publisher.Mono;

@Service
@Configurable
public class ParentServiceImpl implements ParentService {

	@Autowired
	private ParentRepository parentRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	
	@Override
	public long saveParent(Parent parent, AddressDTO address) {
//		long addressId = restTemplate.postForObject("http://ADDRESS-SERVICE/address/save", address, Long.class);		
		
		Mono<Long> monoAddressId = webClientBuilder.build()
				.post().uri("http://ADDRESS-SERVICE/address/save").bodyValue(address).retrieve().bodyToMono(Long.class);
		long addressId = monoAddressId.block();
		
		parent.setAddressId(addressId);
		return parentRepository.save(parent).getParentId();			

	}

	@Override
	public ParentDTO findParentById(long parentId) {
		Parent parent = parentRepository.findById(parentId).orElseThrow();
//		AddressDTO addressDTO = webClientBuilder.build().get()
//			.uri("http://ADDRESS-SERVICE/address/find/"+parent.getAddressId()).retrieve().bodyToFlux(AddressDTO.class)
//			.blockFirst();
		AddressDTO addressDTO = restTemplate.getForObject("http://ADDRESS-SERVICE/address/find/"+parent.getAddressId(), AddressDTO.class);
		ParentDTO parentDTO = modelMapper.map(parent, ParentDTO.class);
		parentDTO.setAddress(addressDTO);		
		return parentDTO;
	}
	
	@Override
	public List<ParentDTO> findAll() throws InterruptedException, ExecutionException{
		List<Parent> parents = parentRepository.findAll();	
		ExecutorService executorService = Executors.newCachedThreadPool();
	 	return parents.stream().map(parent ->  executorService.submit(new Callable<ParentDTO>() {

			@Override
			public ParentDTO call() throws Exception {
				AddressDTO addressDTO = webClientBuilder.build().get()
						.uri("http://ADDRESS-SERVICE/address/find/"+parent.getAddressId()).retrieve()
						.bodyToMono(AddressDTO.class).block();
				ParentDTO parentDTO = modelMapper.map(parent, ParentDTO.class);
				parentDTO.setAddress(addressDTO);
				return parentDTO;
			}
		})
		).map(list->{
			try {
				return list.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			return null;
		}).toList();		 		
	}

	@Override
	public List<Long> saveAll(List<ParentDTO> parentDTO) {		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		List<Parent> parent = parentDTO.stream().map(eachParentDTO -> executorService.submit(new Callable<Parent>() {
			public Parent call() throws Exception{
				long addressID = webClientBuilder.build().post()
						.uri("http://ADDRESS-SERVICE/address/save").bodyValue(eachParentDTO.getAddress())
						.retrieve().bodyToMono(Long.class).block();
				Parent parent = modelMapper.map(eachParentDTO, Parent.class);
				parent.setAddressId(addressID);
				return parent;
			}
		})
		).map(eachParent ->{ try {
			return eachParent.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return null;}).toList();
		
		return parentRepository.saveAll(parent).stream().map(Parent::getParentId).toList();
		 
	}

	@Override
	public String testAddress() {
		discoveryClient.getServices().forEach(System.out::println);
		discoveryClient.getInstances("address-service").forEach(System.out::println);
		return restTemplate.getForObject("http://ADDRESS-SERVICE/address", String.class);
	}

}
