
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author miaryvard
 */
public class TravisTestTest
{
    
    public TravisTestTest()
    {
    }
   

    @Test
    public void testgivemeten()
    {
         assertEquals(10, TravisTest.giveMeTen());
    }
    
}
