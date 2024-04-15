package SSVV;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import domain.Nota;
import domain.Tema;
import domain.Student;

import repository.*;

import service.Service;

import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

public class ServiceTest {
    private Service service;
    private StudentXMLRepository studentRepo;
    private TemaXMLRepository temaRepo;
    private NotaXMLRepository notaRepo;


    public void setUp() {

        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        studentRepo = new StudentXMLRepository(studentValidator, "studenti.xml");
        temaRepo = new TemaXMLRepository(temaValidator, "teme.xml");
        notaRepo = new NotaXMLRepository(notaValidator, "note.xml");

        service = new Service(studentRepo, temaRepo, notaRepo);
    }

//BLACK-BOX TESTING------------------------------------------------------------------------------------------------
    @Test
    public void testSaveStudent_Failure1() {
        setUp();
        int result = service.saveStudent("4", "John Doe", 88);
        assertEquals(1, result);
    }

    @Test
    public void testSaveStudent_Failure2() {
        setUp();
        int result = service.saveStudent("5", "Jane Doe", 109);
        assertEquals(1, result);
    }

    @Test
    public void testSaveStudent_Success1() {
        setUp();
        int result = service.saveStudent("6", "John Doe", 110);
        assertEquals(1, result);
    }

    @Test
    public void testSaveStudent_Success2() {
        setUp();
        int result = service.saveStudent("7", "Jane Doe", 111);
        assertEquals(1, result);
    }


    @Test
    public void testSaveStudent_Success3() {
        setUp();
        int result = service.saveStudent("8", "John Doe", 225);
        assertEquals(1, result);
    }

    @Test
    public void testSaveStudent_Success4() {
        setUp();
        int result = service.saveStudent("9", "Jane Doe", 937);
        assertEquals(1, result);
    }


    @Test
    public void testSaveStudent_Success5() {
        setUp();
        int result = service.saveStudent("10", "John Doe", 938);
        assertEquals(1, result);
    }

    @Test
    public void testSaveStudent_Failure3() {
        setUp();
        int result = service.saveStudent("11", "Jane Doe", 939);
        assertEquals(1, result);
    }


    @Test
    public void testSaveStudent_Failure4() {
        setUp();
        int result = service.saveStudent("12", "John Doe", 999);
        assertEquals(1, result);
    }


//WHITE-BOX TESTING------------------------------------------------------------------------------------------------
    @Test
    public void testSaveTema_ValidInput() {
        setUp();
        int result = service.saveTema("4", "descriere", 10, 5);
        assertEquals(1, result);
    }

    @Test
    public void testSaveTema_NullId() {
        setUp();
        int result = service.saveTema(null, "descriere", 10, 5);
        assertEquals(1, result);
    }

    @Test
    public void testSaveTema_NullDesc() {
        setUp();
        int result = service.saveTema("4", null, 10, 5);
        assertEquals(1, result);
    }

    @Test
    public void testSaveTema_InvalidDeadline() {
        setUp();
        int result = service.saveTema("5", "descriere", 15, 10);
        assertEquals(1, result);
    }

    @Test
    public void testSaveTema_InvalidStartline() {
        setUp();
        int result = service.saveTema("5", "descriere", 12, 0);
        assertEquals(1, result);
    }


    @Test
    public void testSaveTema_InvalidStartDeadline() {
        setUp();
        int result = service.saveTema("5", "descriere", 5, 10);
        assertEquals(1, result);
    }

    @Test
    public void testSaveTema_SuccessfulSave() {
        TemaXMLRepository temaXmlRepo = mock(TemaXMLRepository.class);
        when(temaXmlRepo.save(any())).thenReturn(new Tema("5", "descriere", 10, 5));

        Service service = new Service(null,temaXmlRepo,null);

        int result = service.saveTema("5", "descriere", 10, 5);

        verify(temaXmlRepo, times(1)).save(any());

        assertEquals(0, result);
    }

    @Test
    public void testSaveTema_UnsuccessfulSave() {
        TemaXMLRepository temaXmlRepo = mock(TemaXMLRepository.class);
        when(temaXmlRepo.save(any())).thenReturn(null);

        Service service = new Service(null,temaXmlRepo,null);

        int result = service.saveTema("5", "descriere", 10, 5);

        verify(temaXmlRepo, times(1)).save(any());

        assertEquals(1, result);
    }

}
