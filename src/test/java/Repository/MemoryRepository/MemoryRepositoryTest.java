package Repository.MemoryRepository;

import static org.junit.Assert.assertTrue;

import Domain.Student;
import Domain.TemaLab;
import Exceptions.ValidatorException;
import Validator.StudentValidator;
import Validator.TemaLabValidator;
import org.junit.Test;

/**
 * Unit test for Memory Repository
 */
public class MemoryRepositoryTest
{
    @Test
    public void shouldSuccessfullyAddStudent() throws ValidatorException {
        StudentValidator vs=new StudentValidator();
        StudentRepo studentRepo = new StudentRepo(vs);
        Student student = new Student(
                "testId",
                "testName",
                3112,
                "testMail",
                "testTeacher"
        );
        assertTrue(studentRepo.findAll().spliterator().getExactSizeIfKnown()==0);
        studentRepo.save(student);
        assertTrue(studentRepo.findAll().spliterator().getExactSizeIfKnown()==1);
    }

    @Test
    public void shouldSuccessfullyAddAssignment() throws ValidatorException {
        TemaLabValidator vs=new TemaLabValidator();
        TemaLabRepo temaLabRepo = new TemaLabRepo(vs);
        TemaLab temaLab = new TemaLab(
                1,
                "temaLab",
                6,
                4
        );
        assertTrue(temaLabRepo.findAll().spliterator().getExactSizeIfKnown()==0);
        temaLabRepo.save(temaLab);
        assertTrue(temaLabRepo.findAll().spliterator().getExactSizeIfKnown()==1);
    }

    @Test
    public void shouldThrowWhenGivenIncorrectGroupId(){
        StudentValidator vs=new StudentValidator();
        StudentRepo studentRepo = new StudentRepo(vs);
        Student student = new Student(
                "testId",
                "testName",
                -5,
                "testMail",
                "testTeacher"
        );
        assertTrue(studentRepo.findAll().spliterator().getExactSizeIfKnown()==0);
        try{
            studentRepo.save(student);
            assertTrue(false);
        }catch(ValidatorException ex){
            assertTrue(ex.getMessage().contains("Grupa invalid"));
        }
    }
    @Test
    public void shouldThrowWhenGivenNullStudent(){
        StudentValidator vs=new StudentValidator();
        StudentRepo studentRepo = new StudentRepo(vs);
        Student student = null;
        try{
            studentRepo.save(student);
            assertTrue(false);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage().contains("Entity can not be null!\n"));
        } catch (ValidatorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldThrowWhenGivenNullAssignment() {
        TemaLabValidator vs=new TemaLabValidator();
        TemaLabRepo temaLabRepo = new TemaLabRepo(vs);
        TemaLab temaLab = null;

        try{
            temaLabRepo.save(temaLab);
            assertTrue(false);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage().contains("Entity can not be null!\n"));
        } catch (ValidatorException e) {
            e.printStackTrace();
        }
    }
}
