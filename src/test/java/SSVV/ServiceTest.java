package SSVV;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


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
        int result = service.saveStudent("4", "John Doe", 110);
        assertEquals(1, result);
    }

    @Test
    public void testSaveStudent_Success2() {
        setUp();
        int result = service.saveStudent("5", "Jane Doe", 111);
        assertEquals(1, result);
    }


    @Test
    public void testSaveStudent_Success3() {
        setUp();
        int result = service.saveStudent("4", "John Doe", 225);
        assertEquals(1, result);
    }

    @Test
    public void testSaveStudent_Success4() {
        setUp();
        int result = service.saveStudent("5", "Jane Doe", 937);
        assertEquals(1, result);
    }


    @Test
    public void testSaveStudent_Success5() {
        setUp();
        int result = service.saveStudent("4", "John Doe", 938);
        assertEquals(1, result);
    }

    @Test
    public void testSaveStudent_Failure3() {
        setUp();
        int result = service.saveStudent("5", "Jane Doe", 939);
        assertEquals(1, result);
    }


    @Test
    public void testSaveStudent_Failure4() {
        setUp();
        int result = service.saveStudent("4", "John Doe", 999);
        assertEquals(1, result);
    }


//WHITE-BOX TESTING------------------------------------------------------------------------------------------------



}
