package rodeo.agile.impress.pos;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class ZaikoStockLogicTest {

	private ZaikoDao dao = null;
	private ZaikoStockLogic stockLogic = null;
	
	@Before
	public void setup() {
		dao = mock(ZaikoDao.class);
		stockLogic = new ZaikoStockLogic(dao);
	}
	
	
	@Test
	public void testInsertMethodShouldBeCalledIfValuesAreValid() throws ClassNotFoundException, SQLException {		
		stockLogic.add("ValidName", 5);
		
		verify(dao, times(1)).insert("ValidName", 5);
	}
	
	@Test (expected=RuntimeException.class)
	public void testExceptionShouldBeThrownIfAmountIsZero() throws ClassNotFoundException, SQLException {
		stockLogic.add("ValidName", 0);
	}

}
