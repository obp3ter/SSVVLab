package Service.XMLFileService;

import java.time.LocalDateTime;

import Exceptions.ValidatorException;
import Repository.XMLFileRepository.NotaXMLRepo;
import Repository.XMLFileRepository.StudentXMLRepo;
import Repository.XMLFileRepository.TemaLabXMLRepo;
import Validator.NotaValidator;
import Validator.StudentValidator;
import Validator.TemaLabValidator;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class BIgBang {
    StudentXMLRepo repo = new StudentXMLRepo(new StudentValidator(), "Studfile.txt");
    TemaLabXMLRepo temaLabXMLRepo= new TemaLabXMLRepo(new TemaLabValidator(),"Tema.txt");
    NotaXMLRepo notaXMLRepo = new NotaXMLRepo(new NotaValidator(),"nota.txt");

    StudentXMLService studentXMLService = new StudentXMLService(repo);
    TemaLabXMLService temaLabXMLService = new TemaLabXMLService(temaLabXMLRepo);
    NotaXMLService notaXMLService = new NotaXMLService(notaXMLRepo);

    @Test
    public void studentTest()throws ValidatorException
    {
        String[] sa = { "1", "a", "1", "a", "b" };
        studentXMLService.add(sa);
        assertNotNull(studentXMLService.findOne("1"));
    }

    @Test
    public void labTest()throws ValidatorException
    {
        String[] sa = { "1", "a", "1", "1" };
        temaLabXMLService.add(sa);
        assertNotNull(temaLabXMLService.findOne(1));
    }

    @Test
    public void notaTest()throws ValidatorException
    {
        String[] sa = { "1", "a", "1", "9.1", (LocalDateTime.now()).toString() };
        notaXMLService.add(sa);
        assertNotNull(notaXMLService.findOne(1));
    }

    @Test
    public void allTest() throws ValidatorException
    {
        studentTest();
        labTest();
        notaTest();
    }




}
