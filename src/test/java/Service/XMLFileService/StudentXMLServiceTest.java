package Service.XMLFileService;

import java.util.Arrays;

import Exceptions.ValidatorException;
import Repository.XMLFileRepository.StudentXMLRepo;
import Validator.StudentValidator;
import lombok.SneakyThrows;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StudentXMLServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    StudentXMLRepo repo = spy(new StudentXMLRepo(new StudentValidator(), "Studfile.txt"));
    StudentXMLService studentXMLService = spy(new StudentXMLService(repo));

    @SneakyThrows
    @Test
    public void addTest() {
        String[] sa = { "1", "a", "1", "a", "b" };
        studentXMLService.add(sa);
        verify(repo).save(any());
    }

    @SneakyThrows
    @Test
    public void badIDAddTest() {
        String[] sa = { "ab", "a", "1a", "aaa", "b" };
        thrown.expect(ValidatorException.class);
        thrown.expectMessage("Grupa invalid");
        studentXMLService.add(sa);
    }

}