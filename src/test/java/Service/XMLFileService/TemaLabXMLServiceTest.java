package Service.XMLFileService;
import Exceptions.ValidatorException;
import Repository.XMLFileRepository.TemaLabXMLRepo;
import Validator.TemaLabValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertNotNull;


public class TemaLabXMLServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    TemaLabXMLRepo repository = new TemaLabXMLRepo(new TemaLabValidator(), "TemaLaborator.txt");

    TemaLabXMLService temaLabXMLService = new TemaLabXMLService(repository);

    @Test
    public void testAddAssignmentValidEntity() throws ValidatorException
    {
        String[] sa = { "1", "afasf", "1", "5" };
        temaLabXMLService.add(sa);
        assertNotNull(temaLabXMLService.findOne(1));
    }
    @Test
    public void testAddAssignmentInvalidId() throws ValidatorException
    {
        String[] sa = { "-1", "afasf", "1", "5" };
        thrown.expect(ValidatorException.class);
        thrown.expectMessage("Nr tema invalid");
        temaLabXMLService.add(sa);
    }
    @Test
    public void testAddAssignmentInvalidDeliveryWeek() throws ValidatorException
    {
        String[] sa = {"2", "afasf", "1", "15" };
        thrown.expect(ValidatorException.class);
        thrown.expectMessage("Sapatamana predarii invalida");
        temaLabXMLService.add(sa);

    }

   @Test
   public void testAddAssignmentInvalidDeadline() throws ValidatorException
    {
        String[] sa = { "5", "afasf", "15", "5" };
        thrown.expect(ValidatorException.class);
        thrown.expectMessage("Termen limita invalid");
        temaLabXMLService.add(sa);

    }

    @Test
    public void testAddAssignmentInvalidDescription() throws ValidatorException
    {
        String[] sa = { "5", "", "15", "5" };
        thrown.expect(ValidatorException.class);
        thrown.expectMessage("Descriere tema invalida");
        temaLabXMLService.add(sa);

    }

    @Test
    public void testAddAssignmentIdNaN() throws ValidatorException
    {
        String[] sa = { "a", "afasf", "1", "5" };
        thrown.expect(NumberFormatException.class);
        thrown.expectMessage("For input string: \"a\"");
        temaLabXMLService.add(sa);
    }

    @Test
    public void testAddAssignmentDeliveryWeekNaN() throws ValidatorException
    {
        String[] sa = { "1", "afasf", "a", "5" };
        thrown.expect(NumberFormatException.class);
        thrown.expectMessage("For input string: \"a\"");
        temaLabXMLService.add(sa);
    }

    @Test
    public void testAddAssignmentDeadlineNaN() throws ValidatorException
    {
        String[] sa = { "1", "afasf", "1", "a" };
        thrown.expect(NumberFormatException.class);
        thrown.expectMessage("For input string: \"a\"");
        temaLabXMLService.add(sa);
    }


}
