package com.sandav.pruebatecnica.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;

import com.sandav.pruebatecnica.repository.custom.SpaceshipRepositoryCustom;
import com.sandav.pruebatecnica.service.SpaceshipService;
import com.sandav.pruebatecnica.valueObject.Spaceship;

@SpringBootTest
public class SpaceshipServiceImplTest {
	@Autowired
	private SpaceshipService spaceshipService;
	@MockBean
	private SpaceshipRepositoryCustom spaceshipRepositoryCustomMock;

	@Test
	void contextLoads() throws Exception {
		assertThat(spaceshipService).isNotNull();
	}
	
	@Test
	public void findById_when_is_OK() {
		Long id = 1L;
		Spaceship spaceshipMock = new Spaceship();
		when(spaceshipRepositoryCustomMock.findById(id)).thenReturn(spaceshipMock);
		assertThat(spaceshipService.findById(id)).isNotNull();
		verify(spaceshipRepositoryCustomMock, times(1)).findById(id);
	}
	
	@Test
	public void findById_when_id_is_less_than_1() {
		assertThrows(IllegalArgumentException.class, () -> {
			spaceshipService.findById(-1L);
	    });
		assertThrows(IllegalArgumentException.class, () -> {
			spaceshipService.findById(0L);
	    });
		verify(spaceshipRepositoryCustomMock, times(0)).findById(-1L);
		verify(spaceshipRepositoryCustomMock, times(0)).findById(0L);
	}
	
	@Test
	public void findAll_when_is_OK() {
		List<Spaceship> spaceshipListMock = new ArrayList<>(16);
		Integer page = 0;
		Integer size = 20;
		when(spaceshipRepositoryCustomMock.findAll(PageRequest.of(page, size))).thenReturn(spaceshipListMock);
		List<Spaceship> resultList = spaceshipService.findAll(page, size);
		assertThat(resultList).isNotNull();
		assertEquals(resultList.size(), spaceshipListMock.size());
		verify(spaceshipRepositoryCustomMock, times(1)).findAll(PageRequest.of(page, size));
	}
	
	@Test
	public void findAll_when_is_params_are_less_than_1() {
		Integer page = -1;
		Integer size = -1;
		assertThrows(IllegalArgumentException.class, () -> {
			spaceshipService.findAll(page, size);
	    });
		
	}
	
	@Test
	public void findByNameContains_when_is_OK() {
		List<Spaceship> spaceshipListMock = new ArrayList<>(3);
		String searchKey = "Enter";
		when(spaceshipRepositoryCustomMock.findByNameContains(searchKey)).thenReturn(spaceshipListMock);
		List<Spaceship> resultList = spaceshipService.findByNameContains(searchKey);
		assertThat(resultList).isNotNull();
		assertEquals(resultList.size(), spaceshipListMock.size());
		verify(spaceshipRepositoryCustomMock, times(1)).findByNameContains(searchKey);
	}
	
	@Test
	public void delete_when_is_OK() {
		Long id = 1L;
		doNothing().when(spaceshipRepositoryCustomMock).deleteById(id);
		spaceshipService.delete(id);
		verify(spaceshipRepositoryCustomMock, times(1)).deleteById(id);
	}
	
	@Test
	public void delete_when_id_is_negative() {
		Long id = -1L;
		doNothing().when(spaceshipRepositoryCustomMock).deleteById(id);
		assertThrows(IllegalArgumentException.class, () -> {
			spaceshipService.delete(id);
	    });
		verify(spaceshipRepositoryCustomMock, times(0)).deleteById(id);
	}
	
	@Test
	public void create_when_is_OK() {
		Spaceship spaceshipMock = new Spaceship();
		when(spaceshipRepositoryCustomMock.create(spaceshipMock)).thenReturn(spaceshipMock);
		assertThat(spaceshipService.create(spaceshipMock)).isNotNull();
		verify(spaceshipRepositoryCustomMock, times(1)).create(spaceshipMock);
	}
	
	@Test
	public void create_when_id_exists() {
		Spaceship spaceshipMock = new Spaceship();
		spaceshipMock.setId(1L);
		assertThrows(IllegalArgumentException.class, () -> {
			spaceshipService.create(spaceshipMock);
	    });
		verify(spaceshipRepositoryCustomMock, times(0)).create(spaceshipMock);
	}
	
	@Test
	public void update_when_is_OK() {
		Spaceship spaceshipMock = new Spaceship();
		spaceshipMock.setId(1L);
		when(spaceshipRepositoryCustomMock.update(spaceshipMock)).thenReturn(spaceshipMock);
		assertThat(spaceshipService.update(spaceshipMock)).isNotNull();
		verify(spaceshipRepositoryCustomMock, times(1)).update(spaceshipMock);
	}
	
	@Test
	public void update_when_id_not_exists() {
		Spaceship spaceshipMock = new Spaceship();
		assertThrows(IllegalArgumentException.class, () -> {
			spaceshipService.update(spaceshipMock);
	    });
		verify(spaceshipRepositoryCustomMock, times(0)).update(spaceshipMock);
	}
	
	
	
	
}