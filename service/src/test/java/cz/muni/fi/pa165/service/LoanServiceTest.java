package cz.muni.fi.pa165.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import cz.muni.fi.pa165.dao.LoanDao;
import cz.muni.fi.pa165.entity.Loan;

@RunWith(MockitoJUnitRunner.class)
public class LoanServiceTest {

    @Mock
    LoanDao daoMock;

    @InjectMocks
    LoanService service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate() {
        Loan loan = new Loan();
        service.create(loan);
        verify(daoMock).create(loan);
    }

}
