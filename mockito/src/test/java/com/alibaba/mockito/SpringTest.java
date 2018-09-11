package com.alibaba.mockito;

import com.alibaba.mockito.service.Configuration;
import com.alibaba.mockito.service.DaoService;
import com.alibaba.mockito.service.Service;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.when;

/**
 * @author sier.pys 9/11/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Configuration.class)
public class SpringTest {
    @Mock
    private DaoService daoService;

    @Autowired
    @InjectMocks
    private Service service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(daoService.findOne()).thenReturn("mock mock object");
    }

    @Test
    public void testMockito() {
        System.out.println(service.find());
    }
}
