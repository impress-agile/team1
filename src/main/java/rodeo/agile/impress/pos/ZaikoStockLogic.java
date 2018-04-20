package rodeo.agile.impress.pos;

import java.sql.SQLException;

public class ZaikoStockLogic {
	
	private ZaikoDao dao;
	
	public ZaikoStockLogic(ZaikoDao dao) {
		this.dao = dao;
	}
		
	public void add(String name, int amount) throws ClassNotFoundException, SQLException {
		if (amount == 0) {
			throw new RuntimeException("Failed to add item which amount is 0.");
		}
		dao.insert(name, amount);
	}

}
