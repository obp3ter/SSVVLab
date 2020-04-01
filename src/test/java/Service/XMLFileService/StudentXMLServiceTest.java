package Service.XMLFileService;

import java.util.ArrayList;
import java.util.Arrays;

import Exceptions.ValidatorException;
import Repository.XMLFileRepository.StudentXMLRepo;
import Validator.StudentValidator;
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

    @Test
    public void addTest() throws ValidatorException {
        String[] sa = { "1", "a", "1", "a", "b" };
        studentXMLService.add(sa);
        verify(repo).save(any());
    }

    @Test
    public void badIDAddTest() throws ValidatorException {
        String[] sa = { "ab", "a", "1a", "aaa", "b" };
        thrown.expect(ValidatorException.class);
        thrown.expectMessage("Grupa invalid");
        studentXMLService.add(sa);
    }

    @Test
    public void conditionCoverageAddTest() throws ValidatorException {
        ArrayList<String[]> tests = new ArrayList<>();
        String[] testAllOk = { "1", "a", "1", "a", "b" };
        tests.add(testAllOk);
        String[] testId = { "", "a", "1", "a", "b", "Id invalid\n" };
        tests.add(testId);
        String[] testName1 = { "1", "", "1", "a", "b", "Nume invalid\n" };
        tests.add(testName1);
        String[] testName2 = { "1", null, "1", "a", "b", "Nume invalid\n" };
        tests.add(testName2);
        String[] testGroup = { "1", "a", "-1", "a", "b", "Grupa invalida\n" };
        tests.add(testGroup);
        String[] testEmail1 = { "1", "", "1", "a", "b", "Email invalid\n" };
        tests.add(testEmail1);
        String[] testEmail2 = { "1", null, "1", "a", "b", "Email invalid\n" };
        tests.add(testEmail2);
        for (String[] test : tests) {
            conditionCoverageInternal(test);
        }

    }

    private void conditionCoverageInternal(String... values)
            throws ValidatorException {
        if (values.length > 5) {
            thrown.expect(ValidatorException.class);
            thrown.expectMessage(values[5]);
        }
        studentXMLService.add(values);
        if (values.length == 5) {
            verify(repo).save(any());
        }
        studentXMLService.remove("1");

    }

}