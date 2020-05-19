package Service.XMLFileService;

import Domain.Student;
import Domain.TemaLab;
import Exceptions.ValidatorException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Repository.XMLFileRepository.NotaXMLRepo;
import Repository.XMLFileRepository.StudentXMLRepo;
import Repository.XMLFileRepository.TemaLabXMLRepo;
import Validator.NotaValidator;
import Validator.StudentValidator;
import Validator.TemaLabValidator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

public class IncrementalIntegrationTesting {

    StudentXMLRepo repo = new StudentXMLRepo(new StudentValidator(), "Studfile.txt");
    TemaLabXMLRepo temaLabXMLRepo= new TemaLabXMLRepo(new TemaLabValidator(),"Tema.txt");
    NotaXMLRepo notaXMLRepo = new NotaXMLRepo(new NotaValidator(),"nota.txt");

    StudentXMLService studentXMLService = new StudentXMLService(repo);
    TemaLabXMLService temaLabXMLService = new TemaLabXMLService(temaLabXMLRepo);
    NotaXMLService notaXMLService = new NotaXMLService(notaXMLRepo);



    @Test
    public void addStudent() throws ValidatorException {

        String[] stud = { "15", "Nume", "935", "a@scs.ubbcluj.com", "Teacher" };
        studentXMLService.add(stud);
        assertNotNull(studentXMLService.findOne("15"));
    }

    @Test
    public void addAssignment() throws ValidatorException {
        addStudent();

        String[] tema = { "3", "vvss", "1", "5" };
        temaLabXMLService.add(tema);
        assertNotNull(temaLabXMLService.findOne(3));
    }

    @Test
    public void addGrade() throws ValidatorException {
        addStudent();
        addAssignment();

        String[] sa = { "1", "15", "3", "9.1", (LocalDateTime.now()).toString() };
        notaXMLService.add(sa);
        assertNotNull(notaXMLService.findOne(1));
    }


    @Test
    public void incrementalIntegration() throws ValidatorException {
        addStudent();
        addAssignment();
        addGrade();
    }





}
