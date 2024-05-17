package SSVV;

import domain.Nota;
import domain.Pair;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ServiceTest {
    private Service service;
    private StudentXMLRepository studentRepo;
    private TemaXMLRepository temaRepo;
    private NotaXMLRepository notaRepo;

    @BeforeEach
    public void setUp() {
        studentRepo = mock(StudentXMLRepository.class);
        temaRepo = mock(TemaXMLRepository.class);
        notaRepo = mock(NotaXMLRepository.class);
        service = new Service(studentRepo, temaRepo, notaRepo);
    }

    @Test
    public void testSaveStudent_Failure1() {
        int result = service.saveStudent("4", "John Doe", 88);
        assertEquals(1, result);
    }

    @Test
    public void testSaveStudent_Failure2() {
        int result = service.saveStudent("5", "Jane Doe", 109);
        assertEquals(1, result);
    }

    @Test
    public void testSaveStudent_Success1() {
        int result = service.saveStudent("6", "John Doe", 110);
        assertEquals(1, result);
    }

    @Test
    public void testSaveStudent_Success2() {
        int result = service.saveStudent("7", "Jane Doe", 111);
        assertEquals(1, result);
    }


    @Test
    public void testSaveStudent_Success3() {
        int result = service.saveStudent("8", "John Doe", 225);
        assertEquals(1, result);
    }

    @Test
    public void testSaveStudent_Success4() {
        int result = service.saveStudent("9", "Jane Doe", 937);
        assertEquals(1, result);
    }


    @Test
    public void testSaveStudent_Success5() {
        int result = service.saveStudent("10", "John Doe", 938);
        assertEquals(1, result);
    }

    @Test
    public void testSaveStudent_Failure3() {
        int result = service.saveStudent("11", "Jane Doe", 939);
        assertEquals(1, result);
    }


    @Test
    public void testSaveStudent_Failure4() {
        int result = service.saveStudent("12", "John Doe", 999);
        assertEquals(1, result);
    }


    //WHITE-BOX TESTING------------------------------------------------------------------------------------------------
    @Test
    public void testSaveTema_ValidInput() {
        int result = service.saveTema("4", "descriere", 10, 5);
        assertEquals(1, result);
    }

    @Test
    public void testSaveTema_NullId() {
        int result = service.saveTema(null, "descriere", 10, 5);
        assertEquals(1, result);
    }

    @Test
    public void testSaveTema_NullDesc() {
        int result = service.saveTema("4", null, 10, 5);
        assertEquals(1, result);
    }

    @Test
    public void testSaveTema_InvalidDeadline() {
        int result = service.saveTema("5", "descriere", 15, 10);
        assertEquals(1, result);
    }

    @Test
    public void testSaveTema_InvalidStartline() {
        int result = service.saveTema("5", "descriere", 12, 0);
        assertEquals(1, result);
    }


    @Test
    public void testSaveTema_InvalidStartDeadline() {
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


    //INTEGRATION TESTING------------------------------------------------------------------------------------------------

    @Test
    public void testSaveStudent_Success() {
        when(studentRepo.save(any(Student.class))).thenReturn(null);

        int result = service.saveStudent("6", "John Doe", 110);
        assertEquals(1, result);

        verify(studentRepo).save(any(Student.class));
    }

    @Test
    public void testSaveTema_Success() {
        when(temaRepo.save(any(Tema.class))).thenReturn(null);

        int result = service.saveTema("1", "Description", 7, 3);
        assertEquals(1, result);

        verify(temaRepo).save(any(Tema.class));
    }

    @Test
    public void testAllIntegration() {
        when(studentRepo.save(any(Student.class))).thenReturn(new Student("123", "Alice Wonderland", 935));
        when(temaRepo.save(any(Tema.class))).thenReturn(new Tema("10", "Assignment Description", 7, 1));

        Pair<String, String> idNota = new Pair<>("123", "10");
        when(notaRepo.save(any(Nota.class))).thenReturn(new Nota(idNota, 9, 7, "Well Done"));

        service.saveStudent("123", "Alice Wonderland", 935);
        service.saveTema("10", "Assignment Description", 7, 1);
        service.saveNota("123", "10", 9, 7, "Well Done");

        verify(studentRepo).save(any(Student.class));
        verify(temaRepo).save(any(Tema.class));
    }
}
